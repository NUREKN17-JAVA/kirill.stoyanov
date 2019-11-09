package ua.nure.kn.stoianov.usermanagement1.domain.db;

import java.util.Date;

import junit.framework.TestCase;
import ua.nure.kn.stoianov.usermanagement1.domain.User;

public class HsqldbUserDaoTest extends TestCase {
	HsqldbUserDao dao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqldbUserDao();
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

}
