package albert.lacambra.factures.app.rest;

import com.google.gwt.core.client.JavaScriptObject;

public class JsonPutResponse extends JavaScriptObject
{
	protected JsonPutResponse(){}

	public final native String getUrl() /*-{
		return this.url;
	}-*/;
	
	public final native int getId() /*-{
		return this.id;
	}-*/;
	
	public static final native JsonPutResponse buildInvoce(String json) /*-{
	    return eval('(' + json + ')');
	}-*/;
}
