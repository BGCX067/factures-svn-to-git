package albert.lacambra.factures.app.presenter.invoicelist;

import java.util.ArrayList;
import java.util.List;

import albert.lacambra.factures.app.ClientFactory;
import albert.lacambra.factures.app.events.InvoiceAddedEvent;
import albert.lacambra.factures.app.events.InvoiceAddedHandler;
import albert.lacambra.factures.app.filters.InvoiceListFilters;
import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.presenter.newinvoice.NewInvoicePlace;
import albert.lacambra.factures.app.rest.PayableCallback;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class InvoiceListActivity extends AbstractActivity implements InvoiceListView.InvoiceListPresenter
{

	@SuppressWarnings("unused")
	private InvoiceListPlace place;
	private ClientFactory clientFactory;
	private InvoiceListProvider provider;
	private List<Invoice> invoicesList = new ArrayList<Invoice>();
	private InvoiceListFilters filter = new InvoiceListFilters();
	
	public InvoiceListActivity(InvoiceListPlace place, ClientFactory clientFactory)
	{
		this.place = place;
		this.clientFactory = clientFactory;
		this.provider = new InvoiceListProvider();
		this.provider.addDataDisplay(clientFactory.getInvoiceListView().getCellList());
		initEventHandlers();
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) 
	{
		clientFactory.getInvoiceListView().setPresenter(this);
		loadInvoiceList();
		panel.setWidget(clientFactory.getInvoiceListView().asWidget());
	}
	
	private void loadInvoiceList()
	{
		try {
			clientFactory.getRestClient().getAllInvoices(new PayableCallback<List<Invoice>>() {
				
				@Override
				public void onSuccess(List<Invoice> ressource) {
					invoicesList = ressource;
					setPartialList(invoicesList);
				}

				@Override
				public void onFailure(Throwable e) {
					
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	private void setPartialList(List<Invoice> partialList)
	{
		float totalPrice = filter.getTotalFromList(partialList);
		
		provider.loadData(partialList);
		clientFactory.getInvoiceListView().updateInvoiceList();
		clientFactory.getInvoiceListView().setTotalPrice(String.valueOf(totalPrice));
		clientFactory.getInvoiceListView().setDates(filter.getFilterDates(invoicesList));
		clientFactory.getInvoiceListView().setCategories(filter.getCategoryFilters(invoicesList));
		clientFactory.getInvoiceListView().setOwner(filter.getOwnerFilters(invoicesList));
	}
	
	@Override
	public void invoiceUpdate(Invoice invoice) {
		try {
			clientFactory.getRestClient().updateFactura(invoice, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
					
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void invoiceDelete(int invoiceId) {
	}

	@Override
	public void goToAddInvoice() {
		clientFactory.getPlaceController().goTo(new NewInvoicePlace());
	}

	@Override
	public InvoiceListProvider getDataProvider() {
		return this.provider;
	}
	
	private void initEventHandlers()
	{
		clientFactory.getEventBus().addHandler(InvoiceAddedEvent.TYPE, new InvoiceAddedHandler() {
			
			@Override
			public void onAddInvoice(InvoiceAddedEvent e) {
				invoicesList.add(e.getInvoice());
				provider.refresh();
			}
		});
	}
	
	@Override
	public void setListByCategory(String cat) {
		
		if(cat.equals("all")){ 
			setPartialList(invoicesList);
			return;
		}
			
		List<Invoice> list = new ArrayList<Invoice>();
		for(Invoice invoice : invoicesList) {
			if(invoice.getCategory().equals(cat)) {
				list.add(invoice);
			}
		}
		
		setPartialList(list);
	}
	
	@Override
	public void setListByDate(String date) {
		
		if(date.equals("all")){ 
			setPartialList(invoicesList);
			return;
		}
			
		List<Invoice> list = this.filter.filterDateInvoice(date, invoicesList);
		setPartialList(list);
	}

	@Override
	public void setListByOwner(String owner) {
		if(owner.equals("all")){ 
			setPartialList(invoicesList);
			return;
		}
			
		List<Invoice> list = new ArrayList<Invoice>();
		for(Invoice invoice : invoicesList) {
			String ownerId = String.valueOf(invoice.getOwnerId());
			if(ownerId.equals(owner)) {
				list.add(invoice);
			}
		}
		
		setPartialList(list);
	}
}
















































