package albert.lacambra.factures.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(
			name="person.select.all",
			query="from Person"
			),
	@NamedQuery(
			name="person.select",
			query="from Person where personId=:personId"
			),
	@NamedQuery(
			name="person.delete",
			query="delete from Person where personId=:personId"
			),
})
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int personId = -1;
	private String name;
	private String lastname;

	public Person(){}
	
	public Person(String name, String lastname) {
		this();
		this.name = name;
		this.lastname = lastname;
	}
	
	public Person(int personId, String name, String lastname) {
		this(name, lastname);
		this.personId = personId;
	}
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
