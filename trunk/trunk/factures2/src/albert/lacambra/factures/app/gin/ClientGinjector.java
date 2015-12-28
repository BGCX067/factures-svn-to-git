package albert.lacambra.factures.app.gin;

import com.google.gwt.inject.client.GinModules;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;

import albert.lacambra.factures.app.gin.ClientModule;
import albert.lacambra.factures.app.presenter.FixCostListPresenter;
import albert.lacambra.factures.app.presenter.InvoiceListPresenter;
import albert.lacambra.factures.app.presenter.MasterPresenter;
import albert.lacambra.factures.app.presenter.NewFixCostPresenter;
import albert.lacambra.factures.app.presenter.NewInvoicePresenter;

import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.gwt.inject.client.AsyncProvider;
import albert.lacambra.factures.app.presenter.ChartsPresenter;

@GinModules({ DispatchAsyncModule.class, ClientModule.class })
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	AsyncProvider<MasterPresenter> getMasterPresenter();

	AsyncProvider<NewInvoicePresenter> getNewInvoicePresenter();

	AsyncProvider<NewFixCostPresenter> getNewFixCostPresenter();

	AsyncProvider<InvoiceListPresenter> getInvoiceListPresenter();

	AsyncProvider<FixCostListPresenter> getFixCostLIstPresenter();

	AsyncProvider<ChartsPresenter> getChartsPresenter();
	
}
