package albert.lacambra.factures.api.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@JsonIgnoreProperties({"payer", "timestamp"})
@NamedQueries({
	@NamedQuery(
			name="invoice.select",
			query="from Invoice where idInvoice=:idInvoice"
			),
	@NamedQuery(
			name="invoice.delete",
			query="delete from Invoice where idInvoice=:idInvoice"
			),
	@NamedQuery(
			name="invoice.select.byUser",
			query="from Invoice where person=:person"
			),
	@NamedQuery(
			name="invoice.select.all",
			query="from Invoice"
			),
})
public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idInvoice = -1;
	
	@Transient
	private Person payer;
	
	private int personId;
	private float price;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private String category;
	private String extra;
	
	
	public Invoice(){}
	public Invoice(int personId, float quantity, Date date) {
		
		this();
		this.personId = personId;
		this.price = quantity;
		this.date = date;
	}
	public Invoice(Person payer, int personId, float quantity, Date date) {
		
		this(personId, quantity, date);
		this.payer = payer;
	}
	
	public int getInvoiceId() {
		return idInvoice;
	}
	public void setInvoiceId(int invoiceId) {
		this.idInvoice = invoiceId;
	}
	
	public Person getPayer() {
		return payer;
	}
	public void setPayer(Person payer) {
		this.payer = payer;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float quantity) {
		this.price = quantity;
	}
	
	@Override
	public String toString(){
		return idInvoice + " " + personId + " " + price;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String comments) {
		this.extra = comments;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	
	
}




























