package albert.lacambra.factures.app.presenter;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.PopupView;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasText;

public class ConfirmationPresenter extends PresenterWidget<ConfirmationPresenter.MyView> {

	public interface MyView extends PopupView {

		HasClickHandlers getOkBtn();
		HasClickHandlers getCancelBtn();
		HasText getTextLabel();
	}
	
	public interface Callback{
		public void confirmed();
	}

	@Inject
	public ConfirmationPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}
	
	public void setCallBack(final Callback callback) {
		registerHandler(getView().getOkBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				callback.confirmed();
				getView().hide();
			}
		}));
	}
	
	public void setInfoText(String text) {
		getView().getTextLabel().setText(text);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		registerHandler(getView().getCancelBtn().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				getView().hide();
			}
		}));
	}
	
	
}
