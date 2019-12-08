package ua.nure.kn.stoianov.usermanagement1.domain.db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ua.nure.kn.stoianov.usermanagement1.domain.User;

public class MockUserDao implements UserDao {
	private long id = 0;
	private Map users = new HashMap();

	public User create(User user) throws DatabaseException {
		Long currentID = new Long(++id);
		user.setId(currentID);
		users.put(currentID, user);
		return user;
	}


	public void update(User user) throws DatabaseException {
		Long currentId = user.getId();
		users.remove(currentId);
		users.put(currentId, user);

	}


	public void delete(User user) throws DatabaseException {
		Long currentId = user.getId();
		users.remove(currentId);
	}


	public User find(Long id) throws DatabaseException {

		return (User) users.get(id);
	}


	public Collection findAll() throws DatabaseException {
		
		return users.values();
	}


	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

}
