package albert.lacambra.factures.api.actions;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;

import albert.lacambra.factures.api.HibernateUtil;
import albert.lacambra.factures.api.dao.PersonDAO;
import albert.lacambra.factures.api.models.Person;

@Path("/person")
public class PersonAction {
	
	private ObjectMapper mapper = new ObjectMapper();
	private PersonDAO dao = new PersonDAO();
	
	@GET
	@Path("{id:[0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPerson(@PathParam("id") int personId) throws JsonGenerationException, JsonMappingException, IOException{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person p = dao.selectPerson(personId);
		session.getTransaction().commit();
		return mapper.writeValueAsString(p);
	}
	
	@PUT
	@Path("{id:[0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updatePersonData(@PathParam("id") int personId, String personData){
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPerson(String personData){
		
	}
	
	@DELETE
	@Path("{id:[0-9]+}")
	public void removePerson(@PathParam("id") int personId){
		
	}
}
