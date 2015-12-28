package albert.lacambra.factures.app.view.desktop;

import java.util.List;

import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.presenter.invoicelist.InvoiceListView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;

public class InvoiceListViewImpl extends Composite implements InvoiceListView {
	
	InvoiceListPresenter presenter;
	InvoiceCellList invoiceCellList = new InvoiceCellList();
	
	private static InvoiceListViewImplUiBinder uiBinder = GWT
			.create(InvoiceListViewImplUiBinder.class);

	interface InvoiceListViewImplUiBinder extends
			UiBinder<Widget, InvoiceListViewImpl> {
	}
	
	@UiField HTMLPanel invoices;
	@UiField HTMLPanel totalPrice;
	@UiField HTMLPanel filterPanel;
	@UiField HTMLPanel categoryFilter;
	@UiField HTMLPanel dateFilter;
	@UiField HTMLPanel personFilter;

	public InvoiceListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(invoiceCellList);
		invoices.add(pager);
		invoices.add(invoiceCellList);
		invoices.add(pager);
	}
	
	@Override
	public void setPresenter(InvoiceListPresenter presenter) {
		this.presenter = presenter;
		invoiceCellList.setPresenter(presenter);
	}

	@Override
	public HasData<Invoice> getCellList() {
		return invoiceCellList;
	}

	@Override
	public void updateInvoiceList() {
		invoiceCellList.updateInvoiceList();
	}

	@Override
	public void setTotalPrice(String total) {
		SafeHtmlBuilder price = new SafeHtmlBuilder();
		price.appendEscaped(total);
		totalPrice.clear();
		totalPrice.add(new HTML(price.toSafeHtml()));
	}

	@Override
	public void setCategories(List<String> categoriesArray) {
		
		categoryFilter.clear();
		for(final String cat : categoriesArray) {
			Label l = new Label(cat);
			l.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					presenter.setListByCategory(cat);
				}
			});
			categoryFilter.add(l);
		}
	}

	@Override
	public void setDates(List<String> datesArray) {
		dateFilter.clear();
		for(final String date : datesArray) {
			Label l = new Label(date);
			l.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					presenter.setListByDate(date);
				}
			});
			dateFilter.add(l);
		}
	}

	@Override
	public void setOwner(List<String> ownerArray) {
		personFilter.clear();
		for(final String owner : ownerArray) {
			Label l = new Label(owner);
			l.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					presenter.setListByOwner(owner);
				}
			});
			personFilter.add(l);
		}
	}
}

























































