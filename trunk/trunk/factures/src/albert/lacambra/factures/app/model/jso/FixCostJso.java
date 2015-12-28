package albert.lacambra.factures.app.model.jso;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;

public class FixCostJso extends JavaScriptObject
{
	protected FixCostJso(){}
	
	public final native int getFixCostId() /*-{ 
        return this.fixCostId;
    }-*/;

	public final native void setFixCostId(int value) /*-{
        this.fixCostId = value;
    }-*/;

	public final native int getPersonId() /*-{
        return this.personId;
    }-*/;

	public final native void setPersonId(int value) /*-{
        this.personId = value;
    }-*/;

	public final native float getQuota() /*-{ 
	    return this.quota;
	}-*/;

	public final native void setQuota(float value) /*-{
	    this.quota = value; 
	}-*/;
	
	public final native int getFrequency() /*-{ 
	    return this.frequency;
	}-*/;

	public final native void setFrequency(int value) /*-{
	    this.frequency = value; 
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

	public final native String getEndDate() /*-{
		return this.endDate;
	}-*/;

	public final native void setEndDate(String value) /*-{
	    this.endDate = value;
	}-*/;
	
	public final native String getStartDate() /*-{
		return this.startDate;
	}-*/;

	public final native void setStartDate(String value) /*-{
	    this.startDate = value;
	}-*/;

	public static final native FixCostJso buildFixCost(String json) /*-{
	    return eval('(' + json + ')');
	}-*/;

	public static final native FixCostJso buildFixCost() /*-{
	    return {};
	}-*/;
	
	public final native void setActive(boolean value) /*-{
		this.active = value;
	}-*/;
	
	public final native boolean getActive(boolean value) /*-{
		return this.active;
	}-*/;
	
	public final String serialize(){
		String json = new JSONObject(this).toString();
		return json;
	}
}




























