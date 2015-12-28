package albert.lacambra.factures.app.view.desktop;

import albert.lacambra.factures.app.presenter.MasterPresenter;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MasterView extends ViewImpl implements MasterPresenter.MyView {

	private final Widget widget;

	@UiField HTMLPanel container;
	@UiField Button goToNewInvoice;
	@UiField Button goToNewFixCost;
	@UiField Button goToInvoiceList;
	@UiField Button goToCostList;
	@UiField Button goToCharts;

	public interface Binder extends UiBinder<Widget, MasterView> {
	}

	@Inject
	public MasterView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == MasterPresenter.TYPE_MainContent) {
			setMainContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setMainContent(Widget content) {
		container.clear();

		if (content != null) {
			container.add(content);
		}
	}

	public HTMLPanel getContainer() {
		return container;
	}

	public Button getGoToNewInvoice() {
		return goToNewInvoice;
	}

	@Override
	public Button getGoToNewFixCost() {
		return goToNewFixCost;
	}

	@Override
	public Button getGoToInvoiceList() {
		return goToInvoiceList;
	}

	@Override
	public Button getGoToCostList() {
		return goToCostList;
	}
	
	@Override
	public Button getGoToCharts() {
		return goToCharts;
	}
	
	
}
