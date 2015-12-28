package albert.lacambra.factures.app.model;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public class People extends JavaScriptObject
{
	public People() {}
	
	public final native int getPersonId() /*-{
	    return this.personId;
	}-*/;
	
	public final native void setPersonId(int value) /*-{
	    this.personId = value;
	}-*/;
	
	public static final native People buildPerson(String json) /*-{
	    return eval('(' + json + ')');
	}-*/;
	
	public static final native Invoice buildPerson() /*-{
	    return {};
	}-*/;
	
	public final String serialize(){
		String json = new JSONObject(this).toString();
		return json;
	}
}
