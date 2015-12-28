package albert.lacambra.factures.app.model.jso;

import albert.lacambra.factures.app.model.Invoice;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.view.client.ProvidesKey;


public class InvoiceJso extends JavaScriptObject
{
	protected InvoiceJso(){}
	
	public static final ProvidesKey<Invoice> KEY_PROVIDER = new ProvidesKey<Invoice>() {
		public Object getKey(Invoice item) {
			return item == null ? null : item.getId();
		}
	};
	    
	public final int getId()
	{
		GWT.log(toString());
		return getInvoiceId();
	}

	public final native int getInvoiceId() /*-{ 
        return this.invoiceId;
    }-*/;

	public final native void setInvoiceId(int value) /*-{
        this.invoiceId = value;
    }-*/;

	public final native int getPersonId() /*-{
        return this.personId;
    }-*/;

	public final native void setPersonId(int value) /*-{
        this.personId = value;
    }-*/;

	public final native float getPrice() /*-{ 
	    return this.price;
	}-*/;

	public final native void setPrice(float value) /*-{
	    this.price = value; 
	}-*/;

	public final native String getCategory() /*-{
	    return this.category;
	}-*/;
	
	public final native void setCategory(String value) /*-{
	    this.category = value; 
	}-*/;

	public final native String getExtra() /*-{
	    return this.extra;
	}-*/;

	public final native void setExtra(String value) /*-{
		this.extra = value; 
	}-*/;

	public final native String getDate() /*-{
		return this.date;
	}-*/;

	public final native void setDate(String value) /*-{
	    this.date = value;
	}-*/;

	public static final native InvoiceJso buildInvoce(String json) /*-{
	    return eval('(' + json + ')');
	}-*/;

	public static final native InvoiceJso buildInvoce() /*-{
	    return {};
	}-*/;

	public final String serialize(){
		String json = new JSONObject(this).toString();
		return json;
	}
	
	public final String print()
	{
		return getInvoiceId() + ":" + getDate() + ":" + getPrice()  + ":" + getCategory() + ":" +getExtra();
	}
}





























