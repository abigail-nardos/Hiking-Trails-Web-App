package simple;

public class HikingRecord {
	/**
	* Name of the trail.   
	*/
	private String trailName;
	/**
	* Name of the user.
	*/
	private String userName;
	/**
	* Date of the hike. 
	*/
	private String date;
	/**
	* Time hike started. 
	*/
	private String startTime;
	/**
	* Time hike ended. 
	*/
	private String endTime;
	
	/**
	* Class constructor.
	* 
	* @param  trailName  Name of the trail.
	* @param  userName   Name of the user.
	* @param  date       Date of the hike.
	* @param  startTime  Time hike started.
	* @param  endTime    Time hike ended.
	*/
	public HikingRecord(String trailName, String userName, String date, String startTime, String endTime) {
		super();
		this.trailName = trailName;
		this.userName = userName;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	/**
	* Name of the trail.
	* 
	* @return  The current name of this records hiking trail. 
	*/
	public String getTrailName() {
		return trailName;
	}
	
	/**
	* Name of the trail.
	* 
	* @param  trailName  New value for this records trail name.
	*/
	public void setTrailName(String trailName) {
		this.trailName = trailName;
	}
	
	/**
	* Name of the user.
	* 
	* @return  The current name of this records user. 
	*/
	public String getUserName() {
		return userName;
	}
	
	/**
	* Name of the user.
	* 
	* @param  userName  New value for the user's name.
	*/
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	* Date of the hike.
	* 
	* @return  The current date of this records hike. 
	*/
	public String getDate() {
		return date;
	}
	
	/**
	* Date of the hike.
	* 
	* @param  date  New value for the date of the hike.
	*/
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	* Time hike started.
	* 
	* @return  the time the hike started.
	*/
	public String getStartTime() {
		return startTime;
	}
	
	/**
	* Time hike started.
	* 
	* @param  startTime  New value for the time the hike started.
	*/
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	* Time hike ended.
	* 
	* @return  the time the hike ended.
	*/
	public String getEndTime() {
		return endTime;
	}
	
	/**
	* Time hike ended.
	* 
	* @param  endTime  New value for the time the hike ended.
	*/
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
