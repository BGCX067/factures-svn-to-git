package albert.lacambra.factures.api.actions;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;

import albert.lacambra.factures.api.HibernateUtil;
import albert.lacambra.factures.api.dao.InvoiceDAO;
import albert.lacambra.factures.api.models.Invoice;

@Path("/invoice")
public class InvoiceActions {

	private ObjectMapper mapper = new ObjectMapper();
	private InvoiceDAO dao = new InvoiceDAO();
	/**
	 * get all invoices from person
	 * @param userId
	 * @return
	 */
	@GET
	@Path("user/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUserInvoices(@PathParam("id") int userId){

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return "";
	}

	/**
	 * get an invoice
	 * @param invoiceId
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@GET
	@Path("{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getInvoice(@PathParam("id") int invoiceId) throws JsonGenerationException, JsonMappingException, IOException{

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Invoice invoice = dao.getInvoice(invoiceId);
		session.getTransaction().commit();
		return mapper.writeValueAsString(invoice);
	}

	/**
	 * get all invoices in DB
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllInvoices() throws JsonGenerationException, JsonMappingException, IOException{

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Invoice> invoices = dao.getAllInvoices();
		session.getTransaction().commit();
		return mapper.writeValueAsString(invoices);
	}

	/**
	 * Get all invoices in a period from a user
	 * @param start
	 * @param end
	 * @return
	 */
	@GET
	@Path("user/{id:[0-9]+}/period/{start:[0-9]+}/{end:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllInvoicesInPeriod(@PathParam("id") int userId, @PathParam("start") int start, @PathParam("end") int end){

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return "";
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String addInvoice(String json) throws JsonParseException, JsonMappingException, IOException{
		System.out.println("test:");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Invoice invoice = mapper.readValue(json, Invoice.class);
		
		dao.addInvoice(invoice);
		session.getTransaction().commit();
		System.out.println("test:" + invoice.toString());
		return "{\"url\":\"rest/invoice/" + invoice.getInvoiceId() + "\", \"id\":\"" + invoice.getInvoiceId() + "\"}";
	}

	@PUT
	@Path("{id:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateInvoice(@PathParam("id") int invoiceId, String json) throws JsonParseException, JsonMappingException, IOException{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Invoice invoice = mapper.readValue(json, Invoice.class);
		invoice.setInvoiceId(invoiceId);
		session.beginTransaction();
		dao.updateInvoice(invoice);
		session.getTransaction().commit();
	}

	@DELETE
	@Path("{id:[0-9]+}")
	public void removeInvoice(@PathParam("id") int invoiceId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		dao.deleteInvoice(invoiceId);
		session.getTransaction().commit();
	}
	
	@GET
	@Path("update")
	public void update(){

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		if(!session.getTransaction().isActive())
//			session.beginTransaction();
//		dao.updateDates();
//		session.getTransaction().commit();
	}
}




















