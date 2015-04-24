package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Admin;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Category;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.AdminDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.CategoryDaoLocal;

public class CRUDCategory extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	private static Logger log = Logger.getLogger(CRUDCategory.class);

	@EJB
	private CategoryDaoLocal dao;

	@EJB
	private AdminDaoLocal adminDao;
	
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operation = request.getParameter("operation");
		String name = null;
		String description = null;
		int categoryId = -1;
		
		if (operation == null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		
		try {
			
			User user = (User)request.getSession().getAttribute("user");
			if (user == null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			Admin admin = (Admin)adminDao.findById(user.getId());
			if (admin==null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}

			if ((request.getParameter("name") != null) && (!"".equals(request.getParameter("name")))) {
				name = request.getParameter("name");
			}

			if ((request.getParameter("description") != null) 	&& (!"".equals(request.getParameter("description")))) {
				description = request.getParameter("description");
			}
			
			if ((request.getParameter("categoryId") != null) && (!"".equals(request.getParameter("categoryId")))) {
				categoryId = new Integer(request.getParameter("categoryId"));
			}
			
			if (operation.equals("ADD") && admin!=null){
				Category newO = new Category(name,description);
				Category oldO = (Category)dao.findCategoryByName(name);
				if (oldO!=null)
				{}
				else
					dao.persist(newO);
			}
			if (operation.equals("UPD")){
				Category s = (Category)dao.findById(categoryId);
				Category old = (Category)dao.findCategoryByName(name);
				if (admin != null) {	
					if (old!=null && !old.equals(s))
					{
						// VEC POSTOJI S IMENOM
					}
					else
						s.setName(name);
					s.setDescription(description);
					dao.merge(s);
				}
			}
			if (operation.equals("DEL")){
				Category s = (Category)dao.findById(categoryId);
				if (admin!=null) {	
					dao.remove(s);
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
