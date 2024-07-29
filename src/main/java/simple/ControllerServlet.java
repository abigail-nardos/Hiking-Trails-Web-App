package simple;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.tinylog.Logger;

@WebServlet("/")

public class ControllerServlet extends HttpServlet {
	/**
	* Serial Version initialization. 
	*/
	public static final long serialVersionUID = 1L;
	/**
	* Operation parameter initialization. 
	*/
	public static final String OPERATION_PARAM = null;
	/**
	* Filter parameter initialization. 
	*/
	public static final String FILTER_PARAM = null;
	/**
	* TrailDAO initialization.  
	*/
	public TrailDAO trailDAO = null;
	/**
	* UserDAO initialization.   
	*/
	public UserDAO userDAO = null;
	/**
	* HikingRecordDAO initialization.  
	*/
	public HikingRecordDAO hikingRecordDAO = null;
	/**
	* Initialization of hiking record before being updated in the UI.
	*/
	public HikingRecord prevRecord;
	/**
	* Initialization of the user before being updated in the UI.   
	*/
	public User prevUser;
	/**
	* Initialization of DBConnection to the database.   
	*/
	protected DBConnection DBcon;
	/**
	* Easter egg message.
	*/
	public String secertMessage = "";
	
	
	/**
	* Class constructor.
	* Initializes the DAO's.
	*/
	public ControllerServlet() {
		trailDAO = new TrailDAO();
		userDAO = new UserDAO();
		hikingRecordDAO = new HikingRecordDAO();
		DBcon = new DBConnection();
	}
	
	/**
	* doPost method to connect request and response messages between DAO and .jsp files.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Logger.info("doPost Method for the ControllerServlet");
		
		this.doGet(request, response);
	}
	
	/**
	* doGet method to connect request and response messages between DAO and .jsp files.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Logger.info("doGet Method for the ControllerServlet");
		
		String action = request.getServletPath();

		//Select which method to call based on the action
		switch (action) {
		case "/insert-user":
			insertUser(request,response);
			break;
		case "/insert-hiking-record":
			insertHikingRecord(request,response);
			break;
		case "/delete-user":
			deleteUser(request,response);
			break;
		case "/delete-hiking-record":
			deleteHikingRecord(request,response);
			break;
		case "/edit-user":
			showUserEditForm(request,response);
			break;
		case "/edit-hiking-record":
			showRecordEditForm(request,response);
			break;
		case "/update-user":
			updateUser(request,response);
			break;
		case "/update-hiking-record":
			updateHikingRecord(request,response);
			break;
		default:
			Lists(request, response);
			break;
		}
        
	}
	
	/**
	* Sends request to user-form.jsp to display with the previous user details.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void showUserEditForm(HttpServletRequest request, HttpServletResponse response) {
		Logger.info("showUserEditForm Method for the ControllerServlet");
		
		try {
			//initialize a connection
			Connection con = DBcon.getConnection(); 
			
			try {
				String name = request.getParameter("name");
				prevUser = userDAO.selectUser(name, con);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/user-form.jsp");
				request.setAttribute("user", prevUser);
				dispatcher.forward(request, response);
				
				//close connection
				DBcon.closeConnection(con);
				
			} catch (Exception e) {
				displayErrorPage(request,response, e);
				
			} finally {
				//close connection
				DBcon.closeConnection(con);
			}
			
		} catch (Exception e) {
			//Error creating a connection
			displayErrorPage(request,response, e);
		}
	}
	
	/**
	* Sends request to records-form.jsp to display with the previous hiking records details.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void showRecordEditForm(HttpServletRequest request, HttpServletResponse response) {
		Logger.info("showRecordEditForm Method for the ControllerServlet");
		
		try {
			//initialize connection
			Connection con = DBcon.getConnection();
			
			try {
				//get all the values from the trails.jsp file
				String trail_name = request.getParameter("trail_name");
				String user_name = request.getParameter("user_name");
				String date = request.getParameter("date");
				String start_time = request.getParameter("start_time");
				String end_time = request.getParameter("end_time");
				
				prevRecord = hikingRecordDAO.selectRecord(trail_name, user_name, date, con);
				request.setAttribute("record", prevRecord);
				
				//remove the current trail so it's not duplicated in the selection
				List<Trail> listTrail = trailDAO.selectAllTrails(con);
				for (int i=0; i<listTrail.size(); i++) {
					
					if (listTrail.get(i).getName().equals(trail_name)) {
						listTrail.remove(i);
					}
				}
	
				//remove the current user so it's not duplicated in the selection
				List<User> listUser = userDAO.selectAllUsers(con);
				for (int i=0; i<listUser.size(); i++) {
					
					if (listUser.get(i).getName().equals(user_name)) {
						listUser.remove(i);
					}
				}
				
				//set all the attributes
				request.setAttribute("listTrail", listTrail);
				request.setAttribute("listUser", listUser);
				request.setAttribute("trail_name", trail_name);
				request.setAttribute("user_name", user_name);
				request.setAttribute("date", date);
				request.setAttribute("start_time", start_time);
				request.setAttribute("end_time", end_time);
				
				//dispatch the values
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/record-form.jsp");
				dispatcher.forward(request, response);
				
			} catch (IllegalArgumentException e) {
				//Illegal Argument - output an error form
				outputErrorPage(request,response, e);
				
			} catch (Exception e) {
				displayErrorPage(request,response, e);
				
			} finally {
				//close connection
				DBcon.closeConnection(con);
			}
			
		} catch (Exception e) {
			//Error creating a connection
			displayErrorPage(request,response, e);
		}
	}
	
	/**
	* Sends lists of hiking trails, hiking records, and users to the trails.jsp file to be displayed on the UI.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void Lists(HttpServletRequest request, HttpServletResponse response) {
		Logger.info("Lists Method for the ControllerServlet");
		
		try {
			//initialize a connection
			Connection con = DBcon.getConnection();
			
			try {
				List<User> listUser = userDAO.selectAllUsers(con);
				request.setAttribute("listUser", listUser);
				
				List<HikingRecord> listRecord = hikingRecordDAO.selectAllRecords(con);
				request.setAttribute("listRecord", listRecord);
				
				List<Trail> listTrail = trailDAO.selectAllTrails(con);
				request.setAttribute("listTrail", listTrail);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/trails.jsp"); 
				dispatcher.forward(request, response);
				
			} catch (Exception e) {
				displayErrorPage(request,response, e);
				
			} finally {
				//close connection
				DBcon.closeConnection(con);
			}
			
		} catch (Exception e) {
			//Error creating a connection
			displayErrorPage(request,response, e);
		}
	}
	
	/**
	* Gets response from UI, inserts the given values into the users table in the database, and dispatches the updated list.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void insertUser(HttpServletRequest request, HttpServletResponse response) {
		Logger.info("insertUser Method for the ControllerServlet");
		
		try {
			//initialize a connection
			Connection con = DBcon.getConnection();
			Logger.info("hello");
			try {
				String name = request.getParameter("name");
				String birthday = request.getParameter("birthday");
				
				User newUser = new User(name, birthday);
				userDAO.insertUser(newUser, con);
				
				//EASTER EGG
				easterEgg(request,response,name,birthday);

				response.sendRedirect("user_list");
			
			} catch (IllegalArgumentException e) {
				//Illegal Argument - output an error form
				outputErrorPage(request,response, e);
				
			} catch (Exception e) {
				displayErrorPage(request,response, e);
				
			} finally {
				//close connection
				DBcon.closeConnection(con);
			}
			
		} catch (Exception e) {
			//Error creating a connection
			displayErrorPage(request,response, e);
		}
	}
	
	/**
	* Gets response from UI, inserts the given values into the records table in the database, and dispatches the updated list.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void insertHikingRecord(HttpServletRequest request, HttpServletResponse response) {
		Logger.info("insertHikingRecord Method for the ControllerServlet");
		
		try {
			//initialize connection
			Connection con = DBcon.getConnection();
			
			try {
				String trail_name = request.getParameter("trail_name");
				String user_name = request.getParameter("user_name");
				String date = request.getParameter("date");
				String start_time = request.getParameter("start_time");
				String end_time = request.getParameter("end_time");
				
				HikingRecord newRecord = new HikingRecord(trail_name, user_name, date, start_time, end_time);
				hikingRecordDAO.insertRecord(newRecord, con);
				response.sendRedirect("record_list");
			
			} catch (IllegalArgumentException e) {
				//Illegal Argument - output error form
				outputErrorPage(request,response, e);
				
			} catch (Exception e) {
				displayErrorPage(request,response, e);
				
			} finally {
				//close connection
				DBcon.closeConnection(con);
			}
			
		} catch (Exception e) {
			//Error creating a connection
			displayErrorPage(request,response, e);
		}
	}
	
	/**
	* Gets response from UI, deletes the given user from the table in the database, and dispatches the updated list.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		Logger.info("deleteUser Method for the ControllerServlet");
		
		try {
			//initialize connection
			Connection con = DBcon.getConnection();
			
			try {
				String name = request.getParameter("name");
				userDAO.deleteUser(name, con);
				response.sendRedirect("user_list");
			
			} catch (Exception e) {
				displayErrorPage(request,response, e);
				
			} finally {
				//close connection
				DBcon.closeConnection(con);
			}
			
		} catch (Exception e) {
			//Error creating a connection
			displayErrorPage(request,response, e);
		}
	}
	
	/**
	* Gets response from UI, deletes the given hiking record from the table in the database, and dispatches the updated list.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void deleteHikingRecord(HttpServletRequest request, HttpServletResponse response) {
		Logger.info("deleteHikingRecord Method for the ControllerServlet");
		
		try {
			//initialize connection
			Connection con = DBcon.getConnection();
			
			try {
				String trail_name = request.getParameter("trail_name");
				String user_name = request.getParameter("user_name");
				String date = request.getParameter("date");
				hikingRecordDAO.deleteRecord(trail_name, user_name, date, con);
				response.sendRedirect("record_list");
			
			} catch (Exception e) {
				displayErrorPage(request,response, e);
				
			} finally {
				//close connection
				DBcon.closeConnection(con);
			}
			
		} catch (Exception e) {
			//Error creating a connection
			displayErrorPage(request,response, e);
		}
	}
	
	/**
	* Gets response from the UI, updates the indicated row from the previous user to the current user, and sends the updated list.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void updateUser(HttpServletRequest request, HttpServletResponse response) {
		Logger.info("updateUser Method for the ControllerServlet");
		
		try {
			//initialize connection
			Connection con = DBcon.getConnection();
			
			try {
				String name = request.getParameter("name");
				String birthday = request.getParameter("birthday");
				
				User curUser= new User(name, birthday);
				userDAO.updateUser(prevUser, curUser, con);
				
				//EASTER EGG
				easterEgg(request,response,name,birthday);
				
				response.sendRedirect("user_list");
			
			} catch (IllegalArgumentException e) {
				//Illegal Argument - output error form
				outputErrorPage(request,response, e);
				
			} catch (Exception e) {
				displayErrorPage(request,response, e);
				
			} finally {
				//close connection
				DBcon.closeConnection(con);
			}
			
		} catch (Exception e) {
			//Error creating a connection
			displayErrorPage(request,response, e);
		}
	}
	
	/**
	* Gets response from the UI, updates the indicated row from the previous hiking record to the current hiking record, and sends the updated list.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void updateHikingRecord(HttpServletRequest request, HttpServletResponse response)  {
		Logger.info("updateHikingRecord Method for the ControllerServlet");
		
		try {
			//initialize connection
			Connection con = DBcon.getConnection();
			
			try {
				String trail_name = request.getParameter("trail_name");
				String user_name = request.getParameter("user_name");
				String date = request.getParameter("date");
				String start_time = request.getParameter("start_time");
				String end_time = request.getParameter("end_time");
				
				HikingRecord curRecord = new HikingRecord(trail_name, user_name, date, start_time, end_time);
				hikingRecordDAO.updateRecord(prevRecord,curRecord, con);
				
				response.sendRedirect("record_list");
			
			} catch (IllegalArgumentException e) {
				//Illegal Argument - output error form
				outputErrorPage(request,response, e);
				
			} catch (Exception e) {
				displayErrorPage(request,response, e);
				
			} finally {
				//close connection
				DBcon.closeConnection(con);
			}
			
		} catch (Exception e) {
			//Error creating a connection
			displayErrorPage(request,response, e);
		}
	}
	
	/**
	* Outputs an error page in response to an Illegal Argument Exception.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void outputErrorPage(HttpServletRequest request, HttpServletResponse response, Exception e) {
		Logger.info("Illegal Argument Exception: {}", e.getMessage());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/error.jsp");
		request.setAttribute("errorMessage", e.getMessage());
		
		try {
			dispatcher.forward(request, response);
			
		} catch (ServletException | IOException e1) {
			Logger.info("Error Dispatching outputErrorPage: {}", e1.getMessage());
		}
	}
	
	/**
	* Outputs an error page in response to any exception caught by the servlet.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	*/
	public void displayErrorPage(HttpServletRequest request, HttpServletResponse response, Exception e) {
		Logger.info("An Error Occurred: {}", e.getMessage());
		
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/display-error.jsp");
			dispatcher.forward(request, response);
			
		} catch (ServletException | IOException e1) {
			Logger.error("Error Dispatching displayErrorPage: {}", e1.getMessage());
		} 
			
	}
	
	/**
	* Displays an easter egg message from specific user input.
	* 
	* @param  request   request sent to the UI.
	* @param  response  response received from the UI.
	 * @throws IOException 
	 * @throws ServletException 
	*/
	public void easterEgg(HttpServletRequest request, HttpServletResponse response, String name, String birthday) throws ServletException, IOException {
		//check name easter egg
		if (name.toLowerCase().equals("nadya")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/message.jsp");
			request.setAttribute("secretMessage", "WE HAVE THE SAME NAME! -Author");
			dispatcher.forward(request, response);
		}
		
		//check birthday easter egg
		if (!birthday.equals("") && birthday.matches("^([0-9]{4})-([0-9]{2})-([0-9]{2})$")) {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate curDate = LocalDate.parse(birthday, dateFormat);
			LocalDate myBirthday = LocalDate.of(2003,10,05);
			
			if (curDate.equals(myBirthday)) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/message.jsp");
				request.setAttribute("secretMessage", "Birthday twins! -Author ┗(｀O ´)┛");
				dispatcher.forward(request, response);
			}
		}
	}

}

