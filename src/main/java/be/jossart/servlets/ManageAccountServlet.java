package be.jossart.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.jossart.javabeans.Person;

public class ManageAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ManageAccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/ManageAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		HttpSession session = request.getSession();
		Person person = (Person) session.getAttribute("person");
		
		if(request.getParameter("submit") != null) {
			if(!paramAreValid(passwordParam) && usernameParam.equals(person.getUsername())) {
				if(person.updatePassword(passwordParam)) {
	 				if(session.isNew() == false) {
	 					session.invalidate();
                        session = request.getSession();
                        session.setAttribute("person", person);
                        request.setAttribute("success", "Password updated!");
                        getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
	 				}
	 			}else {
	 				request.setAttribute("fail", "password incorrect!");
	 				getServletContext().getRequestDispatcher("/WEB-INF/JSP/ManageAccount.jsp").forward(request, response);
	 			}
			}
		}
	}
	private boolean paramAreValid(String passwordParam) {
		if(passwordParam == null || passwordParam.equals("") || passwordParam.length() < 5) {
			return true;
		}
		return false;
	}

}
