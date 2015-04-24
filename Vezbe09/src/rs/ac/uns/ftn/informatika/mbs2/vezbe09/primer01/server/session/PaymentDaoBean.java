package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Payment;

@Stateless
@Local(PaymentDaoLocal.class)
public class PaymentDaoBean extends GenericDaoBean<Payment, Integer> implements
		PaymentDaoLocal {

}
