package br.sham.maven.web.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import br.com.alura.maven.App;
import br.com.caelum.stella.tinytype.CPF;

//@WebServlet(urlPatterns={"/"})
public class Home extends HttpServlet {

	private static final long serialVersionUID = 2806421523585360625L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(new CPF("22222222222").isValido());
		System.out.println(new CPF("05325855182").isValido());
		//App.main(new String[0]);
		req.getRequestDispatcher("/templates/index.jsp").forward(req, resp);
	}
	
	

}
