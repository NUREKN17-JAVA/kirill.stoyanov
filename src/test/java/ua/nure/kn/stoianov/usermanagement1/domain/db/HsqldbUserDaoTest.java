package ua.nure.kn.stoianov.usermanagement1.domain.db;

import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import junit.framework.TestCase;
import ua.nure.kn.stoianov.usermanagement1.domain.User;

public class HsqldbUserDaoTest extends DatabaseTestCase {
	
	
	private HsqldbUserDao dao;
	private ConnectionFactory connectionFactory;
	
	private static final long TEST_ID = 1000;
    private static final String FIRST_NAME = "Bill";
    private static final String LAST_NAME = "Gates";
	private static final Long ID = 1000L;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao(connectionFactory);
	}

	public void testCreate() {
		try {
			User user = new User();
			user.setFirstName("John");
			user.setLastName("Doe");
			user.setDateOfBirth(new Date());
			assertNull(user.getId());
			user = dao.create(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
		
	}

	public void testFindAll() {
		try {
			Collection collection =  dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
		} catch (DatabaseException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	public void testFindID() {
        long id = TEST_ID;
        try {
            User user = dao.find(id);

            assertNotNull(user);

            long userId = user.getId();
            assertEquals(id, userId);
        } catch (DatabaseException e) {
            fail(e.getMessage());
        }

    }
	
	 public void testDeleteUser() {
	        User testUser = createUser();
	        int expectedBeforeSize = 2;
	        int expectedAfterSize = 1;
	        try {
	            int beforeSize = dao.findAll().size();
	            dao.delete(testUser);
	            int afterSize = dao.findAll().size();

	            assertEquals(expectedBeforeSize, beforeSize);
	            assertEquals(expectedAfterSize, afterSize);
	        } catch (DatabaseException e) {
	            fail(e.getMessage());
	        }
	    }
	 
	 
	 public void testUpdateUser() {
	        User testUser = createUser();
	        try {
	            dao.update(testUser);
	            User updatedUser = dao.find(TEST_ID);

	            assertEquals(FIRST_NAME, updatedUser.getFirstName());
	            assertEquals(LAST_NAME, updatedUser.getLastName());
	        } catch (DatabaseException e) {
	            fail(e.getMessage());
	        }
	    }
	
	 private User createUser() {
	        User user = new User();
	        user.setId(TEST_ID);
	        user.setFirstName(FIRST_NAME);
	        user.setLastName(LAST_NAME);
	        user.setDateOfBirth(new Date());
	        return user;
	    }
	 
	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/usermanagement1", "sa", "");
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
