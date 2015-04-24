package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Offer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;

@Stateless
@Local(OfferDaoLocal.class)
public class OfferDaoBean extends GenericDaoBean<Offer, Integer> implements
		OfferDaoLocal {

	public Offer findOfferByName(
			String name) {
		Query q = em
				.createNamedQuery("findOfferByName");
		q.setParameter("name", name);
		Offer result = (Offer) q.getSingleResult();
		return result;
	}
	
	//@SuppressWarnings("unchecked")
	public List<Offer> findActiveOffers() {
		Query q = em
				.createNamedQuery("findActiveOffers");
		return q.getResultList();
	}
	
	//@SuppressWarnings("unchecked")
		public List<Offer> findByManager(Seller s) {
			Query q = em
					.createNamedQuery("findOffersByManager");
			q.setParameter("manager", s);
			return q.getResultList();
		}
}
