package be.jossart.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.jossart.javabeans.Person;

public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignInServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/JSP/SignIn.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstnameParam = request.getParameter("firstname");
		String lastnameParam = request.getParameter("lastname");
		String usernameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		
		if(request.getParameter("submit") != null) {
			if(!paramsAreValid(firstnameParam, lastnameParam, usernameParam, passwordParam)) {
				Person person = new Person(firstnameParam, lastnameParam, usernameParam, passwordParam);
				if(person.create()) {
					request.setAttribute("success", "congratulations registration success please log in");
					getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
				}else {
					request.setAttribute("fail", "fail during the registration please try again!");
                	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("fail", "fail during the registration please try again!");
            	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
			}
		}
	}

	private boolean paramsAreValid(String firstnameParam, String lastnameParam, String usernameParam,
			String passwordParam) {
		if(firstnameParam == null || firstnameParam.equals("") || firstnameParam.length() < 3 ||
				lastnameParam == null || lastnameParam.equals("") || lastnameParam.length() < 3 ||
				usernameParam == null || usernameParam.equals("") || usernameParam.length() < 5 ||
				passwordParam == null || passwordParam.equals("") || passwordParam.length() < 5 ||
	            !passwordParam.matches("[A-Z]+")) {
			return true;
		}
		return false;
	}
}
