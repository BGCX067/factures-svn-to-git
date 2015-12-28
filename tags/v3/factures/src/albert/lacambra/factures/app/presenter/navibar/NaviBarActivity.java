package albert.lacambra.factures.app.presenter.navibar;

import albert.lacambra.factures.app.ClientFactory;
import albert.lacambra.factures.app.presenter.fixcost.FixCostListPlace;
import albert.lacambra.factures.app.presenter.invoicelist.InvoiceListPlace;
import albert.lacambra.factures.app.presenter.newfixcost.NewFixCostPlace;
import albert.lacambra.factures.app.presenter.newinvoice.NewInvoicePlace;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class NaviBarActivity extends AbstractActivity implements NaviBarView.Presenter{

	private ClientFactory clientFactory;
	
	public static final int GO_TO_NEW_INVOICE 	= 0;
	public static final int GO_TO_NEW_FIXCOST 	= 1;
	public static final int GO_TO_INVOICE_LIST 	= 2;
	public static final int GO_TO_COST_LIST 	= 3;
	public static final int GO_TO_ALL		 	= 4;
	
	public NaviBarActivity(ClientFactory clientFactory)
	{
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		clientFactory.getNaviBarView().setPresenter(this);
		panel.setWidget(clientFactory.getNaviBarView().asWidget());
	}

	@Override
	public void goToPlace(int place) {
		switch(place){
		case GO_TO_ALL:
			break;
		case GO_TO_COST_LIST:
			clientFactory.getPlaceController().goTo(new FixCostListPlace());
			break;
		case GO_TO_INVOICE_LIST:
			clientFactory.getPlaceController().goTo(new InvoiceListPlace());
			break;
		case GO_TO_NEW_FIXCOST:
			clientFactory.getPlaceController().goTo(new NewFixCostPlace());
			break;
		case GO_TO_NEW_INVOICE:
			clientFactory.getPlaceController().goTo(new NewInvoicePlace());
			break;
		}
	}
	
}
