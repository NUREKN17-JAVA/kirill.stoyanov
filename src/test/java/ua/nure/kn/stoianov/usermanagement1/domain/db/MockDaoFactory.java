package ua.nure.kn.stoianov.usermanagement1.domain.db;

import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DaoFactory {

	private Mock mockUserDao;
	
	public MockDaoFactory() {
		mockUserDao = new Mock(UserDao.class);
	}
	
	public Mock getMockUserDao() {
		return mockUserDao;
	}
	
	public UserDao getUserDao() {
		return (UserDao) mockUserDao.proxy();
	}

}
