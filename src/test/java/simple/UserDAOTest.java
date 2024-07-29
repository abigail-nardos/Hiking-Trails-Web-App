package simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

public class UserDAOTest {
	
	/**
	 * mocking initializations
	 */
	private User userMock = mock(User.class);
    private UserDAO userDAO = new UserDAO();
    private Connection mockConnection = mock(Connection.class);
    private PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
    private PreparedStatement mockPreparedStatement2 = mock(PreparedStatement.class);
    private ResultSet mockResultSet = mock(ResultSet.class);
    
	/**
	* JUnit test for updateUser() method in UserDAO class.
	*/ 
    @Test
    public void checkUpdateUser() throws SQLException {
    	try {
    		when(mockConnection.prepareStatement(UserDAO.UPDATE_USERS_SQL)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeUpdate()).thenReturn(1);
    		when(userMock.getName()).thenReturn("Nadya");
    		when(userMock.getBirthday()).thenReturn("");
    		
    		when(mockConnection.prepareStatement(UserDAO.SELECT_ALL_USERS)).thenReturn(mockPreparedStatement2);
    		when(mockPreparedStatement2.executeQuery()).thenReturn(mockResultSet);
    		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet.getString("name")).thenReturn("Adiela");
    		when(mockResultSet.getString("birthday")).thenReturn("2003-10-05");
    		
	    	assertTrue(userDAO.updateUser(userMock, userMock, mockConnection));
	    	
	    	when(mockPreparedStatement.executeUpdate()).thenReturn(0);
	    	
	    	assertFalse(userDAO.updateUser(userMock, userMock, mockConnection));
	    	
	    	Logger.info("checkUpdateUserTrue Test Passed");
    	
    	} catch (Exception e) {
    		Logger.error("Exception Thrown in checkUpdateUser: {}",e.getMessage());
    	}
    
    }
    
    /**
	* JUnit test for selectAllUsers() method in UserDAO class.
	*/
    @Test
    public void checkSelectAllUsers() {
    	try {
    		when(mockConnection.prepareStatement(UserDAO.SELECT_ALL_USERS)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet.getString("name")).thenReturn("Nadya");
    		when(mockResultSet.getString("birthday")).thenReturn("2003-10-05");
    		
    		List<User> users = userDAO.selectAllUsers(mockConnection);
	    	assertEquals("Nadya", users.get(0).getName());
	    	assertEquals("2003-10-05", users.get(0).getBirthday());
	    	assertEquals(1,users.size());
	    	
	    	Logger.info("checkSelectAllUsers Test Passed");
    	
	    } catch (Exception e) {
			Logger.error("Exception Thrown in checkSelectAllUsers: {}",e.getMessage());
		}
    }
    
    /**
	* JUnit test for deleteUser() method in UserDAO class.
	*/
    @Test
    public void checkDeleteUser() {
    	try {
    		when(mockConnection.prepareStatement(UserDAO.DELETE_USERS_SQL)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeUpdate()).thenReturn(1);
	    	//Try to delete an existing user
	    	assertTrue(userDAO.deleteUser("Nadya", mockConnection));
	    	
	    	when(mockPreparedStatement.executeUpdate()).thenReturn(0);
    		
	    	assertFalse(userDAO.deleteUser("d98aef8yg8wy", mockConnection));
	    	
	    	Logger.info("checkDeleteUser Test Passed");
    	
	    } catch (Exception e) {
			Logger.error("Exception Thrown in checkDeleteUserTrue: {}",e.getMessage());
		}
    }
    
    /**
	* JUnit test for selectUser() method in UserDAO class.
	*/
    @Test
    public void checkSelectUser() {
    	try {
    		when(mockConnection.prepareStatement(UserDAO.SELECT_USER_BY_NAME_SQL)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet.getString("name")).thenReturn("Nadya");
    		when(mockResultSet.getString("birthday")).thenReturn("2003-10-05");
    		
    		User user = userDAO.selectUser("Nadya", mockConnection);
	    	assertEquals("Nadya", user.getName());
	    	assertEquals("2003-10-05", user.getBirthday());
	    	
	    	when(mockConnection.prepareStatement(UserDAO.SELECT_USER_BY_NAME_SQL)).thenThrow(SQLException.class);
	    	 
	    	boolean thrown = false;
	    	
	    	try {
	    		userDAO.selectUser("d98aef8yg8wy", mockConnection);
	    	} catch (SQLException e) {
	    		thrown = true;
	    	}
	    	assertTrue(thrown);
	    	
	    	Logger.info("checkSelectUser Test Passed");
    	
	    } catch (Exception e) {
			Logger.error("Exception Thrown in checkSelectUser: {}",e.getMessage());
		}
    }
    
    /**
   	* JUnit test for validateName() method in UserDAO class.
   	*/
    @Test
    public void checkValidateName() {
    	assertTrue(userDAO.validateName("Nadya"));
    	assertFalse(userDAO.validateName("Na7dya"));
    	assertFalse(userDAO.validateName("Nadyaaaaaaaaaaaaaaaaaaaaa"));
    	assertFalse(userDAO.validateName(""));
    	
    	Logger.info("checkValidateName Test Passed");
    }
    
    /**
   	* JUnit test for validateBirthday() method in UserDAO class.
   	*/
    @Test
    public void checkValidateBirthday() {
    	
    	assertTrue(userDAO.validateBirthday("2018-06-06"));
    	
    	assertFalse(userDAO.validateBirthday("2018-06-08"));
    	assertFalse(userDAO.validateBirthday("01-2024-02"));
    	assertFalse(userDAO.validateBirthday("dhdh-dh-dh"));
    	assertFalse(userDAO.validateBirthday("Nadya"));
    	
    	Logger.info("checkValidateBirthday Test Passed");
    }
    
}
