package ua.nure.kn.stoianov.usermanagement1.domain.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mockobjects.dynamic.Mock;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn.stoianov.usermanagement1.domain.User;
import ua.nure.kn.stoianov.usermanagement1.domain.db.DaoFactory;
import ua.nure.kn.stoianov.usermanagement1.domain.db.DaoFactoryImpl;
import ua.nure.kn.stoianov.usermanagement1.domain.db.MockDaoFactory;
import ua.nure.kn.stoianov.usermanagement1.domain.db.MockUserDao;

public class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;
	
	private Mock mockUserDao;
	
	protected void setUp() throws Exception {
		super.setUp();
		try {
		Properties properties = new Properties();
		
		DaoFactory.getInstance().init(properties);
		properties.setProperty("dao.factory", MockDaoFactory.class
				.getName());
		DaoFactory.init(properties);
		mockUserDao = ((MockDaoFactory) DaoFactory.getInstance())
				.getMockUserDao();
		mockUserDao.expectAndReturn("findAll", new ArrayList());
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		mainFrame.setVisible(true);
	}

	protected void tearDown() throws Exception {
		mainFrame.setVisible(false);
		getHelper().cleanUp(this);
		super.tearDown();
	}
	
	private Component find(Class componentClass, String name) {
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		//assertNotNull("Could not find component '" + name + "'", component);
		return component;
	}
	
	public void testBrowseControls() {
		find(JPanel.class, "browsePanel"); //$NON-NLS-1$
		JTable table = (JTable) find(JTable.class, "userTable"); //$NON-NLS-1$
		assertEquals(3, table.getColumnCount());
		assertEquals(Messages.getString("MainFrameTest.id"), table.getColumnName(0)); //$NON-NLS-1$
		assertEquals(Messages.getString("MainFrameTest.first_name"), table.getColumnName(1)); //$NON-NLS-1$
		assertEquals(Messages.getString("MainFrameTest.last_name"), table.getColumnName(2)); //$NON-NLS-1$
		find(JButton.class, "addButton"); //$NON-NLS-1$
		find(JButton.class, "editButton"); //$NON-NLS-1$
		find(JButton.class, "deleteButton"); //$NON-NLS-1$
		find(JButton.class, "detailsButton"); //$NON-NLS-1$
	}

	public void testAddUser() {
		String firstName = "John";
		String lastName = "Doe";
		Date now = new Date();
		
		User user = new User(firstName, lastName, now);
		
		User expectedUser = new User(new Long(1), firstName, lastName, now);
		mockUserDao.expectAndReturn("create", user, expectedUser);
		
		ArrayList users = new ArrayList();
		users.add(expectedUser);
		mockUserDao.expectAndReturn("findAll", users);
		
		JTable table = (JTable) find(JTable.class, "userTable");
		assertEquals(0, table.getRowCount());
		
		JButton addButton = (JButton) find(JButton.class, "addButton"); //$NON-NLS-1$
		getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

		find(JPanel.class, "addPanel"); //$NON-NLS-1$
		
		JTextField firstNameField = (JTextField) find(JTextField.class, "firstNameField"); //$NON-NLS-1$
		JTextField lastNameField = (JTextField) find(JTextField.class, "lastNameField"); //$NON-NLS-1$
		JTextField dateOfBirthField = (JTextField) find(JTextField.class, "dateOfBirthField"); //$NON-NLS-1$
		JButton okButton = (JButton) find(JButton.class, "okButton"); //$NON-NLS-1$
		find(JButton.class, "cancelButton"); //$NON-NLS-1$
		
		getHelper().sendString(new StringEventData(this, firstNameField, firstName));
		getHelper().sendString(new StringEventData(this, lastNameField, lastName));
		DateFormat formatter = DateFormat.getDateInstance();
		String date = formatter.format(now);
		getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
		
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find(JPanel.class, "browsePanel");
		table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
	}
}
