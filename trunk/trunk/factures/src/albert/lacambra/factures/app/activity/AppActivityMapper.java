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
package albert.lacambra.factures.app.activity;

import albert.lacambra.factures.app.ClientFactory;
import albert.lacambra.factures.app.presenter.fixcost.FixCostListActivity;
import albert.lacambra.factures.app.presenter.fixcost.FixCostListPlace;
import albert.lacambra.factures.app.presenter.fixcost.NewFixCostActivity;
import albert.lacambra.factures.app.presenter.invoicelist.InvoiceListActivity;
import albert.lacambra.factures.app.presenter.invoicelist.InvoiceListPlace;
import albert.lacambra.factures.app.presenter.newfixcost.NewFixCostPlace;
import albert.lacambra.factures.app.presenter.newinvoice.NewInvoiceActivity;
import albert.lacambra.factures.app.presenter.newinvoice.NewInvoicePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;


/**
 * A mapping of places to activities used by this application.
 */
public class AppActivityMapper implements ActivityMapper {

  private final ClientFactory clientFactory;

  public AppActivityMapper(ClientFactory clientFactory) {
    this.clientFactory = clientFactory;
  }

  public Activity getActivity(final Place place) {
    if (place instanceof InvoiceListPlace) {
    	return new InvoiceListActivity((InvoiceListPlace) place, clientFactory);
    }

    if (place instanceof NewInvoicePlace) {
      return new NewInvoiceActivity((NewInvoicePlace) place, clientFactory);
    }
    
    if (place instanceof NewFixCostPlace) {
    	return new NewFixCostActivity((NewFixCostPlace) place, clientFactory);
    }
    
    if (place instanceof FixCostListPlace) {
    	return new FixCostListActivity((FixCostListPlace) place, clientFactory);
    }

    return null;
  }
}
