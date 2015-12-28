package albert.lacambra.factures.app.view.desktop;

import albert.lacambra.factures.app.presenter.navibar.NaviBarActivity;
import albert.lacambra.factures.app.presenter.navibar.NaviBarView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class NaviBarViewImpl extends Composite implements NaviBarView {

	private static NaviBarViewImplUiBinder uiBinder = GWT
			.create(NaviBarViewImplUiBinder.class);
	
	private Presenter presenter;

	interface NaviBarViewImplUiBinder extends UiBinder<Widget, NaviBarViewImpl> {
	}

	public NaviBarViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField Button goToNewInvoice;
	@UiField Button goToNewFixCost;
	@UiField Button goToInvoiceList;
	@UiField Button goToCostList;
//	@UiField Button goToAll;
	

	public NaviBarViewImpl(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("goToNewInvoice")
	void ongoToNewInvoiceClick(ClickEvent e) {
		this.presenter.goToPlace(NaviBarActivity.GO_TO_NEW_INVOICE);
	}

	@UiHandler("goToNewFixCost")
	void onGoToNewCostClick(ClickEvent e) {
		this.presenter.goToPlace(NaviBarActivity.GO_TO_NEW_FIXCOST);
	}
	
	@UiHandler("goToInvoiceList")
	void onGoToInvoiceListClick(ClickEvent e) {
		this.presenter.goToPlace(NaviBarActivity.GO_TO_INVOICE_LIST);
	}
	
	@UiHandler("goToCostList")
	void onGoToCostListClick(ClickEvent e) {
		this.presenter.goToPlace(NaviBarActivity.GO_TO_COST_LIST);
	}
	
//	@UiHandler("goToAll")
//	void onGoToAllClick(ClickEvent e) {
//		this.presenter.goToPlace(NaviBarActivity.GO_TO_INVOICE_LIST);
//	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
