package albert.lacambra.factures.app.presenter.newinvoice;

import albert.lacambra.factures.app.ClientFactory;
import albert.lacambra.factures.app.events.InvoiceAddedEvent;
import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.rest.PayableCallback;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class NewInvoiceActivity extends AbstractActivity implements NewInvoiceView.NewInvoicePresenter{

	@SuppressWarnings("unused")
	private NewInvoicePlace place;
	private ClientFactory clientFactory;
	
	public NewInvoiceActivity(NewInvoicePlace place, ClientFactory clientFactory)
	{
		this.place = place;
		this.clientFactory = clientFactory;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		clientFactory.getNewInvoiceView().setPresenter(this);
		panel.setWidget(clientFactory.getNewInvoiceView().asWidget());
	}

	@Override
	public void addInvoice(Invoice invoice) {
		try {
			clientFactory.getRestClient().addInvoice(invoice, new PayableCallback<Invoice>() {
				
				@Override
				public void onSuccess(Invoice ressource) {
					clientFactory.getEventBus().fireEvent(new InvoiceAddedEvent(ressource));
					clientFactory.getNewInvoiceView().onSuccess(ressource.toString());
				}
				
				@Override
				public void onFailure(Throwable e) {
					
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
}
