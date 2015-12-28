package albert.lacambra.factures.api.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import albert.lacambra.factures.api.HibernateUtil;
import albert.lacambra.factures.api.models.Person;

public class PersonDAO {

	public void insertPerson(Person person){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.save(person);
	}
	
	public Person selectPerson(int personId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = session.getNamedQuery("person.select").setParameter("personId", personId);
		return (Person) q.uniqueResult();
	}
	
	public void removePerson(int personId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = session.getNamedQuery("person.delete").setParameter("personId", personId);
		q.executeUpdate();
	}
	
	public List<Person> getPersonsList()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = session.getNamedQuery("person.select.all");
		@SuppressWarnings("unchecked")
		List<Person> list = q.list();
		return list;
	}
}
