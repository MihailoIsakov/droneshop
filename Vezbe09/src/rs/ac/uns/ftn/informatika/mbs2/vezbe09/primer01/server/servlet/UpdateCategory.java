package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Category;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.CategoryDaoLocal;

public class UpdateCategory extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	private static Logger log = Logger.getLogger(UpdateCategory.class);

	@EJB
	private CategoryDaoLocal dao;
	
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {
			
			String name = null;
			String description = null;
		
			
			if ((request.getSession().getAttribute("admin")) == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			if ((request.getParameter("name") != null) && (!"".equals(request.getParameter("name")))) {
				name = request.getParameter("name");
			}


			if ((request.getParameter("description") != null) && (!"".equals(request.getParameter("description")))) {
				description = request.getParameter("description");
			}

			Category s = (Category)dao.findCategoryByName(name);
			
			if (s != null) {	
				s.setDescription(description);
				dao.merge(s);
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
