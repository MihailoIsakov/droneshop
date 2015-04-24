package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.UserDaoLocal;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session.GenericDaoBean;


@Stateless
@Local(UserDaoLocal.class)
public class UserDaoBean extends GenericDaoBean<User, Integer>
		implements UserDaoLocal {

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
