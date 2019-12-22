package ua.nure.kn.stoianov.usermanagement1.domain.agent;

import java.util.Collection;

import jade.core.AID;
import jade.core.Agent;
import ua.nure.kn.stoianov.usermanagement1.domain.db.DaoFactory;
import ua.nure.kn.stoianov.usermanagement1.domain.db.DatabaseException;

public class SearchAgent extends Agent {

	@Override
	protected void setup() {
		super.setup();
		System.out.print(getAID().getName() + " sarted");
	}

	@Override
	protected void takeDown() {
		System.out.println(getAID().getName() + " terminated");
		super.takeDown();
	}
	
	public void search(String firstName, String lastName) throws SearchException {
		try {
			Collection users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
			if (users.size() > 0) {
				showUsers(users);
			} else {
				addBehaviour(new SearchRequestBehaviour(new AID[] {}, firstName, lastName));
			}
		} catch (DatabaseException e) {
			throw new SearchException(e);
		}

	}
	
	void showUsers(Collection user) {
		// TODO отобразить найденных пользователей
	}
	
}
