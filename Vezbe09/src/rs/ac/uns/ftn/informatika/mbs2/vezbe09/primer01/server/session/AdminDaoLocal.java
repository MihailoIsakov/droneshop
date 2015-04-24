package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Admin;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;

public interface AdminDaoLocal extends GenericDaoLocal<Admin, Integer> {

	public User findKorisnikSaKorisnickimImenomILozinkom(
			String korisnickoIme, String lozinka) ;
}
