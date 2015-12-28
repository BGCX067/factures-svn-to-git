package albert.lacambra.factures.app.presenter;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;

import albert.lacambra.factures.app.place.NameTokens;
import albert.lacambra.factures.app.presenter.MasterPresenter;

import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class NewFixCostPresenter extends
		Presenter<NewFixCostPresenter.MyView, NewFixCostPresenter.MyProxy> {

	public interface MyView extends View {
		public Button getButton();
		public RadioButton getWhoAlbert();
		public RadioButton getWhoRuth();
		public RadioButton getWhoBoth();
		public RadioButton getWhoUnknown();
		public TextBox getPrice();
		public TextBox getStartDay();
		public TextBox getStartMonth();
		public TextBox getStartYear();
		public TextBox getEndDay();
		public TextBox getEndMonth();
		public TextBox getEndYear();
		public TextBox getCategory();
		public TextBox getExtra();
		public Label getInfoLabel();
		public TextBox getFrequency();
		public CheckBox getActive();
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.newfixcost)
	public interface MyProxy extends ProxyPlace<NewFixCostPresenter> {
	}

	@Inject
	public NewFixCostPresenter(final EventBus eventBus, final MyView view,
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
	}
}
