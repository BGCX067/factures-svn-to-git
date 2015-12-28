package albert.lacambra.factures.app.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import albert.lacambra.factures.app.place.ClientPlaceManager;
import albert.lacambra.factures.app.place.DefaultPlace;
import albert.lacambra.factures.app.place.NameTokens;
import albert.lacambra.factures.app.presenter.ConfirmationPresenter;
import albert.lacambra.factures.app.presenter.FiltersPresenter;
import albert.lacambra.factures.app.presenter.FixCostListPresenter;
import albert.lacambra.factures.app.presenter.InvoiceListPresenter;
import albert.lacambra.factures.app.presenter.MasterPresenter;
import albert.lacambra.factures.app.presenter.NewFixCostPresenter;
import albert.lacambra.factures.app.presenter.NewInvoicePresenter;
import albert.lacambra.factures.app.presenter.ChartsPresenter;
import albert.lacambra.factures.app.view.desktop.ChartsView;
import albert.lacambra.factures.app.view.desktop.ConfirmationView;
import albert.lacambra.factures.app.view.desktop.FiltersView;
import albert.lacambra.factures.app.view.desktop.FixCostLIstView;
import albert.lacambra.factures.app.view.desktop.InvoiceListView;
import albert.lacambra.factures.app.view.desktop.MasterView;
import albert.lacambra.factures.app.view.desktop.NewFixCostView;
import albert.lacambra.factures.app.view.desktop.NewInvoiceView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(MasterPresenter.class, MasterPresenter.MyView.class,
				MasterView.class, MasterPresenter.MyProxy.class);

		bindPresenter(NewInvoicePresenter.class,
				NewInvoicePresenter.MyView.class, NewInvoiceView.class,
				NewInvoicePresenter.MyProxy.class);

		bindPresenter(NewFixCostPresenter.class,
				NewFixCostPresenter.MyView.class, NewFixCostView.class,
				NewFixCostPresenter.MyProxy.class);

		bindPresenter(InvoiceListPresenter.class,
				InvoiceListPresenter.MyView.class, InvoiceListView.class,
				InvoiceListPresenter.MyProxy.class);

		bindPresenter(FixCostListPresenter.class,
				FixCostListPresenter.MyView.class, FixCostLIstView.class,
				FixCostListPresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.newinvoice);
		
		bindPresenterWidget(FiltersPresenter.class,
				FiltersPresenter.MyView.class, FiltersView.class);


		bindPresenterWidget(ConfirmationPresenter.class,
				ConfirmationPresenter.MyView.class, ConfirmationView.class);

		bindPresenter(ChartsPresenter.class, ChartsPresenter.MyView.class,
				ChartsView.class, ChartsPresenter.MyProxy.class);
	}
}
