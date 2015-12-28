package albert.lacambra.factures.app.presenter;

import albert.lacambra.factures.app.place.NameTokens;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MasterPresenter extends Presenter<MasterPresenter.MyView, MasterPresenter.MyProxy> {

	@ContentSlot 
	public static final Type<RevealContentHandler<?>> TYPE_MainContent = new Type<RevealContentHandler<?>>();
	
	@Inject PlaceManager placeManager;

	public interface MyView extends View {
		public HTMLPanel getContainer();
		public Button getGoToNewInvoice() ;
		public Button getGoToNewFixCost();
		public Button getGoToInvoiceList();
		public Button getGoToCostList();
		Button getGoToCharts();
	}

	@ProxyCodeSplit
	public interface MyProxy extends Proxy<MasterPresenter> {
	}

	@Inject
	public MasterPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		getView().getGoToNewInvoice().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest request = new PlaceRequest(NameTokens.newinvoice);
				placeManager.revealPlace(request);
			}
		});
		
		
		getView().getGoToCostList().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest request = new PlaceRequest(NameTokens.fixcostlist);
				placeManager.revealPlace(request);
			}
		});
		getView().getGoToInvoiceList().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest request = new PlaceRequest(NameTokens.invocelist);
				placeManager.revealPlace(request);
			}
		});
		getView().getGoToNewFixCost().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest request = new PlaceRequest(NameTokens.newfixcost);
				placeManager.revealPlace(request);
			}
		});
		getView().getGoToCharts().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				PlaceRequest request = new PlaceRequest(NameTokens.charts);
				placeManager.revealPlace(request);
			}
		});
	}
}





























































