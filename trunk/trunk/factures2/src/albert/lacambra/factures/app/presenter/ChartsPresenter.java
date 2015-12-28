package albert.lacambra.factures.app.presenter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.google.gwt.i18n.client.DateTimeFormat;
import albert.lacambra.factures.app.events.ApplyFilterEvent;
import albert.lacambra.factures.app.filters.FilterByCategory;
import albert.lacambra.factures.app.filters.FilterByDate;
import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.place.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.RequestException;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import albert.lacambra.factures.app.presenter.MasterPresenter;
import albert.lacambra.factures.app.rest.PayableCallback;
import albert.lacambra.factures.app.rest.PayableRestClient;

public class ChartsPresenter extends Presenter<ChartsPresenter.MyView, ChartsPresenter.MyProxy> {

	public static final Object SLOT_Filters = new Object();
	public static final Object SLOT_Charts = new Object();
	public static final Object SLOT_Menu = new Object();
	
	private FiltersPresenter<Invoice> filtersPresenter;
	private PayableRestClient restClient;

	public interface MyView extends View {

		void loadChart(HashMap<String, Float> categories, String title);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.charts)
	public interface MyProxy extends ProxyPlace<ChartsPresenter> {
	}

	@Inject
	public ChartsPresenter(
			final EventBus eventBus, 
			final MyView view, final 
			MyProxy proxy, 
			PayableRestClient restClient, 
			FiltersPresenter<Invoice> filtersPresenter
			) {

		super(eventBus, view, proxy);
		this.restClient = restClient;
		this.filtersPresenter = filtersPresenter;
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MasterPresenter.TYPE_MainContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();

		registerHandler(getEventBus().addHandler(ApplyFilterEvent.TYPE, new ApplyFilterEvent.ApplyFilterHandler() {

			@Override
			public void onApplyFilter(ApplyFilterEvent event) {
				applyFilter();
			}
		}));
		
		setInSlot(SLOT_Filters, filtersPresenter);
		loadInvoiceList();
	}

	private void applyFilter() {
		//		filtersPresenter.getView().getPriceLabel().setText(String.valueOf(calculateTotal()));
	}

	private void loadInvoiceList()
	{
		try {
			restClient.getAllInvoices(new PayableCallback<List<Invoice>>() {

				@Override
				public void onSuccess(List<Invoice> resource) {

					filtersPresenter.loadFilters(resource);
					
					DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd");
					DateTimeFormat dtfToShow = DateTimeFormat.getFormat("MM/yyyy"); 

					FilterByDate<Invoice> filter = new FilterByDate<Invoice>();
					List<String> dates = filter.getFiltersValues(resource);
					
					FilterByCategory<Invoice> cats = new FilterByCategory<Invoice>();
					
					for(String c : cats.getFiltersValues(resource)){
						
					}

					for(String d : dates) {
						HashMap<String, Float> categories = new HashMap<String, Float>();
						Date min = dtf.parse(d + "-01");
						Date max;
						
						for(String c : cats.getFiltersValues(resource)){
							float f = 0;
							categories.put(c, f);
						}
						
						try{
							max = dtf.parse(d + "-31");
						} catch (IllegalArgumentException e){
							max = dtf.parse(d + "-30");
						}
						
						for(Invoice i : resource) {
							Date date = dtf.parse(i.getDate());

							if(max.getTime() < date.getTime() || date.getTime() < min.getTime()) continue;

							String cat = i.getCategory();
							if(!categories.containsKey(cat)) {
								categories.put(cat, i.getPrice());
							} else {
								categories.put(cat,categories.get(cat) + i.getPrice());
							}
						}

						getView().loadChart(categories, dtfToShow.format(min));
					}
				}

				@Override
				public void onFailure(Throwable e) {
					System.out.println("ko");
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}


}
