package ua.nure.kn.stoianov.usermanagement1.domain.db;

import java.util.Collection;

import ua.nure.kn.stoianov.usermanagement1.domain.User;

public class HsqldbUserDao implements UserDao {
	
	private ConnectionFactory connectionFactory;
	
	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}
	

	public User create(User user) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(User user) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public void delete(User user) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public User find(Long id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection findAll() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
