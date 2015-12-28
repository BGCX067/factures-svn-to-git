package albert.lacambra.factures.app.presenter;

import java.util.ArrayList;
import java.util.List;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;

import albert.lacambra.factures.app.events.ApplyFilterEvent;
import albert.lacambra.factures.app.events.InvoicesListReceivedEvent;
import albert.lacambra.factures.app.filters.IsFilter;
import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.place.NameTokens;
import albert.lacambra.factures.app.presenter.ConfirmationPresenter.Callback;
import albert.lacambra.factures.app.presenter.MasterPresenter;
import albert.lacambra.factures.app.rest.PayableCallback;
import albert.lacambra.factures.app.rest.PayableRestClient;
import albert.lacambra.factures.app.view.desktop.InvoiceCellList;

import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.view.client.ListDataProvider;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class InvoiceListPresenter extends Presenter<InvoiceListPresenter.MyView, InvoiceListPresenter.MyProxy> 
	{

	public static final Object SLOT_filters = new Object(); 

	private FiltersPresenter<Invoice> filtersPresenter;
	private PayableRestClient restClient;
	private ListDataProvider<Invoice> provider;
	private ArrayList<IsFilter<Invoice>> appliedFilters;
	private List<Invoice> unfilteredResources;
	private ConfirmationPresenter confirmationPresenter;

	public interface MyView extends View {
		public InvoiceCellList getInvoiceCellList();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.invocelist)
	public interface MyProxy extends ProxyPlace<InvoiceListPresenter> {
	}

	@Inject
	public InvoiceListPresenter(
			final EventBus eventBus, 
			final MyView view, final 
			MyProxy proxy, 
			PayableRestClient restClient, 
			FiltersPresenter<Invoice> filtersPresenter, 
			ConfirmationPresenter confirmationPresenter) {
		super(eventBus, view, proxy);
		this.confirmationPresenter = confirmationPresenter;
		this.restClient = restClient;
		this.filtersPresenter = filtersPresenter;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MasterPresenter.TYPE_MainContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		provider = new ListDataProvider<Invoice>();
		provider.addDataDisplay(getView().getInvoiceCellList());
		appliedFilters = new ArrayList<IsFilter<Invoice>>();

		setInSlot(SLOT_filters, filtersPresenter);

		loadInvoiceList();

		getEventBus().addHandler(ApplyFilterEvent.TYPE, new ApplyFilterEvent.ApplyFilterHandler() {

			@Override
			public void onApplyFilter(ApplyFilterEvent event) {
				applyFilter();
			}
		});
	}

	private void applyFilter() {

		provider.setList(filtersPresenter.filterList(unfilteredResources));
		getView().getInvoiceCellList().loadSortHandlers(provider.getList());

		provider.refresh();

		filtersPresenter.getView().getPriceLabel().setText(String.valueOf(calculateTotal()));
	}

	private float calculateTotal(){

		float price = 0;

		for(Invoice i : provider.getList()) {
			price = price + i.getMonthlyPrice();
		}

		return price;
	}

	private void refreshAllFilters() {

		List<Invoice> toFilter = unfilteredResources;

		for(IsFilter<Invoice> filter : appliedFilters) {
			toFilter = filter.doFilter(toFilter);
		}

		provider.setList(toFilter);
		provider.refresh();
	}

	private void loadInvoiceList()
	{
		try {
			restClient.getAllInvoices(new PayableCallback<List<Invoice>>() {

				@Override
				public void onSuccess(List<Invoice> ressource) {
					unfilteredResources = ressource;
					refreshAllFilters();
					getView().getInvoiceCellList().build(InvoiceListPresenter.this, provider.getList());
					provider.refresh();
					filtersPresenter.loadFilters(ressource);
					getEventBus().fireEvent(new InvoicesListReceivedEvent(unfilteredResources));
					filtersPresenter.getView().getPriceLabel().setText(String.valueOf(calculateTotal()));
				}

				@Override
				public void onFailure(Throwable e) {
					System.out.println("ko");
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}

	public void invoiceUpdate(Invoice invoice) {
		try {
			restClient.updateFactura(invoice, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request, Response response) {
					System.out.println("updated");
				}

				@Override
				public void onError(Request request, Throwable exception) {
					System.out.println("error");
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}		
	}

	public void deleteInvoice(final Invoice invoice) {
		confirmationPresenter.setInfoText("Segur que vols esborrar la factura? " + invoice);
		confirmationPresenter.getView().show();
		confirmationPresenter.setCallBack(new Callback() {
			
			@Override
			public void confirmed() {
				restClient.delete(new RequestCallback() {

					@Override
					public void onResponseReceived(Request request, Response response) {
						System.out.println("deleted");
						provider.getList().remove(invoice);
					}

					@Override
					public void onError(Request request, Throwable exception) {
						System.out.println("error");
					}
				}, "invoice", invoice.getId());
			}
		});
	}

}


































