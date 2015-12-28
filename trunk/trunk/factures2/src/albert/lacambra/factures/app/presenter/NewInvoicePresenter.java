package albert.lacambra.factures.app.presenter;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;

import albert.lacambra.factures.app.events.InvoiceAddedEvent;
import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.place.NameTokens;
import albert.lacambra.factures.app.presenter.MasterPresenter;
import albert.lacambra.factures.app.rest.PayableCallback;
import albert.lacambra.factures.app.rest.PayableRestClient;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class NewInvoicePresenter extends Presenter<NewInvoicePresenter.MyView, NewInvoicePresenter.MyProxy> {

	@Inject EventBus eventBus;
	@Inject private PayableRestClient restClient;
	
	
	public interface MyView extends View {
		public Button getButton();
		public RadioButton getWhoAlbert();
		public RadioButton getWhoRuth();
		public RadioButton getWhoBoth();
		public RadioButton getWhoUnknown();
		public TextBox getPrice();
		public TextBox getDay();
		public TextBox getMonth();
		public TextBox getYear();
		public TextBox getCategory();
		public TextBox getExtra();
		public Label getInfoLabel();
		void restartFields();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.newinvoice)
	public interface MyProxy extends ProxyPlace<NewInvoicePresenter> {
	}

	@Inject
	public NewInvoicePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MasterPresenter.TYPE_MainContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Invoice invoice = new Invoice();
				invoice.setCategory(getView().getCategory().getText());
				invoice.setDate(getDate());
				invoice.setExtra(getView().getExtra().getText());
				invoice.setPersonId(getWho());
				invoice.setPrice(new Float(getView().getPrice().getText()));
				addInvoice(invoice);
			}
		});
	}
	
	public void addInvoice(Invoice invoice) {
		try {
			restClient.addInvoice(invoice, new PayableCallback<Invoice>() {
				
				@Override
				public void onSuccess(Invoice ressource) {
					eventBus.fireEvent(new InvoiceAddedEvent(ressource));
					getView().getInfoLabel().setText(ressource.toString());
					getView().restartFields();
				}
				
				@Override
				public void onFailure(Throwable e) {
					
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}
	
	private String getDate()
	{
		String y = getView().getYear().getValue().matches("^[0-9]{4}$") ? getView().getYear().getValue() : "0000";
		String m = getView().getMonth().getValue().matches("^[0-1]{0,1}[0-9]{1}$") ? getView().getMonth().getValue() : "00";
		String d = getView().getDay().getValue().matches("^[0-3]{0,1}[0-9]{1}$") ? getView().getDay().getValue() : "00";
		String date = y + "-" + m + "-" + d;
		
		return date;
	}
	
	private Integer getWho() 
	{
		if(getView().getWhoAlbert().getValue()) {
			return 1;
		}
		if(getView().getWhoRuth().getValue()) {
			return 2;
		}
		if(getView().getWhoBoth().getValue()) {
			return 3;
		}
		if(getView().getWhoUnknown().getValue()) {
			return 0;
		}
		return 3;
	}
}
