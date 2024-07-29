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
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

public class HikingRecordDAOTest {
	
	/**
	 * mocking initializations
	 */
    private HikingRecordDAO recordDAO = new HikingRecordDAO();
	private HikingRecord recordMock = mock(HikingRecord.class);
    private Connection mockConnection = mock(Connection.class);
    private PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
    private PreparedStatement mockPreparedStatement2 = mock(PreparedStatement.class);
    private PreparedStatement mockPreparedStatement3 = mock(PreparedStatement.class);
    private ResultSet mockResultSet = mock(ResultSet.class);
    private ResultSet mockResultSet2 = mock(ResultSet.class);
    private ResultSet mockResultSet3 = mock(ResultSet.class);
   
  	List<HikingRecord> allRecords = Arrays.asList(new HikingRecord("Spectacle Lake", "Nadya", "2024-05-25", "10:15", "11:00"));

  	/**
	* JUnit test for true update in UpdateRecord() method from HikingRecordDAO class.
	*/ 
    @Test
    public void checkUpdateRecordTrue() {
    	
    	try {
	    	when(mockConnection.prepareStatement(HikingRecordDAO.UPDATE_RECORDS_SQL)).thenReturn(mockPreparedStatement);
	    	
			when(recordMock.getTrailName()).thenReturn("Spectacle Lake");
			when(recordMock.getUserName()).thenReturn("Nadya");
			when(recordMock.getDate()).thenReturn("2024-05-25");
			when(recordMock.getStartTime()).thenReturn("");
			when(recordMock.getEndTime()).thenReturn("");
			
			//set when thenReturn's for select all trails
			when(mockConnection.prepareStatement(TrailDAO.SELECT_ALL_TRAILS)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet.getString("name")).thenReturn("Spectacle Lake");
    		when(mockResultSet.getString("location")).thenReturn("Metchosin");
    		when(mockResultSet.getString("difficulty")).thenReturn("Easy");
    		when(mockResultSet.getInt("elevation")).thenReturn(15);
			
    		//set when thenReturn's for select all users
    		when(mockConnection.prepareStatement(UserDAO.SELECT_ALL_USERS)).thenReturn(mockPreparedStatement2);
    		when(mockPreparedStatement2.executeQuery()).thenReturn(mockResultSet2);
    		when(mockResultSet2.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet2.getString("name")).thenReturn("Nadya");
    		when(mockResultSet2.getString("birthday")).thenReturn("2003-10-05");

			when(mockPreparedStatement.executeUpdate()).thenReturn(1);
			
	    	assertTrue(recordDAO.updateRecord(recordMock, recordMock, mockConnection));

	    	Logger.info("checkUpdateRecordTrue Test Passed.");
	    	
    	} catch (Exception e) {
    		Logger.info("Exception Thrown in checkUpdateRecordTrue: {}", e.getMessage());
    	}
    }
    
    /**
	* JUnit test for false update in UpdateRecord() method from HikingRecordDAO class.
	*/ 
    @Test
    public void checkUpdateRecordFalse() {
    	
    	try {
	    	when(mockConnection.prepareStatement(HikingRecordDAO.UPDATE_RECORDS_SQL)).thenReturn(mockPreparedStatement);
	    	
			when(recordMock.getTrailName()).thenReturn("Spectacle Lake");
			when(recordMock.getUserName()).thenReturn("Nadya");
			when(recordMock.getDate()).thenReturn("2024-05-25");
			when(recordMock.getStartTime()).thenReturn("");
			when(recordMock.getEndTime()).thenReturn("");
			
			//set when thenReturn's for select all trails
			when(mockConnection.prepareStatement(TrailDAO.SELECT_ALL_TRAILS)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet.getString("name")).thenReturn("Spectacle Lake");
    		when(mockResultSet.getString("location")).thenReturn("Metchosin");
    		when(mockResultSet.getString("difficulty")).thenReturn("Easy");
    		when(mockResultSet.getInt("elevation")).thenReturn(15);
			
    		//set when thenReturn's for select all users
    		when(mockConnection.prepareStatement(UserDAO.SELECT_ALL_USERS)).thenReturn(mockPreparedStatement2);
    		when(mockPreparedStatement2.executeQuery()).thenReturn(mockResultSet2);
    		when(mockResultSet2.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet2.getString("name")).thenReturn("Nadya");
    		when(mockResultSet2.getString("birthday")).thenReturn("2003-10-05");

			when(mockPreparedStatement.executeUpdate()).thenReturn(0);
			
	    	assertFalse(recordDAO.updateRecord(recordMock, recordMock, mockConnection));

	    	Logger.info("checkUpdateRecordFalse Test Passed.");
	    	
    	} catch (Exception e) {
    		Logger.info("Exception Thrown in checkUpdateRecordFalse: {}", e.getMessage());
    	}
    }
    
    /**
	* JUnit test for selectAllRecords() method from HikingRecordDAO class.
	*   
	*/ 
    @Test
    public void checkSelectAllRecords() {
    	
    	try {
			when(mockConnection.prepareStatement(HikingRecordDAO.SELECT_ALL_RECORDS)).thenReturn(mockPreparedStatement);
			when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
			when(mockResultSet.next()).thenReturn(true).thenReturn(false);
			when(mockResultSet.getString("trail_name")).thenReturn("Spectacle Lake");
			when(mockResultSet.getString("user_name")).thenReturn("Nadya");
			when(mockResultSet.getString("hike_date")).thenReturn("2024-05-25");
			when(mockResultSet.getString("start_time")).thenReturn("10:15");
			when(mockResultSet.getString("end_time")).thenReturn("11:00");
	    	
			List<HikingRecord> records = recordDAO.selectAllRecords(mockConnection);
	    	assertEquals("Spectacle Lake", records.get(0).getTrailName());
	    	assertEquals("Nadya", records.get(0).getUserName());
	    	assertEquals("2024-05-25", records.get(0).getDate());
	    	assertEquals("10:15", records.get(0).getStartTime());
	    	assertEquals("11:00", records.get(0).getEndTime());
	    	assertEquals(1,records.size());
	    	
	    	Logger.info("checkSelectAllRecords Test Passed.");
	    	
    	} catch (SQLException e) {
    		Logger.error("Exception Thrown in checkSelectAllRecords: {}",e.getMessage());
		}
    }

    /**
	* JUnit test for valid delete in deleteRecord() method from HikingRecordDAO class.
	*/ 
    @Test
    public void checkDeleteRecordTrue() {
    	try {
	    	when(mockConnection.prepareStatement(HikingRecordDAO.DELETE_RECORDS_SQL)).thenReturn(mockPreparedStatement);
	
			//set when thenReturn's for select all trails
			when(mockConnection.prepareStatement(TrailDAO.SELECT_ALL_TRAILS)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet.getString("name")).thenReturn("Spectacle Lake");
    		when(mockResultSet.getString("location")).thenReturn("Metchosin");
    		when(mockResultSet.getString("difficulty")).thenReturn("Easy");
    		when(mockResultSet.getInt("elevation")).thenReturn(15);
			
    		//set when thenReturn's for select all users
    		when(mockConnection.prepareStatement(UserDAO.SELECT_ALL_USERS)).thenReturn(mockPreparedStatement2);
    		when(mockPreparedStatement2.executeQuery()).thenReturn(mockResultSet2);
    		when(mockResultSet2.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet2.getString("name")).thenReturn("Nadya");
    		when(mockResultSet2.getString("birthday")).thenReturn("2003-10-05");
    		
    		when(mockPreparedStatement.executeUpdate()).thenReturn(1);
    		
	    	assertTrue(recordDAO.deleteRecord("Spectacle Lake", "Nadya", "2024-05-25", mockConnection));
	    	
	    	//when(mockPreparedStatement.executeUpdate()).thenReturn(0);
	    	
	    	//assertFalse(recordDAO.deleteRecord("Spectacle Lake", "Nadya", "2024-05-25", mockConnection));
	    	
	    	Logger.info("checkDeleteRecordTrue Test Passed.");
	    	
    	} catch (Exception e) {
    		Logger.error("Exception Thrown in checkDeleteRecordTrue: {}",e.getMessage());
	    }
    }
    
    /**
	* JUnit test for invalid delete in deleteRecord() method from HikingRecordDAO class.
	*/ 
    @Test
    public void checkDeleteRecordFalse() {
    	try {
	    	when(mockConnection.prepareStatement(HikingRecordDAO.DELETE_RECORDS_SQL)).thenReturn(mockPreparedStatement);
	
			//set when thenReturn's for select all trails
			when(mockConnection.prepareStatement(TrailDAO.SELECT_ALL_TRAILS)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet.getString("name")).thenReturn("Spectacle Lake");
    		when(mockResultSet.getString("location")).thenReturn("Metchosin");
    		when(mockResultSet.getString("difficulty")).thenReturn("Easy");
    		when(mockResultSet.getInt("elevation")).thenReturn(15);
			
    		//set when thenReturn's for select all users
    		when(mockConnection.prepareStatement(UserDAO.SELECT_ALL_USERS)).thenReturn(mockPreparedStatement2);
    		when(mockPreparedStatement2.executeQuery()).thenReturn(mockResultSet2);
    		when(mockResultSet2.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet2.getString("name")).thenReturn("Nadya");
    		when(mockResultSet2.getString("birthday")).thenReturn("2003-10-05");
	    	
	    	when(mockPreparedStatement.executeUpdate()).thenReturn(0);
	    	
	    	assertFalse(recordDAO.deleteRecord("Spectacle Lake", "Nadya", "2024-05-25", mockConnection));
	    	
	    	Logger.info("checkDeleteRecordFalse Test Passed.");
	    	
    	} catch (Exception e) {
    		Logger.error("Exception Thrown in checkDeleteRecordFalse: {}",e.getMessage());
	    }
    }

    /**
	* JUnit test for SelectRecord() method from HikingRecordDAO class.
	*/ 
    @Test
    public void checkSelectRecordValid() {
    	
    	try {
			when(mockConnection.prepareStatement(HikingRecordDAO.SELECT_RECORD_SQL)).thenReturn(mockPreparedStatement3);
			
			//set when thenReturn's for select all trails
			when(mockConnection.prepareStatement(TrailDAO.SELECT_ALL_TRAILS)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet.getString("name")).thenReturn("Spectacle Lake");
    		when(mockResultSet.getString("location")).thenReturn("Metchosin");
    		when(mockResultSet.getString("difficulty")).thenReturn("Easy");
    		when(mockResultSet.getInt("elevation")).thenReturn(15);
    		
    		//set when thenReturn's for select all users
    		when(mockConnection.prepareStatement(UserDAO.SELECT_ALL_USERS)).thenReturn(mockPreparedStatement2);
    		when(mockPreparedStatement2.executeQuery()).thenReturn(mockResultSet2);
    		when(mockResultSet2.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet2.getString("name")).thenReturn("Nadya");
    		when(mockResultSet2.getString("birthday")).thenReturn("2003-10-05");
			
    		when(mockPreparedStatement3.executeQuery()).thenReturn(mockResultSet3);
			when(mockResultSet3.next()).thenReturn(true).thenReturn(false);
			when(mockResultSet3.getString("start_time")).thenReturn("");
			when(mockResultSet3.getString("end_time")).thenReturn("");
    		
    		//assertions portion
			HikingRecord record = recordDAO.selectRecord("Spectacle Lake", "Nadya", "2024-05-25", mockConnection);
	    	assertEquals("Spectacle Lake", record.getTrailName());
	    	assertEquals("Nadya", record.getUserName());
	    	assertEquals("2024-05-25", record.getDate());
	    	assertEquals("", record.getStartTime());
	    	assertEquals("", record.getEndTime());
	    	
	    	Logger.info("checkSelectRecordValid Test Passed.");
	    	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
	  /**
	  * JUnit test for getting exception in SelectRecord() method from HikingRecordDAO class.
	  */ 
      @Test
      public void checkSelectRecordException() {
      	
      	try {  	    	
  	    	when(mockConnection.prepareStatement(HikingRecordDAO.SELECT_RECORD_SQL)).thenThrow(SQLException.class);
  	    	
  	    	boolean thrown = false;
  	    	
  	    	try {
  	    		recordDAO.selectRecord("d98aef8yg8wy", "Nadya", "2024-05-25", mockConnection);
  	    	} catch (SQLException e) {
  	    		thrown = true;
  	    	}
  	    	assertTrue(thrown);
  	    	
  	    	Logger.info("checkSelectRecordException Test Passed.");
  	    	
      	} catch (Exception e) {
      		Logger.error("Exception Thrown in checkSelectRecordException: {}",e.getMessage());
      	}
      }
    
    /**
	* JUnit test for validateTime() method from HikingRecordDAO class.
	*/ 
    @Test
    public void checkValidateTime() {
    	
    	//upper and lower bounds
    	assertTrue(recordDAO.validateTime("12:59", "01:00"));
    	assertTrue(recordDAO.validateTime("05:45","01:00"));
    	//empty
    	assertTrue(recordDAO.validateTime("", ""));
    	
    	//past upper bound
    	assertFalse(recordDAO.validateTime("13:00", "01:00"));
    	assertFalse(recordDAO.validateTime("01:00", "12:60"));
    	assertFalse(recordDAO.validateTime("99:59", "13:00"));
    	//past lower bound
    	assertFalse(recordDAO.validateTime("00:00", "01:00"));
    	assertFalse(recordDAO.validateTime("00:01", "01:00"));
    	//invalid characters/format
    	assertFalse(recordDAO.validateTime("01:00", "9959"));
    	assertFalse(recordDAO.validateTime("01:00", "ab:ab"));
    	assertFalse(recordDAO.validateTime("01:00", "222:22"));
    	Logger.info("checkValidateTime Test Passed");
    }
    
    /**
	* JUnit test for validateDate() method from HikingRecordDAO class.
	*/ 
    @Test
    public void checkValidateDate() {
    	//upper and lower bounds
    	
    	try {
	    	assertTrue(recordDAO.validateDate("2024-01-01"));
	    	
	    	//invalid characters/format
	    	assertFalse(recordDAO.validateDate("1830-01-01"));
	    	assertFalse(recordDAO.validateDate("01-2024-02"));
	    	assertFalse(recordDAO.validateDate("dhdh-dh-dh"));
	    	assertFalse(recordDAO.validateDate("Nadya"));
	    	assertFalse(recordDAO.validateDate(""));
	    	
	    	Logger.info("checkValidateDate Test Passed");
	    	
    	} catch (ParseException e) {
    		Logger.error("Exception Thrown in checkValidateDate: {}", e.getMessage());
		}
    }
    
    /**
	* JUnit test for validateTrailName() method from HikingRecordDAO class.
	*/ 
    @Test
    public void checkValidateTrailName() {
    	try {
    		//set when thenReturn's for select all trails
			when(mockConnection.prepareStatement(TrailDAO.SELECT_ALL_TRAILS)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet.getString("name")).thenReturn("Spectacle Lake");
    		when(mockResultSet.getString("location")).thenReturn("Metchosin");
    		when(mockResultSet.getString("difficulty")).thenReturn("Easy");
    		when(mockResultSet.getInt("elevation")).thenReturn(15);
    		
			assertTrue(recordDAO.validateTrailName("Spectacle Lake", mockConnection));
			assertFalse(recordDAO.validateTrailName("Niagra Falls", mockConnection));
			
			Logger.info("checkValidateTrailName Test Passed");
			
    	} catch (SQLException e) {
    		Logger.error("Exception Thrown in checkValidateTrailName: {}", e.getMessage());
		}
    }
    
    /**
	* JUnit test for validateUserName() method from HikingRecordDAO class.
	*/ 
    @Test
    public void checkValidateUserName() {
    	try {			
    		//set when thenReturn's for select all users
    		when(mockConnection.prepareStatement(UserDAO.SELECT_ALL_USERS)).thenReturn(mockPreparedStatement2);
    		when(mockPreparedStatement2.executeQuery()).thenReturn(mockResultSet2);
    		when(mockResultSet2.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet2.getString("name")).thenReturn("Nadya");
    		when(mockResultSet2.getString("birthday")).thenReturn("2003-10-05");
    		
			assertTrue(recordDAO.validateUserName("Nadya", mockConnection));
			assertFalse(recordDAO.validateUserName("Adiela", mockConnection));
			
			Logger.info("checkValidateUserName Test Passed");
			
    	} catch (SQLException e) {
    		Logger.error("Exception Thrown in checkValidateUserName: {}", e.getMessage());
		}
    }
}
