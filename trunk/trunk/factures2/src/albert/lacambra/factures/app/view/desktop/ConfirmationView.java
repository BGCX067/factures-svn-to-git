package albert.lacambra.factures.app.view.desktop;

import albert.lacambra.factures.app.presenter.ConfirmationPresenter;
import albert.lacambra.factures.app.presenter.ConfirmationPresenter.MyView;

import com.gwtplatform.mvp.client.PopupViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

public class ConfirmationView extends PopupViewImpl implements
		ConfirmationPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, ConfirmationView> {
	}

	@Inject
	public ConfirmationView(final EventBus eventBus, final Binder binder) {
		super(eventBus);
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public HasClickHandlers getOkBtn() {
		return okBtn;
	}
	
	@Override
	public HasClickHandlers getCancelBtn() {
		return cancelBtn;
	}
	
	@Override
	public HasText getTextLabel() {
		return textLabel;
	}
	
	@UiField Button okBtn;
	@UiField Button cancelBtn;
	@UiField Label textLabel;
}
