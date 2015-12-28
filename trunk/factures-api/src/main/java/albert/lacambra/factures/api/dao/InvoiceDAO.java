package albert.lacambra.factures.api.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import albert.lacambra.factures.api.HibernateUtil;
import albert.lacambra.factures.api.models.Invoice;

public class InvoiceDAO {

	public int addInvoice(Invoice invoice){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.save(invoice);
		return invoice.getInvoiceId();
	}
	
	public int updateInvoice(Invoice invoice){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.update(invoice);
		return invoice.getInvoiceId();
	}
	
	public int deleteInvoice(int invoiceId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = session.getNamedQuery("invoice.delete").setParameter("idInvoice", invoiceId);
		int deleted = q.executeUpdate();
		return deleted;
	}
	
	public List<Invoice> getAllInvoices(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = session.getNamedQuery("invoice.select.all");
		
		@SuppressWarnings("unchecked")
		List<Invoice> list = q.list();
		return list;
	}
	
	public Invoice getInvoice(int invoiceId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = session.getNamedQuery("invoice.select").setParameter("idInvoice", invoiceId);
		return (Invoice) q.uniqueResult();
	}
}
