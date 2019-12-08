package ua.nure.kn.stoianov.usermanagement1.domain.gui;

import java.awt.Component;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;
	
	protected void setUp() throws Exception {
		super.setUp();
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
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
		
		getHelper().sendString(new StringEventData(this, firstNameField, "John"));
		getHelper().sendString(new StringEventData(this, lastNameField, "Doe"));
		DateFormat formatter = DateFormat.getDateInstance();
		String date = formatter.format(new Date());
		getHelper().sendString(new StringEventData(this, dateOfBirthField, date));
		
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		find(JPanel.class, "browsePanel");
		table = (JTable) find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
