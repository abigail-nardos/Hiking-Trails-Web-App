package simple;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.tinylog.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TrailDAOTest {
    
	/**
	 * mocking initializations
	 */
    private TrailDAO trailDAO = new TrailDAO();
    private Connection mockConnection = mock(Connection.class);
    private PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
    private ResultSet mockResultSet = mock(ResultSet.class);
    
    /**
	* JUnit test for selectAllTrails() method in TrailDAO class.
	*/ 
    @Test
    public void checkSelectAllTrails() {
    	try {
    		when(mockConnection.prepareStatement(TrailDAO.SELECT_ALL_TRAILS)).thenReturn(mockPreparedStatement);
    		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    		when(mockResultSet.next()).thenReturn(true).thenReturn(false);
    		when(mockResultSet.getString("name")).thenReturn("Tower Point");
    		when(mockResultSet.getString("location")).thenReturn("Metchosin");
    		when(mockResultSet.getString("difficulty")).thenReturn("Easy");
    		when(mockResultSet.getInt("elevation")).thenReturn(15);
    		
    		List<Trail> trails = trailDAO.selectAllTrails(mockConnection);
	    	assertEquals("Tower Point", trails.get(0).getName());
	    	assertEquals("Metchosin", trails.get(0).getLocation());
	    	assertEquals("Easy", trails.get(0).getDifficulty());
	    	assertEquals(15, trails.get(0).getElevation());
	    	assertEquals(1,trails.size());
	    	
	    	Logger.info("checkSelectAllTrails Test Passed");
    	
	    } catch (Exception e) {
	    	Logger.error("Exception Thrown in checkSelectAllTrails: {}",e.getMessage());
		}
    }
    

}
