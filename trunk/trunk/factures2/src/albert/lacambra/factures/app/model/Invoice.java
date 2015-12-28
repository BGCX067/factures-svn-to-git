package albert.lacambra.factures.app.model;

import albert.lacambra.factures.app.model.jso.InvoiceJso;

import com.google.gwt.view.client.ProvidesKey;

public class Invoice  extends Payable
{
	private InvoiceJso jso;
	
	public static final ProvidesKey<Invoice> KEY_PROVIDER = new ProvidesKey<Invoice>() {
		public Object getKey(Invoice item) {
			return item == null ? null : item.getId();
		}
	};
	
	public Invoice()
	{
		jso = InvoiceJso.buildInvoce();
	}
	
	public Invoice(String json)
	{
		jso = InvoiceJso.buildInvoce(json);
	}

	public int getId() {
		return jso.getInvoiceId();
	}

	@Override
	public String serialize() {
		return jso.serialize();
	}

	@Override
	public void setId(int id) {
		jso.setInvoiceId(id);
		
	}

	@Override
	public int getOwnerId() {
		return jso.getPersonId();
	}

	@Override
	public float getMonthlyPrice() {
		return jso.getPrice();
	}

	@Override
	public String getCategory() {
		return jso.getCategory();
	}

	public void setCategory(String text) {
		jso.setCategory(text);
		
	}

	public void setDate(String date) {
		jso.setDate(date);
		
	}

	public void setExtra(String text) {
		jso.setExtra(text);
	}

	public void setPersonId(int who) {
		jso.setPersonId(who);
	}

	public void setPrice(Float price) {
		jso.setPrice(price);
	}

	public String getDate() {
		return jso.getDate();
	}

	public float getPrice() {
		return jso.getPrice();
	}

	public String getExtra() {
		return jso.getExtra();
	}
}
