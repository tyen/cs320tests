package harness;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.postgresql.*;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Handles the interactions with the Postgres database.
 * Initiates an SSH tunnel before connecting to the database.
 * Requires an open-ssh private key named "id_rsa" in local directory.
 * @author Sean Dooley
 */
public class DBHandler {

	private Connection connection;
	private PreparedStatement statement;
	private Session tunnel;
	
	// Singleton because problems arise if more than one tunnel is created
	private static DBHandler instance = new DBHandler();

	// Load Postgres Driver
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates a new DBHanlder object.
	 * Attempts to connect to the database.
	 */
	private DBHandler(){
		this.connect();
	}

	/**
	 * Creates an SSH tunnel so that the handler can connect to the Postgres database.
	 * @return <code>true</code> if the tunnel was initiated successfully, else <code>false</code>
	 */
	private boolean initiateTunnel() {
		try {
			if(this.isTunnelOpen())
				return true;

			JSch jsch = new JSch();

			// Path to private RSA key
			jsch.addIdentity("id_rsa");

			Properties config = new Properties(); 
			config.put("StrictHostKeyChecking", "no");

			this.tunnel = jsch.getSession("cs320", "manabi.org", 22);
			this.tunnel.setPortForwardingL(3333, "manabi.org", 5432);
			this.tunnel.setConfig(config);
			this.tunnel.connect();

			if(this.tunnel.isConnected())
				return true;
			else
				return false;

		} catch (JSchException e) {
			return false;
		}
	}

	/**
	 * Closes the SSH tunnel.
	 */
	private void closeTunnel() {
		if(this.tunnel == null)
			return;

		this.tunnel.disconnect();
	}

	/**
	 * Returns <code>true</code> if the tunnel is open, else <code>false</code>.
	 * @return <code>true</code> if the tunnel is open, else <code>false</code>.
	 */
	private boolean isTunnelOpen() {
		return tunnel != null && tunnel.isConnected();
	}

	/**
	 * Connects to the database and initiates a tunnel if necessary.
	 * @return <code>true</code> if the connection to the database was successful, else <code>false</code>
	 */
	public boolean connect() {
		try {
			// Attempt to initiate an SSH tunnel if it is not already open
			if(!this.isTunnelOpen() && !this.initiateTunnel())
				return false;

			if(this.isConnected())
				return true;

			this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:3333/cs320test", "cs320", "hnakpt7");
			
			if(connection.isClosed())
				return false;
			else
				return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 * Returns <code>true</code> if there is a connection to the database, else <code>false</code>.
	 * @return <code>true</code> if there is a connection to the database, else <code>false</code>
	 */
	public boolean isConnected() {
		try {
			if(!this.isTunnelOpen())
				return false;
			if(this.connection == null)
				return false;
			if(this.connection.isClosed())
				return false;

			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Inserts a row into the specified table based on the column names and values specified.
	 * Requires a connection to the database.
	 * @param table The table to insert the data into
	 * @param data A map containing the column names and corresponding values to insert into the database
	 * @throws NoDBConnectivityException Thrown when no connection to the database exists. Call {@link #connect()} first.
	 */
	public void insert(String table, HashMap<String, String> data) throws NoDBConnectivityException {
		if(!this.isConnected())
			throw new NoDBConnectivityException();

		String query = "INSERT INTO " + table + " ";
		String columns = "(";
		String values = "(";

		// Add each column/value pair for query
		int count = 1;
		for (Map.Entry<String, String> entry : data.entrySet()) {
			if(count < data.size()){
				columns += entry.getKey() + ", ";
				values += entry.getValue() + ", ";
			}
			else{
				columns += entry.getKey() + ") ";
				values += entry.getValue() + ") ";
			}

			++count;
		}

		// Concatenate columns with corresponding values
		query += columns + "VALUES " + values;

		try {
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the generated key from the last statement executed if successful, else 0
	 */
	public int getLastGeneratedKey() {
		try {
			ResultSet keys = statement.getGeneratedKeys();

			if(keys.next()){
				return keys.getInt(1);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Updates a row in the specified table based on the column names and values specified.
	 * Requires a connection to the database.
	 * @param table The table to update the data in
	 * @param data A map containing the column names and corresponding values to insert into the database
	 * @return <code>true</code> if the update was successful, else <code>false</code>
	 * @throws NoDBConnectivityException Thrown when no connection to the database exists. Call {@link #connect()} first.
	 */
	public void update(String table, HashMap<String, String> data, HashMap<String, String> identifier) throws NoDBConnectivityException {
		if(!this.isConnected())
			throw new NoDBConnectivityException();

		String query = "UPDATE " + table + " SET ";
		String idKey = "";
		String idValue = "";

		// Add each column/value pair for query
		int count = 1;
		for (Map.Entry<String, String> entry : data.entrySet()) {
			if(count < data.size()){
				query += entry.getKey() + " = " + entry.getValue() + ", ";
			}
			else{
				query += entry.getKey() + " = " + entry.getValue() + " ";
			}

			++count;
		}

		for (Map.Entry<String, String> entry : identifier.entrySet()) {
			idKey = entry.getKey();
			idValue = entry.getValue();
		}

		query += "WHERE " + idKey + " = " + idValue;

		try {
			statement = connection.prepareStatement(query);
			statement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Determines if an entry in the database exists based on the specified values.
	 * Requires a connection to the database.
	 * @param table The table to look for the entry in
	 * @param data A map representing the entry to look for in the database
	 * @return <code>true</code> if the entry exists in the database, else <code>false</code>
	 * @throws NoDBConnectivityException Thrown when no connection to the database exists. Call {@link #connect()} first.
	 */
	public boolean exists(String table, HashMap<String, String> data) throws NoDBConnectivityException {
		ResultSet results = query(table, data);
		
		try {
			if(results != null && results.next())
				return true;

			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Queries the database based on the given table and data
	 * @param table The table to query on
	 * @param data HashMap of data to add to query
	 * @return The results of the query if successful, else <code>null</code>
	 * @throws NoDBConnectivityException Thrown when no connection to the database exists. Call {@link #connect()} first.
	 */
	public ResultSet query(String table, HashMap<String, String> data) throws NoDBConnectivityException {
		String query = "SELECT * FROM " + table + " WHERE ";

		// Add each column/value pair for query
		int count = 1;
		for (Map.Entry<String, String> entry : data.entrySet()) {
			if(count < data.size()){
				query += entry.getKey() + "=" + entry.getValue() + " AND ";
			}
			else{
				query += entry.getKey() + "=" + entry.getValue();
			}

			++count;
		}

		return query(query);
	}

	/**
	 * Queries the database with the specified query
	 * @param query The query to execute
	 * @return The results of the query if successful, else <code>null</code>
	 * @throws NoDBConnectivityException Thrown when no connection to the database exists. Call {@link #connect()} first.
	 */
	public ResultSet query(String query) throws NoDBConnectivityException {
		if(!this.isConnected())
			throw new NoDBConnectivityException();

		try {
			statement = connection.prepareStatement(query);
			return statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Closes the connection to the database.
	 */
	public void disconnect(){
		try {
			if(this.connection == null){
				this.closeTunnel();
				return;
			}

			this.connection.close();
			this.closeTunnel();
		} 
		catch (SQLException e) {}
	}

	/**
	 * @return the instance
	 */
	public static DBHandler getInstance() {
		return instance;
	}

	/**
	 * An exception that indicates that no connection to the database exists.
	 * @author Sean Dooley
	 */
	@SuppressWarnings("serial")
	public class NoDBConnectivityException extends Exception {
		public NoDBConnectivityException(){
			super("No connection to the database exists.");
		}
	}
}
