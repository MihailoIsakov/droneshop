package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Admin;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.AdminDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.SellerDaoLocal;

public class CRUDSeller extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	private static Logger log = Logger.getLogger(CRUDSeller.class);

	@EJB
	private SellerDaoLocal dao;
	
	@EJB
	private AdminDaoLocal adminDao;
	
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operation = request.getParameter("operation");
		String firstName = null;
		String lastName = null;
		String username = null;
		String password = null;
		String phoneNum = null;
		String email = null;
		int sellerId = -1;
		
		if (operation == null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		
		try {
			/*
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			*/
			User user = (User)request.getSession().getAttribute("user");
			if (user == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			Seller sell = (Seller)dao.findById(user.getId());

			Admin admin = (Admin)adminDao.findById(user.getId());
			if (sell== null && admin==null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			if ((request.getParameter("sellerId") != null) && (!"".equals(request.getParameter("sellerId")))) {
				sellerId = new Integer(request.getParameter("sellerId"));
			}

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
			
			if (operation.equals("ADD")){
				if (admin!=null){
					Seller newS = new Seller(firstName,lastName,username, password, phoneNum, email);
					Seller oldS = (Seller)dao.findSellerByUsername(username);
					if (oldS!=null)
					{}
					else
						dao.persist(newS);
				}
			}
			if (operation.equals("UPD")){
				Seller s = (Seller)dao.findById(sellerId);
				Seller oldS = (Seller)dao.findSellerByUsername(username);
				if (s != null) {	
					if (admin==null ||( oldS!=null && !oldS.equals(s))) //samo admin sme menjati username
					{}
					else
						s.setUsername(username);
					s.setFirstName(firstName);
					s.setLastName(lastName);
					s.setPassword(password);
					s.setEmail(email);
					s.setPhoneNumber(phoneNum);				
					dao.merge(s);
				}
			}
			if (operation.equals("DEL")){
				if (admin!=null){
					Seller s = (Seller)dao.findById(sellerId);
					if (s != null) {	
						dao.remove(s);
					}
				}
			}
			
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
