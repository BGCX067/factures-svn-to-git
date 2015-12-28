package albert.lacambra.factures.app.presenter.invoicelist;

import java.util.List;

import albert.lacambra.factures.app.model.Invoice;

import com.google.gwt.view.client.ListDataProvider;

public class InvoiceListProvider extends ListDataProvider<Invoice>{
	
	public void loadData(List<Invoice> list)
	{
		List<Invoice> oldList = getList();
		oldList.clear();
		oldList.addAll(list);
		setList(oldList);
		refresh();
	}
}
