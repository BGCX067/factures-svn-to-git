package albert.lacambra.factures.app.presenter.newinvoice;

import albert.lacambra.factures.app.model.Invoice;

import com.google.gwt.user.client.ui.IsWidget;

public interface NewInvoiceView extends IsWidget{

	public void setPresenter(NewInvoicePresenter presenter);
	
	public interface NewInvoicePresenter
	{
		public void addInvoice(Invoice invoice);
	}

	void onSuccess(String invoice);
}
