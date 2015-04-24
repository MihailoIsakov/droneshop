package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Admin;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Category;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Offer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;

@Stateless(name="InitBean")
@Remote(Init.class)
public class InitBean implements Init {

	@PersistenceContext(unitName = "Vezbe09")
	EntityManager em;

	@EJB
	SellerDaoLocal dao;
	
	public void init() {
		//Admin admin = new Admin("Admin", "Admin", "admin", "admin");
		Category cat = new Category("cat1", "asdas");
		Seller s = dao.findById(2);
		Seller s1 = new Seller("roto", "roic","rot","rot", "a", "a");
		Offer offer = new Offer("offer1", new Date(), new Date(), new Date(), new Date(), 12, 10, 30, "  asd", 5, true, s, cat);
		Offer offer2 = new Offer("offer2", new Date(), new Date(), new Date(), new Date(), 12, 10, 30, "  asd", 5, false, s, cat);
		Offer offer3 = new Offer("offer3", new Date(), new Date(), new Date(), new Date(), 12, 10, 30, "  asd", 5, true, s, cat);
		Offer offer4 = new Offer("offer3", new Date(), new Date(), new Date(), new Date(), 12, 10, 30, "  asd", 5, true, s1, cat);
		Offer offer5 = new Offer("offer3", new Date(), new Date(), new Date(), new Date(), 12, 10, 30, "  asd", 5, true, s, cat);
		//em.persist(admin);
		dao.persist(s1);
		System.out.println("cacaca");
		em.persist(cat);
		System.out.println("cacacalalala");

		em.persist(offer4);
		em.persist(offer5);
		em.persist(offer);
		em.persist(offer2);
		em.persist(offer3);
	}
}
