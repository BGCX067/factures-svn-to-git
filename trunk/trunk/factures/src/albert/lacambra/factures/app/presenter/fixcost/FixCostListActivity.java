package albert.lacambra.factures.app.presenter.fixcost;

import java.util.ArrayList;
import java.util.List;

import albert.lacambra.factures.app.ClientFactory;
import albert.lacambra.factures.app.events.PayableUpdatedEvent;
import albert.lacambra.factures.app.events.PayableUpdatedHandler;
import albert.lacambra.factures.app.filters.FixCostListFilters;
import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.model.Payable;
import albert.lacambra.factures.app.presenter.ListProvider;
import albert.lacambra.factures.app.rest.PayableCallback;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class FixCostListActivity extends AbstractActivity implements FixCostListView.FixCostListPresenter
{

	@SuppressWarnings("unused")
	private FixCostListPlace place;
	private ClientFactory clientFactory;
	private ListProvider<FixCost> provider;
	private List<FixCost> fixCostList = new ArrayList<FixCost>();
	private FixCostListFilters filter = new FixCostListFilters();
	
	public FixCostListActivity(FixCostListPlace place, ClientFactory clientFactory)
	{
		this.place = place;
		this.clientFactory = clientFactory;
		this.provider = new ListProvider<FixCost>();
		this.provider.addDataDisplay(clientFactory.getFixCostListView().getCellList());
		loadHandlers();
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) 
	{
		clientFactory.getFixCostListView().setPresenter(this);
		loadFixCostList();
		panel.setWidget(clientFactory.getFixCostListView().asWidget());
	}

	private void loadFixCostList()
	{
		try {
			clientFactory.getRestClient().getAllFixCosts(new PayableCallback<List<FixCost>>() {
				
				@Override
				public void onSuccess(List<FixCost> ressource) {
					fixCostList = ressource;
					setPartialList(fixCostList);
				}

				@Override
				public void onFailure(Throwable e) {
					
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}

	private void setPartialList(List<FixCost> partialList)
	{
		float totalPrice = filter.getTotalFromList(partialList);
		
		provider.loadData(partialList);
		clientFactory.getFixCostListView().updateFixCostList();
		clientFactory.getFixCostListView().setTotalPrice(String.valueOf(totalPrice));
		clientFactory.getFixCostListView().setOwner(filter.getOwnerFilters(fixCostList));
		clientFactory.getFixCostListView().setCategories(filter.getCategoryFilters(fixCostList));
	}
	@Override
	public ListProvider<FixCost> getDataProvider() {
		return this.provider;
	}
	@Override
	public void fixCostUpdate(FixCost fixCost) {
		try {
			clientFactory.getRestClient().updatePayable(fixCost, new PayableCallback<FixCost>() {

				@Override
				public void onSuccess(FixCost ressource) {
					clientFactory.getEventBus().fireEventFromSource(new PayableUpdatedEvent(), Payable.Type.FIX_COST);
				}

				@Override
				public void onFailure(Throwable e) {
					
				}
			}, "fixcost", fixCost.getId());
		} catch (RequestException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void setListByCategory(String cat) {
		if(cat.equals("all")){ 
			setPartialList(fixCostList);
			return;
		}
			
		List<FixCost> list = new ArrayList<FixCost>();
		for(FixCost fixCost : fixCostList) {
			if(fixCost.getCategory().equals(cat)) {
				list.add(fixCost);
			}
		}
		
		setPartialList(list);
	}
	
	private void loadHandlers()
	{
		clientFactory.getEventBus().addHandlerToSource(PayableUpdatedEvent.TYPE, Payable.Type.FIX_COST, new PayableUpdatedHandler() {
			
			@Override
			public void onPayableUpdated(PayableUpdatedEvent e) {
				clientFactory.getFixCostListView().setTotalPrice(String.valueOf(filter.getTotalFromList(provider.getList())));
			}
		});
	}

	@Override
	public void setListByOwner(String owner) {
		if(owner.equals("all")){ 
			setPartialList(fixCostList);
			return;
		}
			
		List<FixCost> list = new ArrayList<FixCost>();
		for(FixCost fixCost : fixCostList) {
			String ownerId = String.valueOf(fixCost.getOwnerId());
			if(ownerId.equals(owner)) {
				list.add(fixCost);
			}
		}
		
		setPartialList(list);
	}
}
















































