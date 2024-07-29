package simple;

public class Trail {
	/**
	* Name of the trail.
	*/
	private String name;
	/**
	* General region of the trail.
	*/
	private String location;
	/**
	* Difficulty of the trail - Easy, Intermediate, or Difficult.
	*/
	private String difficulty;
	/**
	* Distance of the trail in kilometers.
	*/
	private double distance;
	/**
	* Elevation of the trail in meters.
	*/
	private int elevation;
	
	/**
	* Class constructor.
	* 
	* @param  name        Name of the trail.
	* @param  location    General region of the trail.
	* @param  difficulty  Difficulty of the trail - Easy, Intermediate, or Difficult.
	* @param  distance    Distance of the trail in kilometers.
	* @param  elevation   Elevation of the trail in meters.
	*/
	public Trail(String name, String location, String difficulty, double distance, int elevation) {
		super();
		this.name = name;
		this.location = location;
		this.difficulty = difficulty;
		this.distance = distance;
		this.elevation = elevation;
	}
	
	/**
	* Name of the trail.
	* 
	* @return  The current name of this trail. 
	*/
	public String getName() {
		return name;
	}
	
	/**
	* Name of the trail.
	* 
	* @param  trailName  New value for this trails name.
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	* General region of the trail.
	* 
	* @return  The current location of the trail. 
	*/
	public String getLocation() {
		return location;
	}
	
	/**
	* General region of the trail.
	* 
	* @param  location  New value for this trails location.
	*/
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	* Difficulty of the trail - Easy, Intermediate, or Difficult.
	* 
	* @return  The current difficulty of the trail. 
	*/
	public String getDifficulty() {
		return difficulty;
	}
	
	/**
	* Difficulty of the trail - Easy, Intermediate, or Difficult.
	* 
	* @param  difficulty  New value for this trails difficulty.
	*/
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	/**
	* Distance of the trail in kilometers.
	* 
	* @return  The current distance of the trail. 
	*/
	public double getDistance() {
		return distance;
	}
	
	/**
	* Distance of the trail in kilometers.
	* 
	* @param  distance  New value for this trails distance.
	*/
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	/**
	* Elevation of the trail in meters.
	* 
	* @return  The current elevation of the trail. 
	*/
	public int getElevation() {
		return elevation;
	}
	
	/**
	* Elevation of the trail in meters.
	* 
	* @param  elevation  New value for this trails elevation.
	*/
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	
}
