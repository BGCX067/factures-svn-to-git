package albert.lacambra.factures.app.presenter;

import java.util.HashSet;
import java.util.List;
import albert.lacambra.factures.app.events.ApplyFilterEvent;
import albert.lacambra.factures.app.filters.Filter;
import albert.lacambra.factures.app.filters.FilterByCategory;
import albert.lacambra.factures.app.filters.FilterByDate;
import albert.lacambra.factures.app.filters.FilterByExtra;
import albert.lacambra.factures.app.filters.FilterByOwner;
import albert.lacambra.factures.app.filters.IsFilter;
import albert.lacambra.factures.app.model.Payable;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.HasText;

public class FiltersPresenter<T extends Payable> extends PresenterWidget<FiltersPresenter.MyView>{

	public enum FilterTypes {
		CATEGORY, OWNER, PRICE, DATE, EXTRA 
	}
	
	private FilterTypes lastTypeUSed = null;

	private HashSet<IsFilter<T>> filters = new HashSet<IsFilter<T>>();

	public interface MyView extends View {
		HasText getPriceLabel();
		HasClickHandlers buildFilterLabel(FilterTypes type, String filterMAtcher);
		HasClickHandlers addSelectedFilter(FilterTypes category, String filterName);
		void clearFilterPanels();
	}

	@Inject
	public FiltersPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	public void loadFilters(List<T> invoices) {

		filters.add(new FilterByCategory<T>());
		filters.add(new FilterByDate<T>());
		filters.add(new FilterByExtra<T>());
		filters.add(new FilterByOwner<T>());

		loadFilterMatchers(invoices);
	}

	public void refreshFilterValues(List<T> invoices) {
		getView().clearFilterPanels();
		loadFilterMatchers(invoices);
	}

	public List<T> filterList(List<T> list){

		for(IsFilter<T> f: filters){
			list = f.doFilter(list);
		}

		refreshFilterValues(list);
		return list;
	}

	private void loadFilterMatchers(List<T> invoices) {

		for(final IsFilter<T> filter : filters) {
			final FilterTypes type = filter.getType();
			
			List<String> filters = lastTypeUSed == type ? filter.getMatchers() : filter.getFiltersValues(invoices); 
			
			for(final String filterName : filters) {

				final String fn = filterName;

				filter.addMatcher(filterName);
				getView().buildFilterLabel(type, filterName).addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {

						if(filter.matcherIsEnabled(fn)) return;
						
						lastTypeUSed = type;

						filter.enableMatcher(fn);
						getEventBus().fireEvent(new ApplyFilterEvent());

						getView().addSelectedFilter(type, filterName).addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {

								if(fn.equals(Filter.ALL)) return;

								filter.disableMatcher(fn);
								getEventBus().fireEvent(new ApplyFilterEvent());
							}
						});
					}
				});
			}
		}
	}
}


















































