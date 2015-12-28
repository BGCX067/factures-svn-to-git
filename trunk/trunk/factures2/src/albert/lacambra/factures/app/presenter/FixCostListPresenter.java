package albert.lacambra.factures.app.presenter;

import java.util.ArrayList;
import java.util.List;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;

import albert.lacambra.factures.app.events.ApplyFilterEvent;
import albert.lacambra.factures.app.filters.IsFilter;
import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.place.NameTokens;
import albert.lacambra.factures.app.presenter.MasterPresenter;
import albert.lacambra.factures.app.rest.PayableCallback;
import albert.lacambra.factures.app.rest.PayableRestClient;
import albert.lacambra.factures.app.view.desktop.FixCostCellList;

import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.view.client.ListDataProvider;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class FixCostListPresenter extends Presenter<FixCostListPresenter.MyView, FixCostListPresenter.MyProxy> {

	public static final Object SLOT_filters = new Object(); 

	FiltersPresenter<FixCost> filtersPresenter;

	private PayableRestClient restClient;
	private ListDataProvider<FixCost> provider;

	final FixCostListPresenter self = this;

	private ArrayList<IsFilter<FixCost>> appliedFilters;
	private List<FixCost> unfilteredResources;

	public interface MyView extends View {

		FixCostCellList getFixCostCellList();


	}

	@ProxyCodeSplit
	@NameToken(NameTokens.fixcostlist)
	public interface MyProxy extends ProxyPlace<FixCostListPresenter> {
	}

	@Inject
	public FixCostListPresenter(
			final EventBus eventBus, 
			final MyView view, final 
			MyProxy proxy, 
			PayableRestClient restClient, 
			FiltersPresenter<FixCost> filtersPresenter) {

		super(eventBus, view, proxy);
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

		provider = new ListDataProvider<FixCost>();
		provider.addDataDisplay(getView().getFixCostCellList());
		appliedFilters = new ArrayList<IsFilter<FixCost>>();

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
		getView().getFixCostCellList().loadSortHandlers(provider.getList());

		provider.refresh();

		filtersPresenter.getView().getPriceLabel().setText(String.valueOf(calculateTotal()));
	}

	private float calculateTotal(){

		float price = 0;

		for(FixCost fc : provider.getList()) {
			price = price + fc.getMonthlyPrice();
		}

		return price;
	}

	private void refreshAllFilters() {

		List<FixCost> toFilter = unfilteredResources;

		for(IsFilter<FixCost> filter : appliedFilters) {
			toFilter = filter.doFilter(toFilter);
		}

		provider.setList(toFilter);
		provider.refresh();
	}

	private void loadInvoiceList()
	{
		try {
			restClient.getAllFixCosts(new PayableCallback<List<FixCost>>() {

				@Override
				public void onSuccess(List<FixCost> ressource) {
					unfilteredResources = ressource;
					refreshAllFilters();
					getView().getFixCostCellList().build(self, provider.getList());
					provider.refresh();
					filtersPresenter.loadFilters(ressource);
					//					getEventBus().fireEvent(new InvoicesListReceivedEvent(unfilteredResources));
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

	public void fixCostUpdate(FixCost object) {
		try {
			restClient.updateFixCost(object, new RequestCallback() {

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
}











































