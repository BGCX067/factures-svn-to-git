package albert.lacambra.factures.app.presenter.fixcost;

import albert.lacambra.factures.app.ClientFactory;
import albert.lacambra.factures.app.events.PayableAddedEvent;
import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.model.Payable;
import albert.lacambra.factures.app.presenter.newfixcost.NewFixCostPlace;
import albert.lacambra.factures.app.presenter.newfixcost.NewFixCostView;
import albert.lacambra.factures.app.rest.PayableCallback;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class NewFixCostActivity extends AbstractActivity implements NewFixCostView.NewInvoicePresenter{

	@SuppressWarnings("unused")
	private NewFixCostPlace place;
	private ClientFactory clientFactory;
	
	public NewFixCostActivity(NewFixCostPlace place, ClientFactory clientFactory)
	{
		this.place = place;
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		clientFactory.getNewFixCostView().setPresenter(this);
		panel.setWidget(clientFactory.getNewFixCostView().asWidget());
	}

	@Override
	public void addFixCost(FixCost fixCost) {
		try {
			clientFactory.getRestClient().addPayable(fixCost, new PayableCallback<FixCost>() {
				
				@Override
				public void onSuccess(FixCost ressource) {
					clientFactory.getEventBus().fireEventFromSource(new PayableAddedEvent(ressource), Payable.Type.FIX_COST);
					clientFactory.getNewFixCostView().onSuccess(ressource.toString());
				}
				
				@Override
				public void onFailure(Throwable e) {
					
				}
					
			}, "fixcost");
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
}
