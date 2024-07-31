package be.jossart.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.jossart.javabeans.Person;

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogInServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/LogIn.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		
		if(request.getParameter("submit") != null) {
			if(!paramsAreValid(usernameParam, passwordParam)){
	 			Person person = Person.login(usernameParam, passwordParam);
	 			if(person != null) {
	 				HttpSession session = request.getSession();
	 				if(session.isNew() == false) {
	 					session.invalidate();
                        session = request.getSession();
                        session.setAttribute("person", person);
                        request.setAttribute("success", "welcome " + person.getUsername());
                        getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
	 				}
	 			}else {
	 				request.setAttribute("fail", "username or password incorrect!");
	 				getServletContext().getRequestDispatcher("/WEB-INF/JSP/LogIn.jsp").forward(request, response);
	 			}
			}else {
					request.setAttribute("fail", "Username or password incorrect or not found ! Go back to <a href=\"/ProjectJEE2023Client/LogInServlet\">Log In Page</a>");
					getServletContext().getRequestDispatcher("/WEB-INF/JSP/LogIn.jsp").forward(request, response);
				}
			}else{
				
			}
	}
	private boolean paramsAreValid(String usernameParam, String passwordParam) {
		if(	usernameParam == null || usernameParam.equals("") || usernameParam.length() < 5 
				||	passwordParam == null || passwordParam.equals("") || passwordParam.length() < 5) {
			return true;
		}
		return false;
	}

}