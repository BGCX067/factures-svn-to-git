package albert.lacambra.factures.app.rest;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestBuilder.Method;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
 
public class Base {
	private String endPoint;
	
	public Base(String endPoint){
		this.endPoint = endPoint;  
	}
	
	public void get(Integer id, String resource, final RequestCallback callback) throws RequestException{
		makeRequest(null, buildUrl(id, resource), callback, RequestBuilder.GET);
	}
	
	public void get(String resource, RequestCallback callback) throws RequestException{
		get(null, resource, callback);
	}
	
	public void put(String data, Integer id, String resource,final RequestCallback callback) throws RequestException{
		
		makeRequest(data, buildUrl(id, resource) , callback, RequestBuilder.PUT);
	}
	
	public void put(String data, String resource, final RequestCallback callback) throws RequestException{
		put(data, null, resource, callback);
	}
	
	public void delete(Integer id, String resource, final RequestCallback callback) throws RequestException{
		makeRequest(null, buildUrl(id, resource) ,callback, RequestBuilder.DELETE);
	}
	
	public void makeRequest(String data, String url, final RequestCallback callback, Method method) throws RequestException{
		
		if(data != null) {
			try{
				JSONParser.parseStrict(data);

			}catch(NullPointerException e){
				callback.onError(null, e);
				return;

			}catch(IllegalArgumentException e){
				callback.onError(null, e);
				return;
			}
		}
		
		RequestBuilder rb;
		
		rb = new RequestBuilder(method, url);
		
		if(data != null)
			rb.setRequestData(data);
		
		rb.setHeader("Content-Type", "application/json");
		
		rb.sendRequest(data, new RequestCallback(){

			@Override
			public void onResponseReceived(Request request, Response response) {

				String text = response.getText();

				if(text != null && !text.equals(""))
				{
					try{
						JSONParser.parseStrict(text);

					}catch(NullPointerException e){
						callback.onError(null, e);
						return;

					}catch(IllegalArgumentException e){
						callback.onError(null, e);
						return;
					}
				}
				callback.onResponseReceived(request, response);
			}

			@Override
			public void onError(Request request, Throwable exception) {
				callback.onError(request, exception);
			}
		});
	}
	
	protected boolean isValidCode(int code){
		return code < 400; 
	}
	
	protected String buildUrl(Integer id, String resource){
		
		return id == null ? endPoint + "/" + resource : endPoint + "/" + resource + "/" + id;
	}
}











































