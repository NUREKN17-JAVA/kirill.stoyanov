package ua.nure.kn.stoianov.usermanagement1.domain.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.stoianov.usermanagement1.domain.User;
import ua.nure.kn.stoianov.usermanagement1.domain.db.DaoFactory;
import ua.nure.kn.stoianov.usermanagement1.domain.db.DatabaseException;

public class EditServlet extends HttpServlet {

	
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	       if (req.getParameter("okButton") != null) {
	           doOk(req, resp);
	       } else if (req.getParameter("cancelButton") != null) {
	           doCancel(req, resp);
	       } else {
	           showPage(req, resp);
	       }
	   }

	private void showPage(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void doCancel(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = getUser(req);
		try {
			processUser(user);
		} catch (DatabaseException e) {
			e.printStackTrace();
			new ServletException(e);
		}
		req.getRequestDispatcher("/browse").forward(req, resp);
		
	}

	private User getUser(HttpServletRequest req) {
		User user = new User();
		String idStr = req.getParameter("id");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateStr = req.getParameter("date");
		
		if (idStr != null) {
			user.setId(new Long(idStr));
		}
		try {
			user.setDateOfBirth(DateFormat.getDateInstance().parse(dateStr));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	private void processUser(User user) throws DatabaseException {
		DaoFactory.getInstance().getUserDao().update(user);
		
	}
}
