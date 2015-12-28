/*
 * Copyright 2011 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package albert.lacambra.factures.app;

import albert.lacambra.factures.app.presenter.fixcost.FixCostListView;
import albert.lacambra.factures.app.presenter.invoicelist.InvoiceListView;
import albert.lacambra.factures.app.presenter.navibar.NaviBarView;
import albert.lacambra.factures.app.presenter.newfixcost.NewFixCostView;
import albert.lacambra.factures.app.presenter.newinvoice.NewInvoiceView;
import albert.lacambra.factures.app.rest.PayableRestClient;
import albert.lacambra.factures.app.view.desktop.FixCostListViewImpl;
import albert.lacambra.factures.app.view.desktop.InvoiceListViewImpl;
import albert.lacambra.factures.app.view.desktop.NaviBarViewImpl;
import albert.lacambra.factures.app.view.desktop.NewFixCostViewImpl;
import albert.lacambra.factures.app.view.desktop.NewInvoiceViewImpl;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * Default implementation of {@link ClientFactory}. Used by desktop version.
 */
public class ClientFactoryImpl implements ClientFactory {

	/**
	 * The URL argument used to enable or disable local storage.
	 */
	private final EventBus eventBus = new SimpleEventBus();
	private final PayableRestClient restClient;
	private final NewInvoiceView newInvoiceView = new NewInvoiceViewImpl();
	private final InvoiceListView invoiceListView = new InvoiceListViewImpl(); 
	private final PlaceController placeController = new PlaceController(eventBus);
	private final NaviBarView naviBarView = new NaviBarViewImpl();
	private NewFixCostView newFixCostView = new NewFixCostViewImpl();
	private FixCostListView fixCostListView = new FixCostListViewImpl();
	
	public ClientFactoryImpl() {
		restClient = new PayableRestClient(this);
	}

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PayableRestClient getRestClient() {
		return restClient;
	}

	@Override
	public NewInvoiceView getNewInvoiceView() {
		return newInvoiceView;
	}

	@Override
	public InvoiceListView getInvoiceListView() {
		return invoiceListView;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public NaviBarView getNaviBarView() {
		return naviBarView;
	}

	@Override
	public NewFixCostView getNewFixCostView() {
		return newFixCostView;
	}

	@Override
	public FixCostListView getFixCostListView() {
		// TODO Auto-generated method stub
		return fixCostListView;
	}
}
