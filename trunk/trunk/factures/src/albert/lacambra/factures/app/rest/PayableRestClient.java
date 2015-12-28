package albert.lacambra.factures.app.rest;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

import albert.lacambra.factures.app.ClientFactory;
import albert.lacambra.factures.app.events.PayableAddedEvent;
import albert.lacambra.factures.app.events.PayableAddedHandler;
import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.model.Payable;
import albert.lacambra.factures.app.model.Serializable;

public class PayableRestClient {

	private Base client;
	private String endPoint = "http://127.0.0.1:8888/rest"; 
	private List<Invoice> invoiceList;
	private List<FixCost> fixCostList;
	
	public PayableRestClient(ClientFactory factory){
		
		client = new Base(endPoint);
		factory.getEventBus().addHandlerToSource(PayableAddedEvent.TYPE, Payable.Type.FIX_COST, new PayableAddedHandler(){
			@Override
			public void onPayableAdded(PayableAddedEvent e) {
				if (fixCostList != null)
					fixCostList.add(e.getFixCost());
			}
		});
		
	}

	public void addInvoice(final Invoice invoice, final PayableCallback<Invoice> callback) throws RequestException{
		client.put(invoice.serialize(), "invoice", new RequestCallback(){

			@Override
			public void onResponseReceived(Request request, Response response) {
				callback.onSuccess(invoice);
			}

			@Override
			public void onError(Request request, Throwable exception) {
				callback.onFailure(exception);
			}
		});
	}
	
	public void updateFactura(Invoice factura, RequestCallback callback) throws RequestException{
		client.put(factura.serialize(),factura.getId(), "invoice", callback);
	}
	
	public void getAllInvoices(final PayableCallback<List<Invoice>> callback) throws RequestException
	{
		if(invoiceList != null) {
			callback.onSuccess(invoiceList);
			return;
		}
		
		client.get("invoice", new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				invoiceList = new ArrayList<Invoice>();
				String body = response.getText();
				JSONValue value = JSONParser.parseStrict(body);
				JSONArray list = value.isArray();
				for(int i = 0; i < list.size(); i++) {
					Invoice invoice = new Invoice(list.get(i).toString());
					invoiceList.add(invoice);
				}
				callback.onSuccess(invoiceList);
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				callback.onFailure(exception);
			}
		});
	}
	
	public void getAllFixCosts(final PayableCallback<List<FixCost>> callback) throws RequestException
	{
		if(fixCostList != null) {
			callback.onSuccess(fixCostList);
			return;
		}
		
		client.get("fixcost", new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request request, Response response) {
				fixCostList = new ArrayList<FixCost>();
				String body = response.getText();
				JSONValue value = JSONParser.parseStrict(body);
				JSONArray list = value.isArray();
				for(int i = 0; i < list.size(); i++) {
					FixCost fixCost = new FixCost(list.get(i).toString());
					fixCostList.add(fixCost);
				}
				callback.onSuccess(fixCostList);
			}
			
			@Override
			public void onError(Request request, Throwable exception) {
				callback.onFailure(exception);
			}
		});
	}

	 public <T extends Payable> void addPayable(final T fixCost, final PayableCallback<T> callback, String ressourceName) throws RequestException {
		client.put(fixCost.serialize(), ressourceName, new RequestCallback(){

			@Override
			public void onResponseReceived(Request request, Response response) {
				JsonPutResponse resp = JsonPutResponse.buildInvoce(response.getText());
				fixCost.setId(resp.getId());
				callback.onSuccess(fixCost);
			}

			@Override
			public void onError(Request request, Throwable exception) {
				callback.onFailure(exception);
			}
		});
	}

	public <T extends Payable> void updatePayable(final T payable, final PayableCallback<T> callback, String ressourceName, int id) throws RequestException {
		client.put(payable.serialize(), id, ressourceName, new RequestCallback(){

			@Override
			public void onResponseReceived(Request request, Response response) {
				callback.onSuccess(payable);
			}

			@Override
			public void onError(Request request, Throwable exception) {
				callback.onFailure(exception);
			}
		});
	}
}




























































