package albert.lacambra.factures.api;

import static org.junit.Assert.*;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import albert.lacambra.factures.api.HibernateUtil;
import albert.lacambra.factures.api.dao.PersonDAO;
import albert.lacambra.factures.api.models.Person;


public class PersonsDAO {

	Session session;
	PersonDAO dao = new PersonDAO();
	Person p1;
	Person p2;

	@Before 
	public void setUp() 
	{
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	@Test
	public void getPersons()
	{
		loadPersons();
		
		assertTrue(p1.getPersonId() == -1);
		assertTrue(p2.getPersonId() == -1);
		
		session.save(p1);
		session.save(p2);
		
		int id1 = p1.getPersonId();
		int id2 = p2.getPersonId();
		
		session.clear();
		
		List<Person> list = dao.getPersonsList();
		assertTrue(list.size() == 2);
		assertTrue(list.get(0).getPersonId() == id1);
		assertTrue(list.get(1).getPersonId() == id2);
	}

	@After
	public void end()
	{
		session.getTransaction().commit();
	}
	
	private void loadPersons()
	{
		p1 = new Person("albert", "lacambra");
		p2 = new Person("ruth", "friedrich");
	}
}


