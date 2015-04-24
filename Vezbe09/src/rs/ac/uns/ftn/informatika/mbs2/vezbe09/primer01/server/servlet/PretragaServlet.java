package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Admin;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Branch;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Buyer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Category;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Coupon;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Offer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Payment;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.AdminDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.BranchDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.BuyerDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.CategoryDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.CouponDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.OfferDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.PaymentDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.SellerDaoLocal;

/**
 * Servlet implementation class PretragaServlet
 */
public class PretragaServlet extends HttpServlet {

	private static final long serialVersionUID = -2544396238785425302L;
	
	private static Logger log = Logger.getLogger(CRUDSeller.class);

	@EJB
	private SellerDaoLocal sellerDao;
	
	@EJB
	private BranchDaoLocal branchDao;
	
	@EJB
	private AdminDaoLocal adminDao;
	
	@EJB
	private BuyerDaoLocal buyerDao;
	
	@EJB
	private OfferDaoLocal offerDao;
	
	@EJB
	private CategoryDaoLocal categoryDao;
	
	@EJB
	private PaymentDaoLocal paymentDao;
	
	@EJB
	private CouponDaoLocal couponDao;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subject = request.getParameter("subject");
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding(response.getCharacterEncoding());

		//Gson gson = new Gson();
		PrintWriter out = response.getWriter();

		
		if (subject == null) {  //nije odabrano sta se prikazuje
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
		
		
		User user = (User)request.getSession().getAttribute("user");
		if (user == null) {  //nije ulogovan
			if (!subject.equals("OFFERS")){  //anonimni trazi pregled nedozvoljenih stvari
				response.sendRedirect(response.encodeURL("./login.jsp"));
				return;
			}
			List <Offer> offers = offerDao.findActiveOffers();
			for(int i=0;i<offers.size();i++)
			{
				System.out.println(offers.get(i).getName());
			}
			
			return;
			/*
			JSONArray nizJsona = new JSONArray(offers);
			
			JSONObject obj = new JSONObject();
			
			for(int i=0;i<offers.size();i++)
			{
				System.out.println(offers.get(i).getName());
			}
			
			response.setStatus(HttpServletResponse.SC_OK);	
			
			obj.put("niz", nizJsona);
			obj.put("brojEl", nizJsona.length());
			out.print(obj);
			*/
		}
		
		Seller sell = (Seller)sellerDao.findById(user.getId());
		Buyer buyer = (Buyer)buyerDao.findById(user.getId());
		Admin admin = (Admin)adminDao.findById(user.getId());
		if (sell== null && admin==null) {
			response.sendRedirect(response.encodeURL("./login.jsp"));
			return;
		}
			
		if (admin!=null)
		{
			if (subject.equals("OFFERS"))
			{
				List <Offer> offers = offerDao.findAll();
				for(int i=0;i<offers.size();i++)
				{
					System.out.println(offers.get(i).getName());
				}
				
				return;
			}
			if (subject.equals("CATEGORIES"))
			{
				List <Category> cats = categoryDao.findAll();
				for(int i=0;i<cats.size();i++)
				{
					System.out.println(cats.get(i).getName());
				}
				
				return;
			}
			if (subject.equals("SELLERS"))
			{
				List <Seller> cats = sellerDao.findAll();
				for(int i=0;i<cats.size();i++)
				{
					System.out.println(cats.get(i).getUsername());
				}
				
				return;
			}
			if (subject.equals("PAYMENTS"))
			{
				List <Payment> cats = paymentDao.findAll();
				for(int i=0;i<cats.size();i++)
				{
					System.out.println(cats.get(i).getId());
				}
				
				return;
			}
			if (subject.equals("COUPONS"))
			{
				List <Coupon> cats = couponDao.findAll();
				for(int i=0;i<cats.size();i++)
				{
					System.out.println(cats.get(i).getId());
				}
				
				return;
			}
		}
		
		if (sell!=null)
		{
			if (subject.equals("OFFERS"))
			{
				List <Offer> offers = offerDao.findByManager(sell);
				for(int i=0;i<offers.size();i++)
				{
					System.out.println(offers.get(i).getName());
				}
				return;
			}
			if (subject.equals("BRANCHES"))
			{
				List <Branch> cats = branchDao.findByManager(sell.getId());
				for(int i=0;i<cats.size();i++)
				{
					System.out.println(cats.get(i).getName());
				}
				
				return;
			}
			if (subject.equals("SELLERS"))
			{
				List <Seller> cats = sellerDao.findAll();
				for(int i=0;i<cats.size();i++)
				{
					System.out.println(cats.get(i).getUsername());
				}
				
				return;
			}
		}

		
		String naziv = request.getParameter("naziv");

		String opis = request.getParameter("opis");
		String radio = request.getParameter("radios");

		/*
		if (radio.equals("Komponente"))
		{
			String minCenaS = request.getParameter("minCena");
			if (minCenaS!="")
				minCena = Double.parseDouble(minCenaS);
			String maxCenaS = request.getParameter("maxCena");
			if (maxCenaS!="")
				maxCena = Double.parseDouble(maxCenaS);
			String kolicinaS = request.getParameter("kolicina");
			if (kolicinaS!="")
			kolicina = Integer.parseInt(kolicinaS);
			String kategorija = request.getParameter("selectKategorija");
			
			ArrayList<JSONObject> listaKomponenti  = new ArrayList<JSONObject>();
			//System.out.println("Duzina: " + Baza.getInstance().getMapaKomponenti().size());
			//Posalji komponente po datim kriterijumima
			for (Komponenta k : Baza.getInstance().getMapaKomponenti().values())
			{
				boolean ostaje = true;
				
				if (!naziv.equals(""))
					if (!k.getNaziv().contains(naziv))
						ostaje = false;
				
				if (minCena!=-1)
					if (k.getCena() < minCena)
						ostaje = false;
				
				if (maxCena!=-1)
					if (k.getCena() > maxCena)
						ostaje = false;
				
				if (kolicina!=-1)
					if (k.getRaspolozivaKolicina() < kolicina)
						ostaje = false;
				
				if (!opis.equals(""))
					if (!k.getOpis().contains(opis))
						ostaje = false;
				
				if (kategorija!=null)
					if(!k.getKategorija().getNaziv().equals(kategorija))
						ostaje = false;
				
				if (ostaje)
				{
					JSONObject obj = null;
					try {
						obj = new JSONObject(gson.toJson(k));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listaKomponenti.add(obj);
				}
			}
			
			JSONArray nizJsona = new JSONArray(listaKomponenti);
			
			JSONObject obj = new JSONObject();
			
			response.setStatus(HttpServletResponse.SC_OK);	
			
			if (kor !=null)
			{
				if (kor.isAdmin())
					obj.put("admin", "true" );
				else
					obj.put("korisnik", "true");
			}
			
			obj.put("niz", nizJsona);
			obj.put("brojEl", nizJsona.length());
			out.print(obj);

		}
		else
		{
			String komponente = request.getParameter("komponente");
			// Posalji uredjaje 
			ArrayList<JSONObject> listaUredjaja  = new ArrayList<JSONObject>();
			//System.out.println("Duzina: " + Baza.getInstance().getMapaKomponenti().size());
			//Posalji komponente po datim kriterijumima

			for (Uredjaj k : Baza.getInstance().getMapaUredjaja().values())
			{
				boolean ostaje = true;
				//System.out.println(k.getSpisakKomponenti());
				
				if (!naziv.equals(""))
					if (!k.getNaziv().contains(naziv))
						ostaje = false;
				
				if (!opis.equals(""))
					if (!k.getOpis().contains(opis))
						ostaje = false;
				
				if (!komponente.equals(""))
				{
					boolean imaNekuKom = false;
					for (String kom : k.getSpisakKomponenti())
					{

					if(kom.equals(komponente))
						imaNekuKom = true;
					}
					if(!imaNekuKom)
						ostaje = false;
				}
				if (ostaje)
				{
					JSONObject obj = null;
					try {
						obj = new JSONObject(gson.toJson(k));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					listaUredjaja.add(obj);
				}
			}
			
			JSONArray nizJsona = new JSONArray(listaUredjaja);
			JSONObject obj = new JSONObject();


			
			response.setStatus(HttpServletResponse.SC_OK);	
			
			if (kor !=null)
			{
				if (kor.isAdmin())
					obj.put("admin", "true" );
				else
					obj.put("korisnik", "true");
			}
			
			obj.put("niz", nizJsona);
			obj.put("brojEl", nizJsona.length());

			//System.out.println(obj.toString(2));
			
			out.print(obj);

		}
		*/
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
