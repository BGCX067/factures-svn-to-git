package albert.lacambra.factures.app.view.desktop;

import albert.lacambra.factures.app.presenter.InvoiceListPresenter;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class InvoiceListView extends ViewImpl implements InvoiceListPresenter.MyView {

	private final Widget widget;
	private InvoiceCellList invoiceCellList = new InvoiceCellList();
	
	@UiField HTMLPanel invoices;
	@UiField HTMLPanel filterPanel;
	
	public interface Binder extends UiBinder<Widget, InvoiceListView> {
	}

	@Inject
	public InvoiceListView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(invoiceCellList);
		invoices.add(invoiceCellList);
		invoices.add(pager);
	}
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		
		if(content == null) filterPanel.clear();
		
		if(slot == InvoiceListPresenter.SLOT_filters) {
			filterPanel.clear();
			filterPanel.add(content);
		}
		else {
			super.setInSlot(slot, content);
		}
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public InvoiceCellList getInvoiceCellList() {
		return invoiceCellList;
	}
}
