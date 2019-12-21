package ua.nure.kn.stoianov.usermanagement1.domain.web;

import java.text.DateFormat;
import java.util.Date;

import ua.nure.kn.stoianov.usermanagement1.domain.User;

public class AddServletTest extends MockServletTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(AddServlet.class);
	}

	public void testAdd() {
		Date date = new Date();
        User newUser = new User("John", "Doe", date);
        User user = new User(new Long(1000), "John", "Doe", date);
        getMockUserDao().expectAndReturn("create", newUser, user);

        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("dateOfBirthd", DateFormat.getDateInstance().format(user.getDateOfBirth()));
        addRequestParameter("okButton", "Ok");
        doPost();
    }
	
	public void testAddEmptyFirstName() {
		Date date = new Date();
        addRequestParameter("lastName", "Doe");
        addRequestParameter("date", DateFormat.getDateInstance().format(user.getDateOfBirth()));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
	public void testAddEmptyLastName() {
        Date date = new Date();
        addRequestParameter("firstName", "John");
        addRequestParameter("dateOfBirthd", DateFormat.getDateInstance().format(user.getDateOfBirth()));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
	public void testAddEmptyDate() {
        Date date = new Date();
        addRequestParameter("firstName", "John");
        addRequestParameter("lastName", "Doe");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }
	public void testAddIncorrectDateOfBirthd() {
        Date date = new Date();
        User user = new User(1000L, "Jon", "Doue", date);
        addRequestParameter("lastName", user.getLastName());
        addRequestParameter("firstName", user.getFirstName());
        addRequestParameter("dateOfBirthd", "bla-bla-bla");
        addRequestParameter("ok", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull(errorMessage);
    }
}

