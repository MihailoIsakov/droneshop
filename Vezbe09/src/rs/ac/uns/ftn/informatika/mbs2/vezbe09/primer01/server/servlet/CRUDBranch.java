package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Branch;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.BranchDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.SellerDaoLocal;

public class CRUDBranch extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	private static Logger log = Logger.getLogger(CRUDBranch.class);

	@EJB
	private BranchDaoLocal dao;
	
	@EJB
	private SellerDaoLocal sellerDao;
	
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operation = request.getParameter("operation");
		String name = null;
		String phoneNum = null;
		String address = null;
		int branchId = -1;
		
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
			
			Seller sell = (Seller)sellerDao.findById(user.getId());
			if (sell== null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			if ((request.getParameter("branchId") != null) && (!"".equals(request.getParameter("branchId")))) {
				branchId = new Integer(request.getParameter("branchId"));
			}

			if ((request.getParameter("name") != null) && (!"".equals(request.getParameter("name")))) {
				name = request.getParameter("name");
			}

			if ((request.getParameter("phoneNum") != null) 	&& (!"".equals(request.getParameter("phoneNum")))) {
				phoneNum = request.getParameter("phoneNum");
			}
			
			if ((request.getParameter("address") != null) && (!"".equals(request.getParameter("address")))) {
				address = request.getParameter("address");
			}
			
			if (operation.equals("ADD")){
				Branch newB = new Branch(name,phoneNum,address, sell);
				Branch oldB = (Branch)dao.findBranchByName(name);
				if (oldB!=null)
				{
					// VEC POSTOJI PONUDA S IMENOM
				}
				else
					dao.persist(newB);
			}
			if (operation.equals("UPD")){
				Branch newB = (Branch)dao.findById(branchId);
				Branch oldB = (Branch)dao.findBranchByName(name);
				if (newB != null && newB.getManager().equals(sell)) {	
					if (oldB!=null && !oldB.equals(newB))
					{
						// VEC POSTOJI PONUDA S IMENOM
					}
					else
						newB.setName(name);
					newB.setAddress(address);
					newB.setPhoneNumber(phoneNum);
					dao.merge(newB);
				}
			}
			if (operation.equals("DEL")){

				Branch s = (Branch)dao.findById(branchId);
				if (s != null && s.getManager().equals(sell)) {	
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
