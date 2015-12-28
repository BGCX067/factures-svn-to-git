package albert.lacambra.factures.api;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.servlet.jsp.tagext.FunctionInfo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import albert.lacambra.factures.api.HibernateUtil;
import albert.lacambra.factures.api.dao.InvoiceDAO;
import albert.lacambra.factures.api.models.Invoice;
import albert.lacambra.factures.api.models.Person;


public class InvoicesDAO 
{
	Session session;
	InvoiceDAO dao = new InvoiceDAO();
	Person albert;
	Person ruth;
	Invoice i1;
	Invoice i2;
	Invoice i3;

	@Before 
	public void setUp() 
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	@Test
	public void addAndGetFactura() 
	{
		loadInvoices();
		int id = dao.addInvoice(i1);
		session.clear();
		Invoice i =  dao.getInvoice(id);
		assertTrue(i.getPersonId() == i1.getPersonId());
	}
	
	@Test
	public void deleteFactura()
	{
		loadInvoices();
		session.save(i1);
		assertNotNull(i1.getInvoiceId());
		session.clear();
		int deleted = dao.deleteInvoice(i1.getInvoiceId());
		assertTrue(deleted == 1);
		Invoice i = dao.getInvoice(i1.getInvoiceId());
		assertNull(i);
	}
	
	@Test
	public void getAll()
	{
		loadInvoices();
		
		assertTrue(i1.getInvoiceId() == -1);
		session.save(i1);
		int id1 = i1.getInvoiceId();

		assertTrue(i2.getInvoiceId() == -1);
		session.save(i2);
		int id2 = i2.getInvoiceId();
		
		session.clear();
		List<Invoice> list = dao.getAllInvoices();
		assertTrue(list.size() == 2);
		assertTrue(list.get(0).getInvoiceId() == id1);
		assertTrue(list.get(1).getInvoiceId() == id2);
	}
	
	@After
	public void end()
	{
		Query q = session.createQuery("delete from Invoice");
		q.executeUpdate();
		session.getTransaction().commit();
	}
	
	
	private void loadInvoices(){
		i1 = new Invoice(albert, 1,(float) 22.3, new Date());
		i2 = new Invoice(albert, 1,(float) 25.3, new Date() );
	}
}






























