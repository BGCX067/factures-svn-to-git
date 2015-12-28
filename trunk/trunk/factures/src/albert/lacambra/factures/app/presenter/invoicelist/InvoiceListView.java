package albert.lacambra.factures.app.presenter.invoicelist;

import java.util.List;

import albert.lacambra.factures.app.model.Invoice;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;

public interface InvoiceListView extends IsWidget{

	public void setPresenter(InvoiceListPresenter presenter);
	
	public interface InvoiceListPresenter
	{
		public void invoiceUpdate(Invoice invoice);
		public void invoiceDelete(int invoiceId);
		public void goToAddInvoice();
		public InvoiceListProvider getDataProvider();
		public void setListByCategory(String cat);
		void setListByDate(String date);
		public void setListByOwner(String owner);
	}

	public HasData<Invoice> getCellList();

	public void updateInvoiceList();
	public void setTotalPrice(String string);
	public void setCategories(List<String> categoriesArray);
	public void setDates(List<String> datesArray);

	public void setOwner(List<String> ownerFilters);
}
