package albert.lacambra.factures.app;

import albert.lacambra.factures.app.presenter.fixcost.FixCostListView;
import albert.lacambra.factures.app.presenter.invoicelist.InvoiceListView;
import albert.lacambra.factures.app.presenter.navibar.NaviBarView;
import albert.lacambra.factures.app.presenter.newfixcost.NewFixCostView;
import albert.lacambra.factures.app.presenter.newinvoice.NewInvoiceView;
import albert.lacambra.factures.app.rest.PayableRestClient;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {
	public EventBus getEventBus();
	public PayableRestClient getRestClient();
	public NewInvoiceView getNewInvoiceView();
	public InvoiceListView getInvoiceListView();
	PlaceController getPlaceController();
	public NaviBarView getNaviBarView();
	public NewFixCostView getNewFixCostView();
	public FixCostListView getFixCostListView();
} 