package albert.lacambra.factures.app.presenter.newfixcost;

import albert.lacambra.factures.app.model.FixCost;

import com.google.gwt.user.client.ui.IsWidget;

public interface NewFixCostView extends IsWidget{

	public void setPresenter(NewInvoicePresenter presenter);
	
	public interface NewInvoicePresenter
	{
		public void addFixCost(FixCost fixCost);
	}

	void onSuccess(String invoice);
}
