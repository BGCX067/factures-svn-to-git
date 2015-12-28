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
@JsonIgnoreProperties({"payer"})
@NamedQueries({
	@NamedQuery(
			name="fixCost.select",
			query="from FixCost where fixCostId=:fixCostId"
			),
	@NamedQuery(
			name="fixCost.delete",
			query="delete from FixCost where fixCostId=:fixCostId"
			),
	@NamedQuery(
			name="fixCost.select.byUser",
			query="from FixCost where person=:person"
			),
	@NamedQuery(
			name="fixCost.select.all",
			query="from FixCost where active=1"
			),
})
public class FixCost {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int fixCostId = -1;
	
	@Transient
	private Person payer;
	
	private int personId;
	private float quota;
	private boolean active;
	
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	/**
	 * Times per year
	 */
	private int frequency;
	
	private String category;
	private String extra;
	
	
	public FixCost(){}
	public FixCost(int personId, float quantity, int frequency) {
		
		this();
		this.personId = personId;
		this.quota = quantity;
		this.frequency = frequency;
	}
	public FixCost(Person payer, int personId, float quantity, int frequency) {
		
		this(personId, quantity, frequency);
		this.payer = payer;
	}
	
	public int getFixCostId() {
		return fixCostId;
	}
	public void setFixCostId(int fixCostId) {
		this.fixCostId = fixCostId;
	}
	
	public Person getPayer() {
		return payer;
	}
	public void setPayer(Person payer) {
		this.payer = payer;
	}
	public float getQuota() {
		return quota;
	}
	public void setQuota(float quantity) {
		this.quota = quantity;
	}
	
	@Override
	public String toString(){
		return fixCostId + " " + personId + " " + quota;
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
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}




























