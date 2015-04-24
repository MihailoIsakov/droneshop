package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Admin;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;

@Stateless
@Local(AdminDaoLocal.class)
public class AdminDaoBean extends GenericDaoBean<Admin, Integer> implements
AdminDaoLocal {

	public User findKorisnikSaKorisnickimImenomILozinkom(
			String korisnickoIme, String lozinka) {
		Query q = em
				.createNamedQuery("findKorisnikSaKorisnickimImenomILozinkom");
		System.out.println(2);
		q.setParameter("korisnickoIme", korisnickoIme);
		q.setParameter("lozinka", lozinka);
		User result = (User) q.getSingleResult();
		return result;
	}
}