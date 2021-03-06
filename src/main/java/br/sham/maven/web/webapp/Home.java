package br.sham.maven.web.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// O Servelet pode ser mapedo com a tag @WebServlet ou pelo web.xml
//@WebServlet(urlPatterns={"/"})
public class Home extends HttpServlet {
	private static final long serialVersionUID = 2806421523585360625L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/templates/index.jsp").forward(req, resp);
	}
}
