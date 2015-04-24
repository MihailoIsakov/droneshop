package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Category;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Gorivo;

public interface CategoryDaoLocal extends GenericDaoLocal<Category, Integer> {

	public Category findCategoryByName(
			String name) ;
	
}
