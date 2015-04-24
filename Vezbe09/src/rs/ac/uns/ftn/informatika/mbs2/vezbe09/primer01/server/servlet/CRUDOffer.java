package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Admin;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Offer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.AdminDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.CategoryDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.OfferDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.SellerDaoLocal;

public class CRUDOffer extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	private static Logger log = Logger.getLogger(CRUDOffer.class);

	@EJB
	private OfferDaoLocal dao;
	
	@EJB
	private SellerDaoLocal sellerDao;

	@EJB
	private AdminDaoLocal adminDao;
	
	@EJB
	private CategoryDaoLocal catDao;
	
	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String operation = request.getParameter("operation");
		String name = null;
		Date dateCreated = null;
		Date expirationDate = null;
		Date validFrom = null;
		Date validTo = null;
		double regularPrice = 0, salePrice = 0;
		int maxOffers = 0, purchasedOffers = 0;
		String description = null;
		boolean active = true;
		int categoryId = -1;
		int offerId = -1;
		
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

			
			Admin admin = (Admin)adminDao.findById(user.getId());
			if (sell== null && admin==null) {
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			
			if ((request.getParameter("offerId") != null) && (!"".equals(request.getParameter("offerId")))) {
				offerId = new Integer(request.getParameter("offerId"));
			}

			if ((request.getParameter("name") != null) && (!"".equals(request.getParameter("name")))) {
				name = request.getParameter("name");
			}

			if ((request.getParameter("description") != null) 	&& (!"".equals(request.getParameter("description")))) {
				description = request.getParameter("description");
			}
			
			if ((request.getParameter("maxOffers") != null) && (!"".equals(request.getParameter("maxOffers")))) {
				maxOffers = new Integer(request.getParameter("maxOffers"));
			}
			
			if ((request.getParameter("purchasedOffers") != null) && (!"".equals(request.getParameter("purchasedOffers")))) {
				purchasedOffers = new Integer(request.getParameter("purchasedOffers"));
			}
			
			if ((request.getParameter("regularPrice") != null) && (!"".equals(request.getParameter("regularPrice")))) {
				regularPrice = new Integer(request.getParameter("regularPrice"));
			}
			
			if ((request.getParameter("salePrice") != null) && (!"".equals(request.getParameter("salePrice")))) {
				salePrice = new Integer(request.getParameter("salePrice"));
			}
			
			if ((request.getParameter("validFrom") != null) && (!"".equals(request.getParameter("validFrom")))) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				validFrom = sdf.parse(request.getParameter("validFrom"));
			}
			
			if ((request.getParameter("validTo") != null) && (!"".equals(request.getParameter("validTo")))) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				validTo = sdf.parse(request.getParameter("validTo"));
			}
			
			if ((request.getParameter("categoryId") != null) && (!"".equals(request.getParameter("categoryId")))) {
				categoryId = new Integer(request.getParameter("categoryId"));
			}
			
			if ((request.getParameter("dateCreated") != null) && (!"".equals(request.getParameter("dateCreated")))) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				dateCreated = sdf.parse(request.getParameter("dateCreated"));
			}
			
			if ((request.getParameter("expirationDate") != null) && (!"".equals(request.getParameter("expirationDate")))) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				expirationDate = sdf.parse(request.getParameter("expirationDate"));
			}
			
			if ((request.getParameter("active") != null) 	&& (!"".equals(request.getParameter("active")))) {
				active = new Boolean(request.getParameter("active"));
			}
			
			if (operation.equals("ADD") && sell!=null){
				Offer newO = new Offer(name,dateCreated,expirationDate,validTo, validFrom, regularPrice, salePrice, maxOffers,description, purchasedOffers, active, sell, catDao.findById(categoryId));
				Offer oldO = (Offer)dao.findOfferByName(name);
				if (oldO!=null)
				{}
				else
					dao.persist(newO);
			}
			if (operation.equals("UPD")){
				Offer s = (Offer)dao.findById(offerId);
				Offer old = (Offer)dao.findOfferByName(name);
				if (s != null && s.getManager().equals(sell)) {	
					if (old!=null && !old.equals(s))
					{
						// VEC POSTOJI PONUDA S IMENOM
					}
					else
						s.setName(name);
					s.setCategory(catDao.findById(categoryId));
					s.setDateCreated(dateCreated);
					s.setDescription(description);
					s.setExpirationDate(expirationDate);
					s.setMaxOffers(maxOffers);
					s.setRegularPrice(regularPrice);
					s.setSalePrice(salePrice);
					s.setValidFrom(validFrom);
					s.setValidTo(validTo);

					dao.merge(s);
				}
				if (admin != null) {	
					s.setActive(active);
					s.setPurchasedOffers(purchasedOffers);
					dao.merge(s);
				}
			}
			if (operation.equals("DEL")){
				Offer s = (Offer)dao.findById(offerId);
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
		} catch (ParseException e)
		{
			log.error(e);
			try {
				throw e;
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
