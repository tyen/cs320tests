import java.sql.*;
import java.util.*;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class StorageTest extends TestCase {
	/** 
	 * validFields contains HashMaps of <field, value> pairs 
	 * that exist in the server DB.
	 **/
	private ArrayList<HashMap<String, String>> validFields;
	private int validFieldsSize = 10;
	/** 
	 * invalidFields contains HashMaps of <field, value> pairs 
	 * that do not exist in the server DB.
	 **/
	private ArrayList<HashMap<String, String>> invalidFields;
	private int invalidFieldsSize = 10;
	
	public StorageTest(String name){
		super(name); 
		}
	
	public void testPush() throws Exception{
		Storage test = new Storage();
		test.Connect();
		
			test.ClientResults = test.ClientStatement.executeQuery("Select * from cs320.patient;");
			Storage test2  = new Storage();
			test2.Connect();
			test2.Push();
			test2.ServerResults = test2.ServerStatement.executeQuery("select * from cs320.patient;");
			assert(test.ClientResults.equals(test2.ServerResults));
		
		
	}
	public void setUpExist(){
		validFields = new ArrayList<HashMap<String, String>>();
		invalidFields = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<validFieldsSize;i++){
			Random r = new Random();
			String rndstr = Long.toString(Math.abs(r.nextLong()), 36);
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("first_name",rndstr);
			hm.put("last_name", "last");
			hm.put("dob", "1999-10-28");
			hm.put("mrn", "10");
			hm.put("cmrn", "10");
			hm.put("sex", "male");
			hm.put("has_note", "1000");
			hm.put("is_editable", "0");
			validFields.add(hm);
			ServerInsert(hm,"patient");
		}
		while(invalidFields.size()<invalidFieldsSize){
			Random r = new Random();
			String rndstr = Long.toString(Math.abs(r.nextLong()), 36);
			boolean write = true;
			for(int i=0;i<validFields.size();i++){
				HashMap<String, String> j = validFields.get(i);
				if(j.containsValue(rndstr))
					write = false;
			}
			if(write){
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("first_name",rndstr);
				invalidFields.add(hm);
			}
		}
	}
	public void testExists() {
		setUpExist();
		Storage test = new Storage();
		Random r1 = new Random();
		for(int i=0;i<100;i++){
			Map<String, String> input = validFields.get(r1.nextInt(validFieldsSize));
			boolean testAnswer = test.Exist("cs320.patient", input);
			assertEquals(true, testAnswer);	
		}
	}
	public void testExists2() {
		setUpExist();
		Storage test = new Storage();
		Random r1 = new Random();
		for(int i=0;i<100;i++){
			Map<String, String> input = invalidFields.get(r1.nextInt(invalidFieldsSize));
			boolean testAnswer = test.Exist("cs320.patient", input);
			assertEquals(false, testAnswer);	
		}
	}
	
	public void setUpRetrieve(){
		validFields = new ArrayList<HashMap<String, String>>();
		invalidFields = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<validFieldsSize;i++){
			Random r = new Random();
			String rndstr = Long.toString(Math.abs(r.nextLong()), 36);
			String rndstr2 = Long.toString(Math.abs(r.nextLong()), 36);
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("username",rndstr);
			hm.put("password",rndstr2);
			hm.put("type","nurse");
			validFields.add(hm);
			ServerInsert(hm,"user");
		}
		while(invalidFields.size()<invalidFieldsSize){
			Random r = new Random();
			String rndstr = Long.toString(Math.abs(r.nextLong()), 36);
			String rndstr2 = Long.toString(Math.abs(r.nextLong()), 36);
			boolean write = true;
			for(int i=0;i<validFields.size();i++){
				HashMap<String, String> j = validFields.get(i);
				if(j.containsValue(rndstr)){
					write = false;
				}
				else if(j.containsValue(rndstr2)){
					write = false;
				}
			}
			if(write){
				HashMap<String, String> hm = new HashMap<String, String>();
				hm.put("username",rndstr);
				hm.put("password",rndstr2);
				hm.put("type","nurse");
				invalidFields.add(hm);
			}
		}
	}
	public void testRetrieve() {	
		setUpRetrieve();
		Storage test = new Storage();
		Random r1 = new Random();
		for(int i=0;i<100;i++){
			HashMap<String, String> input = validFields.get(r1.nextInt(validFieldsSize));
			List<HashMap<String,String> > ReturnList = new ArrayList<HashMap<String, String> >();
			ReturnList.add(input);
			List<HashMap<String,String> > ActualReturnList = new ArrayList<HashMap<String, String> >();
			ActualReturnList = test.Retrieve("cs320.user", input);
			assertEquals(ReturnList, ActualReturnList);	
		}
	}
	public void testRetrieve2() {
		setUpRetrieve();
		Storage test = new Storage();
		Random r1 = new Random();
		for(int i=0;i<100;i++){
			Map<String, String> input = invalidFields.get(r1.nextInt(invalidFieldsSize));
			List<HashMap<String,String> > ReturnList = new ArrayList<HashMap<String, String> >();
			List<HashMap<String,String> > ActualReturnList = new ArrayList<HashMap<String, String> >();
			ActualReturnList = test.Retrieve("cs320.user", input);
			assertEquals(ReturnList, ActualReturnList);
		}
	}
	
	public void testSave() {
		Storage test = new Storage();
		HashMap<String, String> testInput = new HashMap<String, String>();
		testInput.put("username", "jmolloy");
		testInput.put("password", "pass");
		testInput.put("type", "pharmacist");
		boolean Actual= test.Save("cs320.user", testInput);
		assertEquals(true, Actual);	
	}
	public void testStore() {
		Storage test = new Storage();
		boolean Actual= test.Store("1");
		assertEquals(true, Actual);	
	}
	public void testStore2() {
		Storage test = new Storage();
		boolean Actual= test.Store(null);
		assertEquals(false, Actual);	
	}

	/*
	public void testpushRecord(){
		Storage test = new Storage();
		assertEquals(false, true);
	}*/
	
	public void testsync(){
		Storage test = new Storage();
		boolean syncReturn = test.Sync("patient");

		boolean actual;
		if(test.dropReturn!=0 || test.createReturn!=0 || !syncReturn){
			actual = false;
		}
		else{
			actual = true;
		}
		assertEquals(true,actual );
		
	}
	


	public void testLog(){
		Log log = new Log();
		List<String> Usernames = new ArrayList<String>();
		List<String> Functions = new ArrayList<String>();
		List<List<String> > Fields = new ArrayList<List<String>>();
		Random r = new Random();
		for(int i=0;i<25;i++){
			String rndstr = Long.toString(Math.abs(r.nextLong()), 36);
			Usernames.add(rndstr);
		}
		for(int i=0;i<25;i++){
			String rndstr = Long.toString(Math.abs(r.nextLong()), 36);
			Functions.add(rndstr);
		}
		for(int i=0;i<25;i++){
			String rndstr = Long.toString(Math.abs(r.nextLong()), 36);
			String rndstr2 = Long.toString(Math.abs(r.nextLong()), 36);
			List<String> lst = new ArrayList<String>();
			lst.add(rndstr);
			lst.add(rndstr2);
			Fields.add(lst);
		}
		for(int i=0;i<1000;i++){
			boolean test = log.Write(Usernames.get(r.nextInt(10)), Functions.get(r.nextInt(10)), Fields.get(r.nextInt(10)));
			assertEquals(true, test);
		}
	}
	
	public void testLog2(){
		Log log = new Log();
		List<String> Usernames = new ArrayList<String>();
		List<String> Functions = new ArrayList<String>();
		List<List<String> > Fields = new ArrayList<List<String>>();
		Random r = new Random();
		for(int i=0;i<25;i++){
			String rndstr = Long.toString(Math.abs(r.nextLong()), 36);
			Usernames.add(rndstr);
		}
		for(int i=0;i<25;i++){
			String rndstr = Long.toString(Math.abs(r.nextLong()), 36);
			Functions.add(rndstr);
		}
		for(int i=0;i<25;i++){
			String rndstr = Long.toString(Math.abs(r.nextLong()), 36);
			String rndstr2 = Long.toString(Math.abs(r.nextLong()), 36);
			List<String> lst = new ArrayList<String>();
			lst.add(rndstr);
			lst.add(rndstr2);
			Fields.add(lst);
		}
		for(int i=0;i<1000;i++){
			boolean test = log.Write(null, Functions.get(r.nextInt(10)), Fields.get(r.nextInt(10)));
			assertEquals(false, test);
		}
		for(int i=0;i<1000;i++){
			boolean test = log.Write(Usernames.get(r.nextInt(10)), null, Fields.get(r.nextInt(10)));
			assertEquals(false, test);
		}
		for(int i=0;i<1000;i++){
			boolean test = log.Write(Usernames.get(r.nextInt(10)), Functions.get(r.nextInt(10)), null);
			assertEquals(false, test);
		}
		
	}
	
	protected boolean ServerInsert(Map<String, String> Input, String Relation) {
		boolean ServerConnected = false;
		Connection ServerConnection = null;
		Statement ServerStatement = null;
		String serverUrl = "jdbc:mysql://tbonci.thruhere.net:3306";
		String UserName = "darbour";
		String Password = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			ServerConnection = DriverManager.getConnection(serverUrl,
					UserName, Password);
			ServerConnected = true;
			ServerStatement = ServerConnection.createStatement();
		} catch (Exception e) {
			System.err.println("Failed to connect to Server DB\n" + e);
		}
		String Query = "INSERT INTO cs320." + Relation + "(";
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
	
public static Test suite() {
	TestSuite suite = new TestSuite("Test for default package");
	//$JUnit-BEGIN$
	suite.addTestSuite(StorageTest.class);
	//$JUnit-END$
	return suite;
}

}