package simple;

public class User {
	/**
	* Name of the user.
	*/
	private String name;
	/**
	* Birthday of the user.
	*/
	private String birthday;
	
	/**
	* Class constructor.
	* 
	* @param  name      Name of the user.
	* @param  birthday  Birthday of the user.
	*/
	public User(String name, String birthday) {
		super();
		this.name = name;
		this.birthday = birthday;
	}
	
	/**
	* Name of the user.
	* 
	* @return  The current name of this user. 
	*/
	public String getName() {
		return name;
	}
	
	/**
	* Name of the user.
	* 
	* @param  name  New value for this users name.
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	* Birthday of the user.
	* 
	* @return  The current birthday of this user. 
	*/
	public String getBirthday() {
		return birthday;
	}
	
	/**
	* Birthday of the user.
	* 
	* @param  birthday  New value for this users birthday.
	*/
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
