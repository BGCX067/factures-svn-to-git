package albert.lacambra.factures.app;


import albert.lacambra.factures.app.activity.AppActivityMapper;
import albert.lacambra.factures.app.activity.AppPlaceHistoryMapper;
import albert.lacambra.factures.app.presenter.invoicelist.InvoiceListPlace;
import albert.lacambra.factures.app.presenter.navibar.NaviBarActivity;
import albert.lacambra.factures.app.presenter.newinvoice.NewInvoicePlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Invoices implements EntryPoint {

	 private Place defaultPlace = new NewInvoicePlace();
	 private SimplePanel appWidget = new SimplePanel();
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() 
	{
		defaultPlace = new InvoiceListPlace(); 
		
		ClientFactory clientFactory = new ClientFactoryImpl();
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(appWidget);
        
        AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        NaviBarActivity navyBar = new NaviBarActivity(clientFactory);
        
        SimplePanel navyPanel = new SimplePanel();
        navyBar.start(navyPanel, (com.google.gwt.event.shared.EventBus) activityManager.getActiveEventBus());
        
        RootPanel.get().add(navyPanel);
        RootPanel.get().add(appWidget);
        historyHandler.handleCurrentHistory();
	}
}
