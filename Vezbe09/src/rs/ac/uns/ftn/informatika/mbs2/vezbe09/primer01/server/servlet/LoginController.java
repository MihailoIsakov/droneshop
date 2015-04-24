package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.UserDaoLocal;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -7345471861052209628L;
	
	private static Logger log = Logger.getLogger(LoginController.class);

	@EJB
	private UserDaoLocal userDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("++++++++++++++++");
		
		try {
			
			/*
			Context ctx = new InitialContext();
			UserDaoBean userDao = (UserDaoBean)ctx.lookup("UserDaoBeanRemote");
System.out.println(userDao);
			*/
			
			String korisnickoIme = request.getParameter("username");
			String lozinka = request.getParameter("password");
			
			if ((korisnickoIme == null) || (korisnickoIme.equals("")) || (lozinka == null) || (lozinka.equals(""))) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
				return;
			}
			
			System.out.println(korisnickoIme+" "+lozinka+" "+userDao);
			
			User korisnik = (User)userDao.findKorisnikSaKorisnickimImenomILozinkom(korisnickoIme, lozinka);
			
			if (korisnik != null) {	
				HttpSession session = request.getSession(true);
				session.setAttribute("admin", korisnik);
				session.setAttribute("user", korisnik);
				log.info("Korisnik " + korisnik.getUsername() + " se prijavio.");
				getServletContext().getRequestDispatcher("/ReadController").forward(request, response);
			}
			
		} catch (EJBException e) {
			if (e.getCause().getClass().equals(NoResultException.class)) {
				response.sendRedirect(response.encodeRedirectURL("./login.jsp"));
			} else {
				throw e;
			}
		} catch (ServletException e) {
			log.error(e);
			throw e;
		} catch (IOException e) {
			log.error(e);
			throw e;
		} 
		/*catch (NamingException e)
		{
			log.error(e);
			try {
				throw e;
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}*/
	}

	protected void doPost(HttpServletRequest request, 	HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
