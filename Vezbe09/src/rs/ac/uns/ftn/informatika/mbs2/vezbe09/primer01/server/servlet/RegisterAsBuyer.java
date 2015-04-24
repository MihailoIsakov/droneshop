package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Buyer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.BuyerDaoLocal;

public class RegisterAsBuyer extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	private static Logger log = Logger.getLogger(RegisterAsBuyer.class);

	@EJB
	private BuyerDaoLocal dao;
	
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String firstName = null;
			String lastName = null;
			String username = null;
			String password = null;
			String phoneNum = null;
			String email = null;
			String address = null;

		/*
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			*/

			if ((request.getParameter("firstName") != null) && (!"".equals(request.getParameter("firstName")))) {
				firstName = request.getParameter("firstName");
			}


			if ((request.getParameter("lastName") != null) && (!"".equals(request.getParameter("lastName")))) {
				lastName = request.getParameter("lastName");
			}

			if ((request.getParameter("username") != null) 	&& (!"".equals(request.getParameter("username")))) {
				username = request.getParameter("username");
			}
			
			if ((request.getParameter("password") != null) && (!"".equals(request.getParameter("password")))) {
				password = request.getParameter("password");
			}

			if ((request.getParameter("phoneNum") != null) 	&& (!"".equals(request.getParameter("phoneNum")))) {
				phoneNum = request.getParameter("phoneNum");
			}
			
			if ((request.getParameter("email") != null) && (!"".equals(request.getParameter("email")))) {
				email = request.getParameter("email");
			}
			
			if ((request.getParameter("address") != null) && (!"".equals(request.getParameter("address")))) {
				address = request.getParameter("address");
			}

			System.out.println(username+" "+ password + " " + lastName+" "+firstName+" "+phoneNum+" "+address+" "+email);
			
			Buyer s = new Buyer(firstName,lastName,username, password, phoneNum, email, address);
			
			dao.persist(s);

			getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
			return;
			
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
