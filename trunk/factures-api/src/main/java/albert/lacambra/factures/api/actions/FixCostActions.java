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
import albert.lacambra.factures.api.dao.FixCostDAO;
import albert.lacambra.factures.api.models.FixCost;

@Path("/fixcost")
public class FixCostActions {

	private ObjectMapper mapper = new ObjectMapper();
	private FixCostDAO dao = new FixCostDAO();
	/**
	 * get all costs from person
	 * @param userId
	 * @return
	 */
	@GET
	@Path("user/{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUserCosts(@PathParam("id") int userId){

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return "";
	}

	/**
	 * get an cost
	 * @param costId
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@GET
	@Path("{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCost(@PathParam("id") int costId) throws JsonGenerationException, JsonMappingException, IOException{

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		FixCost cost = dao.getCost(costId);
		session.getTransaction().commit();
		return mapper.writeValueAsString(cost);
	}

	/**
	 * get all costs in DB
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllCosts() throws JsonGenerationException, JsonMappingException, IOException{

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<FixCost> costs = dao.getAllCosts();
		session.getTransaction().commit();
		return mapper.writeValueAsString(costs);
	}

	/**
	 * Get all costs in a period from a user
	 * @param start
	 * @param end
	 * @return
	 */
	@GET
	@Path("user/{id:[0-9]+}/period/{start:[0-9]+}/{end:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllCostsInPeriod(@PathParam("id") int userId, @PathParam("start") int start, @PathParam("end") int end){

//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		return "";
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public String addCost(String json) throws JsonParseException, JsonMappingException, IOException{
		System.out.println("test:");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		FixCost cost = mapper.readValue(json, FixCost.class);
		
		dao.addCost(cost);
		session.getTransaction().commit();
		System.out.println("test:" + cost.toString());
		return "{\"url\":\"rest/fixcost/"  + cost.getFixCostId() + "\", \"id\":" + cost.getFixCostId() +"}";
	}

	@PUT
	@Path("{id:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateCost(@PathParam("id") int costId, String json) throws JsonParseException, JsonMappingException, IOException{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		FixCost cost = mapper.readValue(json, FixCost.class);
		cost.setFixCostId(costId);
		session.beginTransaction();
		dao.updateCost(cost);
		session.getTransaction().commit();
	}

	@DELETE
	@Path("{id:[0-9]+}")
	public void removeCost(@PathParam("id") int costId){
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
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




















