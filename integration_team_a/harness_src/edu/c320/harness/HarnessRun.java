package edu.c320.harness;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import edu.c320.harness.DBHandler.NoDBConnectivityException;

public class HarnessRun {

	private static DBHandler dbHandler = DBHandler.getInstance();

	private final char team = 'a';
	private final Timestamp startTime;
	private Timestamp endTime;
	private String revision;
	private int id;

	public HarnessRun() {
		this.startTime = new Timestamp((new Date()).getTime());
	}

	/**
	 * @return the team
	 */
	public char getTeam() {
		return team;
	}

	/**
	 * @return the startTime
	 */
	public Timestamp getStartTime() {
		return startTime;
	}

	/**
	 * Sets the harness run end time to current time
	 */
	public void setEndTime() {
		this.endTime = new Timestamp((new Date()).getTime());
	}

	/**
	 * @return the endTime
	 */
	public Timestamp getEndTime() {
		return endTime;
	}

	/**
	 * @param revision the revision to set
	 */
	public void setRevision(String revision) {
		this.revision = revision;
	}

	/**
	 * @return the revision
	 */
	public String getRevision() {
		return revision;
	}

	/**
	 * @return A hash map representation of the current HarnessRun
	 */
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("time_started", "'"+this.startTime.toString()+"'");
		map.put("team", Integer.toString((int)this.team));

		if(this.endTime != null)
			map.put("time_ended", "'"+this.endTime.toString()+"'");
		if(this.revision != null)
			map.put("revision", "'"+this.revision+"'");

		return map;
	}

	/**
	 * Adds the current harness run to the database and gets the generated id.
	 * @return <code>true</code> if added to the database successfully, else <code>false</code>
	 */
	public boolean save() {
		if(!dbHandler.connect())
			return false;

		try {
			dbHandler.insert("harnessrun", this.toHashMap());
			this.id = dbHandler.getLastGeneratedKey();
			return true;
		}
		catch (NoDBConnectivityException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update() {
		if(!dbHandler.connect())
			return false;
		
		HashMap<String, String> identifier = new HashMap<String, String>();
		identifier.put("harness_run_id", Integer.toString(this.id));
		
		try {
			dbHandler.update("harnessrun", this.toHashMap(), identifier);
			return true;
		}
		catch (NoDBConnectivityException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


}
