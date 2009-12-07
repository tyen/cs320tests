package cs320;

import java.sql.*;
import java.util.*;
/**
 * The storage module provides an interface to the data repository. 
 * Four methods are provided: Exist(), Save(), Store() and Retrieve(). 
 * Exist() checks for the existence of a record and returns True if the 
 * record is found. Retrieve() takes parameters to be searched for 
 * within the data repository and returns all tuples that match those 
 * parameters within the data repository. Save() takes information passed 
 * in, saves it to the repository through the storage module and returns 
 * True if the operation was successful. Store() flags the relation relating 
 * to the patient record matching with PatientID within the data repository 
 * as only able to be appended to. It then calls the PushRecord() method.
 *
 */
public class Storage{
	private static Storage instance = null;
	boolean ServerConnected = false;
	boolean ClientConnected = false;
	ResultSet ServerResults = null;
	Connection ServerConnection = null;
	DatabaseMetaData ServerMetaData = null;
	Statement ClientStatement = null;
	ResultSet ClientResults = null;
	Connection ClientConnection = null;
	DatabaseMetaData ClientMetaData = null;
	Statement ServerStatement;
	String serverUrl = "jdbc:mysql://tbonci.thruhere.net:3306";
	String clientUrl = "jdbc:mysql://localhost:3306";
	String UserName = "darbour";
	String Password = "";

	enum ClientRelations {
		Patient, Drug
	};

	enum ServerRelations {
		Drug
	};

	Log log;

	/**
	 * 
	 * @param UserName
	 * @param Password
	 *            Creates a connection between the storage module and the data
	 *            repositories. Sets connected flags indicating which data
	 *            repositories have been successfully connected to.
	 */
	protected Storage() {
		this.log = new Log();
		Connect();
	}

	public static Storage GetInstance() {
		if (instance == null)
			instance = new Storage();
		return instance;
	}

	/**
	 * Connect() will act as a wrapper for the data repository connection. 
	 * The data repository credentials as well as hostname, connection port, 
	 * and the connection protocol will be hidden. In the event of success 
	 * the connected flag will be set for the appropriate data repository 
	 * (either ServerConnected or ClientConnected).
	 */
	void Connect() {
		if (!ServerConnected) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				ServerConnection = DriverManager.getConnection(serverUrl,
						this.UserName, this.Password);
				ServerConnected = true;
				ServerStatement = ServerConnection.createStatement();
				ServerMetaData = ServerConnection.getMetaData();
			} catch (Exception e) {
				System.err.println("Failed to connect to Server DB\n" + e);
			}
		}
		if (!ClientConnected) {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				ClientConnection = DriverManager.getConnection(clientUrl,
						this.UserName, this.Password);
				ClientConnected = true;
				ClientStatement = ClientConnection.createStatement();
				ClientMetaData = ClientConnection.getMetaData();
			} catch (Exception e) {
				System.err.println("Failed to connect to Client DB\n" + e);
			}
		}
	}

	/**
	 * Retrieve queries the data repository, searching for an instance 
	 * such that all Values specified are contained in the corresponding 
	 * fields within the Relation. The result set is then returned as a 
	 * list containing each tuple represented as a Field to Value map. 
	 * If there are no results within the set an empty List is returned.
	 * @param Relation
	 * @param Input
	 * @return
	 */
	List<HashMap<String, String>> Retrieve(String Relation,
			Map<String, String> Input) { 

		// write list of fields to log
		if (Input != null) {
			Object[] fields = Input.keySet().toArray();
			log.Write("", "Retrieve", new ArrayList(Arrays.asList(fields)));
		} else {// handles if called from Push()
			List<String> nullList = new ArrayList();
			nullList.add(null);
			log.Write("", "Retrieve", nullList);
		}

		String Query = "";
		List<HashMap<String, String>> ReturnList = new ArrayList<HashMap<String, String>>();
		int count = 0;
		if (Input != null) {
			Query = "SELECT * FROM " + Relation + " WHERE ";
			// Processes query
			for (Map.Entry<String, String> KeyValue : Input.entrySet()) {
				Query += KeyValue.getKey() + " = " + "'" + KeyValue.getValue()
						+ "'";
				if (count++ < Input.size() - 1) {
					Query += " AND ";
				}
			}
		} else {
			Query = "select * from " + Relation;
		}
		Query += ";";

		System.out.println("Query: " + Query); // added for debugging purposes

		if (ServerConnected) {
			try {
				// executes the query and gets the meta data and creates arrays
				// for data
				ServerResults = ServerStatement.executeQuery(Query);
				ResultSetMetaData rsMeta = ServerResults.getMetaData();
				String[] colNames = new String[rsMeta.getColumnCount()];
				Vector[] data = new Vector[colNames.length];

				// fills out the columns
				for (int col = 0; col < colNames.length; col++) {
					colNames[col] = rsMeta.getColumnName(col + 1);
					data[col] = new Vector();
				}
				int rowCount = 0;
				while (ServerResults.next()) {// fills out data array
					rowCount = ServerResults.getRow();// will eventually set to
					// total row count
					for (int col = 0; col < colNames.length; col++) {
						Object dat = ServerResults.getObject(colNames[col]);
						data[col].add(dat);
					}
				}
				// put each row in a hashmap
				for (int rowNum = rowCount - 1; rowNum >= 0; rowNum--) {
					HashMap<String, String> firstResult = new HashMap<String, String>();
					for (int col = 0; col < colNames.length; col++) {
						Object dummyData = data[col].remove(rowNum);
						if (dummyData != null) {
							firstResult
									.put(colNames[col], dummyData.toString());
						} else {
							firstResult.put(colNames[col], "");
						}
					}
					ReturnList.add(firstResult); // puts that map in the list
				}
			} catch (Exception e) {
				System.err.println("Error calling Retrieve(Server)\n" + e);
			}
		}
		if (ClientConnected) {
			try {
				ClientResults = ClientStatement.executeQuery(Query);
				ResultSetMetaData rsMeta = ClientResults.getMetaData();
				String[] colNames = new String[rsMeta.getColumnCount()];
				Vector[] data = new Vector[colNames.length];
				for (int col = 0; col < colNames.length; col++) {
					colNames[col] = rsMeta.getColumnName(col + 1);
					data[col] = new Vector();
				}
				int rowCount = 0;
				while (ClientResults.next()) {
					rowCount = ClientResults.getRow();
					for (int col = 0; col < colNames.length; col++) {
						Object dat = ClientResults.getObject(colNames[col]);
						data[col].add(dat);
					}
				}
				for (int rowNum = rowCount - 1; rowNum >= 0; rowNum--) {
					HashMap<String, String> firstResult = new HashMap<String, String>();
					for (int col = 0; col < colNames.length; col++) {
						Object dummyData = data[col].remove(rowNum);
						if (dummyData != null) {
							firstResult
									.put(colNames[col], dummyData.toString());
						} else {
							firstResult.put(colNames[col], "");
						}
					}
					ReturnList.add(firstResult);
				}
			} catch (Exception e) {
				System.err.println("Error calling Retrieve(Client)\n" + e);
			}
		}

		return ReturnList;
	}

	/**
	 * Exist queries the data repository, searching for an instance such 
	 * that all Values specified are contained in the corresponding fields 
	 * within the Relation. If the result set from the query is not empty, 
	 * then the function returns True, otherwise it returns False.
	 * @param Relation
	 * @param Input
	 * @return
	 */
	boolean Exist(String Relation, Map<String, String> Input) {
		Connect();

		// write list of fields to log
		Object[] fields = Input.keySet().toArray();
		log.Write("", "Exist", new ArrayList(Arrays.asList(fields)));

		String Query = "SELECT COUNT(1) FROM " + Relation + " WHERE ";
		int count = 0;
		for (Map.Entry<String, String> KeyValue : Input.entrySet()) {
			Query += KeyValue.getKey() + "= '" + KeyValue.getValue() + "'";
			if (count++ < Input.size() - 1) {
				Query += " AND ";
			}
		}
		Query += ";";

		if (ClientConnected) {
			try {
				ClientResults = ClientStatement.executeQuery(Query);
				while (ClientResults.next()) {
					count = ClientResults.getInt(1);
					if (count > 0) {
						return true;
					}
				}
			} catch (Exception e) {
				System.err.println("Error calling Exist(Client)\n" + e);
			}
		}
		if (ServerConnected) {
			try {
				ServerResults = ServerStatement.executeQuery(Query);
				while (ServerResults.next()) {
					count = ServerResults.getInt(1);
					if (count > 0) {
						return true;
					}
				}
			} catch (Exception e) {
				System.err.println("Error calling Exist(Server)\n" + e);
			}
		}

		return false;
	}

	/**
	 * Save attempts to assign all Values to their corresponding Fields 
	 * within the Relation in the data repository. If the information is 
	 * inserted without error, the function returns True, otherwise it 
	 * returns False.
	 * @param Relation
	 * @param Input
	 * @return
	 */
	boolean Save(String Relation, Map<String, String> Input) {

		// write list of fields to log
		Object[] fields = Input.keySet().toArray();
		log.Write("", "Save", new ArrayList(Arrays.asList(fields)));

		String Query = "INSERT INTO " + Relation + "(";
		String Values = "";
		int count = 0;
		for (Map.Entry<String, String> KeyValue : Input.entrySet()) {
			Query += KeyValue.getKey();
			Values += "'" + KeyValue.getValue() + "'";
			if (count++ < Input.size() - 1) {
				Query += ",";
				Values += ",";
			}
		}
		Query += ") VALUES(" + Values + ")";
		Query += ";";
		System.out.println("Query: " + Query);
		if (ClientConnected) {
			try {
				count = ClientStatement.executeUpdate(Query);
				if (count > 0)
					return true;
				else
					return false;
			} catch (Exception e) {
				System.out.println("error calling Save()(Client)" + e);
			}
		}

		return false;
	}

	/**
	 * PushRecord() checks whether there is currently a connection to 
	 * the server data repository by checking the status of the ServerConnected 
	 * variable. In the case that there is not currently a connection to 
	 * the server data repository one is made, using the Connect() function. 
	 * After a connection is made the client data repository transfers all 
	 * completed records to the server data repository. After the information 
	 * has been successfully transferred and acknowledged the information is 
	 * removed from the client data repository. If the entire process is 
	 * successful it returns True, otherwise it returns False.
	 * @return True if Sync was succesful, false otherwise
	 */
	boolean Push() {
		List<HashMap<String, String>> ReturnList = new ArrayList<HashMap<String, String>>();// What is returned from
		// retrieve
		boolean allPushed = true;// for testing if all records pushed
		boolean[] testSaves;// holding record save booleans

		// Makes sure we can connect
		Connect();
		if (!ClientConnected || !ServerConnected) {
			return false;
		}

		// disconnect from the server so we only get entries from local
		try {
			ServerResults.close();
			ServerStatement.close();
			ServerConnected = false;
			ServerConnection.close();

		} catch (Exception e) {
			System.out.println("Server DB failed to close in Push() \n" + e);
		}

		// Gets all the records from the client
		ReturnList = Retrieve("cs320.patient", null);
		//if there are no entries on the client db
		if(ReturnList.size()==0){
			return true;
		}
		// Creates an array of booleans for testing if all the records pushed
		testSaves = new boolean[ReturnList.size()];

		// disconnect from client so we only save to server
		/*Connect();
		try {
			ClientResults.close();
			ClientConnection.close();
			ClientStatement.close();
			ClientConnected = false;
		} catch (Exception e) {
			System.out.println("Client DB failed to close\n" + e);
		}*/

		Map<String, String> output = new HashMap<String, String>();
		Map<String, String> input = new HashMap<String, String>();

		// Take a map out of the list
		for (int i = 0; i < ReturnList.size(); i++) {
			output = ReturnList.get(i);
			System.out.println("returnlist size:" + ReturnList.size());
			// trim out p_id
			for (Map.Entry<String, String> e : output.entrySet()) {
				if (!e.getKey().equalsIgnoreCase("p_id")) {
					// redo is_editable for input
					if (e.getKey().equalsIgnoreCase("is_editable")) {
						if (e.getValue().equalsIgnoreCase("false")) {
							input.put(e.getKey(), "0");
						}
						if (e.getValue().equalsIgnoreCase("true")) {
							input.put(e.getKey(), "1");
						}
					} else
						input.put(e.getKey(), e.getValue());
				}
			}
			// Just here for debugging purposes
			/*
			 * System.out.println("Input:"); for(Map.Entry<String, String> e :
			 * input.entrySet()){
			 * System.out.println(e.getKey()+": "+e.getValue()); }
			 */
			// Save to server and save output booleans
			testSaves[i] = ServerUpdate(input);
			testSaves[i]=true;
			input.clear();
		}
		// Test output booleans
		for (int i = 0; i < testSaves.length; i++) {
			if (!testSaves[i]) {
				allPushed = false;
			}
		}
		if(allPushed){
			String deleteQuery = "delete * from cs320.patient;";
			try {
				ClientStatement.executeUpdate(deleteQuery);
				return true;
			} catch (Exception e) {
				System.out.println("Delete update from Push() failed.");
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Store flags the relation relating to the patient record matching with 
	 * PatientID within the data repository as only able to be appended to. 
	 * It then calls the pushRecord method.
	 * @param PatientId
	 * @return True if success, false otherwise.
	 * 
	 */
	boolean Store(String PatientId) {
		// if(PatientId==null){
		// return false;
		// }
		String Query = "update cs320.patient set is_editable=\"0\" where p_id = '"
				+ PatientId + "'";
		try {
			int count = ClientStatement.executeUpdate(Query);
			if (count > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println("error calling Store()" + e);
		}

		return false;
	}

	/**
	 * 
	 * Sync() makes a connection from the client data repository to 
	 * the server data repository. After a connection is made the client 
	 * data repository sends the current version number of its system 
	 * schema to the server. If the version number of the server does 
	 * not match, the server sends the most up-to-date schema to replace 
	 * the client�s. If the entire process is successful it returns True, 
	 * otherwise it returns False.
	 * 
	 * @param relation
	 * @return
	 */
	boolean Sync(String relation) {
		Connect();
		if (!ClientConnected || !ServerConnected) {
			return false;
		} else {
			try {
				String ClientVersion = "";
				String ServerVersion = "";

				// Gets the column comments from patient on both client and
				// server
				String Query = "select column_comment from information_schema.columns where table_schema='cs320' and table_name='"
						+ relation + "'";
				ServerResults = ServerStatement.executeQuery(Query);
				ClientResults = ClientStatement.executeQuery(Query);
				if (ServerResults.next()) {
					ServerVersion = ServerResults.getString(1);
				}
				if (ClientResults.next()) {
					ClientVersion = ServerResults.getString(1);
				}
				// If those two match
				if (ClientVersion.equalsIgnoreCase(ServerVersion)) {
					return true;
				} else {
					if (relation.equalsIgnoreCase("cs320.patient")) {
						if (!Push())
							return false;
					}
					Connect();
					Query = "select column_name, column_key, column_type, is_nullable, extra from information_schema.columns where table_schema='cs320' and table_name='"
							+ relation + "';";
					ServerResults = ServerStatement.executeQuery(Query);

					List<HashMap<String, String>> ReturnList = new ArrayList<HashMap<String, String>>();
					ResultSetMetaData rsMeta = ServerResults.getMetaData();
					String[] colNames = new String[rsMeta.getColumnCount()];
					Vector[] data = new Vector[colNames.length];

					// fills out the columns
					for (int col = 0; col < colNames.length; col++) {
						colNames[col] = rsMeta.getColumnName(col + 1);
						data[col] = new Vector();
					}
					int rowCount = 0;
					while (ServerResults.next()) {// fills out data array
						rowCount = ServerResults.getRow();// will eventually set
															// to
						// total row count
						for (int col = 0; col < colNames.length; col++) {
							Object dat = ServerResults.getObject(colNames[col]);
							data[col].add(dat);
						}
					}
					// put each row in a hashmap
					for (int rowNum = rowCount - 1; rowNum >= 0; rowNum--) {
						HashMap<String, String> firstResult = new HashMap<String, String>();
						for (int col = 0; col < colNames.length; col++) {
							Object dummyData = data[col].remove(rowNum);
							if (dummyData != null) {
								firstResult.put(colNames[col], dummyData
										.toString());
							} else {
								firstResult.put(colNames[col], "");
							}

						}
						ReturnList.add(firstResult);
					}
					System.out.println("Returnlist size: " + ReturnList.size());

					String createQuery = "create table `cs320`.`" + relation
							+ "` ( ";

					for (int i = ReturnList.size() - 1; i >= 0; i--) {
						String type = "";
						String name = "";
						String nullable = "";
						String key = "";
						String inc = "";
						HashMap<String, String> output = ReturnList.get(i);
						System.out.println();
						for (Map.Entry<String, String> e : output.entrySet()) {
							if (e.getKey().equalsIgnoreCase("column_name")) {
								name = e.getValue();
								// createQuery+="`"+e.getValue()+"` ";
							}
							if (e.getKey().equalsIgnoreCase("column_key")) {
								if (e.getValue().equalsIgnoreCase("pri"))
									key += "primary key ( `" + name + "` )";
								if (e.getValue().equalsIgnoreCase("uni"))
									key += "unique ( `" + name + "` )";
							}
							if (e.getKey().equalsIgnoreCase("column_type")) {
								type = e.getValue() + " ";
							}
							if (e.getKey().equalsIgnoreCase("is_nullable")) {
								if (e.getValue().equalsIgnoreCase("no"))
									nullable = "not null";
								if (e.getValue().equalsIgnoreCase("yes"))
									nullable = "null";
							}
							if(e.getKey().equalsIgnoreCase("extra")){
								if(e.getValue().equalsIgnoreCase("auto_increment")){
									inc=e.getValue();
								}
							}

							System.out
									.println(e.getKey() + ": " + e.getValue());
						}
						createQuery += "`" + name + "` ";
						createQuery += type + " ";
						createQuery += nullable + " ";
						if(!inc.equalsIgnoreCase("")){
							createQuery += inc+ " ";
							inc="";
						}
						if (ServerVersion != null)
							createQuery += "comment '" + ServerVersion + "'";
						ServerVersion = null;
						if (!key.equalsIgnoreCase(""))
							createQuery += "," + key;

						createQuery += ",";
						// Step through ReturnList and create a create table SQL
						// command from it.
					}
					createQuery = createQuery.substring(0,
							createQuery.length() - 1);
					createQuery += ") ENGINE = MYISAM ;";
					System.out.println(createQuery);
					int result = ClientStatement
							.executeUpdate("drop table cs320." + relation + ";");
					if (result != 0) {
						return false;
					}
					System.out.println(createQuery);
					System.out.println("drop returned: " + result);
					result = ClientStatement.executeUpdate(createQuery);
					if (result != 0) {
						return false;
					}
					System.out.println("create returned: " + result);
					//if we synced user or drug
					if(relation.equalsIgnoreCase("cs320.user")||relation.equalsIgnoreCase("cs320.drug")){
						Connect();//make sure we're connected
						//Retrieve should only get server stuff because we just rebuilt the table for [relation]
						ReturnList=Retrieve(relation, null);
						//if there isn't anything on the server, we're done!
						if(ReturnList.size()==0){
							return true;
						}
						//otherwise make an array of booleans to see if we save all of them
						boolean [] testSaves = new boolean[ReturnList.size()];
						//Map for saving entries
						Map<String, String> input = new HashMap<String, String>();
						//boolean to check if all saved
						boolean allSaved=true;
						// Take a map out of the list, save it, save return val
						for (int i = 0; i < ReturnList.size(); i++) {
							input = ReturnList.get(i);
							testSaves[i]=Save(relation,input);//save it
						}
						// Test saved? booleans
						for (int i = 0; i < testSaves.length; i++) {
							if (!testSaves[i]) {
								allSaved = false;
							}
						}
						//if one of the saves hiccups, we failed.
						if(!allSaved){
							return false;
						}
					}
								
					return true;

				}
			} catch (Exception e) {
				System.err.println("Sync Failed:" + e);
				e.printStackTrace();

				return false;
			}

		}
	}

	/**
	 * Close all open db connections.
	 */
	void Close() {
		if (ServerConnection != null) {
			try {
				ServerResults.close();
				ServerConnection.close();
				ServerStatement.close();
				ServerConnected = false;
			} catch (Exception e) {
				System.out.println("Server DB failed to close\n");
			}
		}
		if (ClientConnection != null) {
			try {
				ClientResults.close();
				ClientConnection.close();
				ClientStatement.close();
				ClientConnected = false;
			} catch (Exception e) {
				System.out.println("Client DB failed to close\n" + e);
			}
		}
	}

	/**
	 * Failsafe in case close() method fails to be called.
	 */
	protected void finalize() {
		if (ServerConnection != null) {
			try {
				ServerResults.close();
				ServerConnection.close();
				ServerStatement.close();
			} catch (Exception e) {
				System.out.println("Server DB failed to close\n" + e);
			}
		}
		if (ClientConnection != null) {
			try {
				ClientResults.close();
				ClientConnection.close();
				ClientStatement.close();
			} catch (Exception e) {
				System.out.println("Client DB failed to close\n" + e);
			}
		}
	}

	protected boolean ServerUpdate(Map<String, String> Input) {
		String Query = "INSERT INTO cs320.patient(";
		String Values = "";
		int count = 0;
		for (Map.Entry<String, String> KeyValue : Input.entrySet()) {
			Query += KeyValue.getKey();
			Values += "'" + KeyValue.getValue() + "'";
			if (count++ < Input.size() - 1) {
				Query += ",";
				Values += ",";
			}
		}
		Query += ") VALUES(" + Values + ")";
		Query += ";";
		System.out.println("Query: " + Query);
		if (ServerConnected) {
			try {
				count = ServerStatement.executeUpdate(Query);
				if (count > 0)
					return true;
				else
					return false;
			} catch (Exception e) {
				System.out.println("error calling ServerUpdate()" + e);
			}
		}
		return false;
	}
}
