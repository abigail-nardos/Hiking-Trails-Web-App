package simple;


import org.tinylog.Logger;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

public class ControllerServletTest {

	private DBConnection mockDBcon = mock(DBConnection.class);
	private ControllerServlet spyServlet = spy(new ControllerServlet());
	private HttpServletRequest request = mock(HttpServletRequest.class); 
	private HttpServletResponse response = mock(HttpServletResponse.class); 
	private Connection mockConnection = mock(Connection.class);
	
	/**
	 * Test insertUserTest() method called from doGet().
	 */
	@Test
    public void insertUserTest() {
		try {
			when(mockDBcon.getConnection()).thenReturn(mockConnection);

			spyServlet.doGet(request, response);
			when(request.getServletPath()).thenReturn("/insert-user");
			
			//verify insertUser is called
			verify(spyServlet).insertUser(request,response);
			
			when(request.getParameter("name")).thenReturn("Nadya");
			when(request.getParameter("birthday")).thenReturn("");
			
			//verify insertUser() is called on userDAO and redirect sent to user_list
			verify(spyServlet.userDAO).insertUser(new User("Nadya", ""), mockConnection);
			verify(response).sendRedirect("user_list");
			
			Logger.info("insertUserTest for ControllerServlet Passed");
			
		} catch (Exception e) {
			Logger.info("Error thrown in insertUserTest for ControllerServletTest: {}", e.getMessage());
		}
    }
	
	/**
	 * Test insertHikingRecordTest() method called from doGet().
	 */
	//@Test
    public void insertHikingRecordTest() {
		try {
			
			when(mockDBcon.getConnection()).thenReturn(mockConnection);
			
			spyServlet.doGet(request, response);
			when(request.getServletPath()).thenReturn("/insert-hiking-record");
			
			//verify insertUser is called
			verify(spyServlet).insertHikingRecord(request,response);
			
			when(request.getParameter("trail_name")).thenReturn("China Beach");
			when(request.getParameter("user_name")).thenReturn("Nadya");
			when(request.getParameter("date")).thenReturn("2024-06-04");
			when(request.getParameter("start_time")).thenReturn("");
			when(request.getParameter("end_time")).thenReturn("");
			
			//verify insertUser() is called on userDAO and redirect sent to user_list
			verify(spyServlet.hikingRecordDAO).insertRecord(new HikingRecord("China Beach", "Nadya", "2024-06-04", "", ""), mockConnection);
			verify(response).sendRedirect("record_list");

			Logger.info("insertHikingRecordTest for ControllerServlet Passed");
			
		} catch (Exception e) {
			Logger.info("Error thrown in insertHikingRecordTest for ControllerServletTest: {}", e.getMessage());
		}
      
    }
	
	/**
	 * Test deleteUserTest() method called from doGet().
	 */
	//@Test
    public void deleteUserTest() {
		try {
			when(mockDBcon.getConnection()).thenReturn(mockConnection);
			
			spyServlet.doGet(request, response);
			when(request.getServletPath()).thenReturn("/delete-user");
			
			verify(spyServlet).insertUser(request,response);
			
			Logger.info("deleteUserTest for ControllerServlet Passed");
			
		} catch (Exception e) {
			Logger.info("Error thrown in deleteUserTest for ControllerServletTest: {}", e.getMessage());
		}
      
    }
	
	/**
	 * Test deleteHikingRecordTest() method called from doGet().
	 */
	//@Test
    public void deleteHikingRecordTest() {
		try {
			when(mockDBcon.getConnection()).thenReturn(mockConnection);
			
			spyServlet.doGet(request, response);
			when(request.getServletPath()).thenReturn("/delete-hiking-record");
			
			verify(spyServlet).insertUser(request,response);
			
			Logger.info("deleteHikingRecordTest for ControllerServlet Passed");
			
		} catch (Exception e) {
			Logger.info("Error thrown in deleteHikingRecordTest for ControllerServletTest: {}", e.getMessage());
		}
      
    }
	
	/**
	 * Test showUserEditForm() method called from doGet().
	 */
	//@Test
    public void showUserEditFormTest() {
		try {
			when(mockDBcon.getConnection()).thenReturn(mockConnection);
			
			spyServlet.doGet(request, response);
			when(request.getServletPath()).thenReturn("/edit-user");
			
			verify(spyServlet).insertUser(request,response);
			
			Logger.info("showUserEditFormTest for ControllerServlet Passed");
			
		} catch (Exception e) {
			Logger.info("Error thrown in showUserEditFormTest for ControllerServletTest: {}", e.getMessage());
		}
      
    }

	/**
	 * Test showRecordEditForm() method called from doGet().
	 */
	//@Test
    public void showRecordEditFormTest() {
		try {
			when(mockDBcon.getConnection()).thenReturn(mockConnection);
			
			spyServlet.doGet(request, response);
			when(request.getServletPath()).thenReturn("/edit-hiking-record");
			
			verify(spyServlet).insertUser(request,response);
			
			Logger.info("showRecordEditFormTest for ControllerServlet Passed");
			
		} catch (Exception e) {
			Logger.info("Error thrown in showRecordEditFormTest for ControllerServletTest: {}", e.getMessage());
		}
      
    }
	
	/**
	 * Test updateUser() method called from doGet().
	 */
	//@Test
    public void updateUserTest() {
		try {
			when(mockDBcon.getConnection()).thenReturn(mockConnection);
			
			spyServlet.doGet(request, response);
			when(request.getServletPath()).thenReturn("/update-user");
			
			verify(spyServlet).insertUser(request,response);
			
			Logger.info("updateUserTest for ControllerServlet Passed");
			
		} catch (Exception e) {
			Logger.info("Error thrown in updateUserTest for ControllerServletTest: {}", e.getMessage());
		}
      
    }
	
	/**
	 * Test updateHikingRecord() method called from doGet().
	 */
	//@Test
    public void updateHikingRecordTest() {
		try {
			when(mockDBcon.getConnection()).thenReturn(mockConnection);
			
			spyServlet.doGet(request, response);
			when(request.getServletPath()).thenReturn("/update-hiking-record");
			
			verify(spyServlet).insertUser(request,response);
			
			Logger.info("updateHikingRecordTest for ControllerServlet Passed");
			
		} catch (Exception e) {
			Logger.info("Error thrown in updateHikingRecordTest for ControllerServletTest: {}", e.getMessage());
		}
      
    }
	
}

