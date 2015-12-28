package albert.lacambra.factures.app.view.desktop;

import albert.lacambra.factures.app.filters.Filter;
import albert.lacambra.factures.app.presenter.FiltersPresenter;
import albert.lacambra.factures.app.presenter.FiltersPresenter.FilterTypes;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

public class FiltersView extends ViewImpl implements FiltersPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, FiltersView> {
	}

	@Inject
	public FiltersView(final Binder binder, EventBus eventBus) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public HasClickHandlers buildFilterLabel(FiltersPresenter.FilterTypes type, String filterMAtcher){
		Label l = new Label(filterMAtcher);
		
		switch(type){
			case CATEGORY:
				categorieTagsContainer.add(l);
				break;
			case PRICE:
				break;
			case EXTRA:
				extraFilterContainer.add(l);
				break;
			case OWNER:
				ownerFilterContainer.add(l);
				break;
			case DATE:
				dateFilterContainer.add(l);
				break;
			default:
				break;
		}

		return l;
	}

	public VerticalPanel getUsedFiltersPanel() {
		return usedFiltersPanel;
	}

	@Override
	public HasText getPriceLabel() {
		return totalLabel;
	}

	@Override
	public HasClickHandlers addSelectedFilter(final FilterTypes category, String filterName) {

		final Label l = new Label(filterName);

		switch(category){
			case CATEGORY:
				if(filterName.equals(Filter.ALL)){
					selectedCategoriesFilterPanel.clear();
					return l;
				}
				selectedCategoriesFilterPanel.add(l);
				break;
			case PRICE:
				break;
			case EXTRA:
				if(filterName.equals(Filter.ALL)){
					selectedExtraFilterPanel.clear();
					return l;
				}
				selectedExtraFilterPanel.add(l);
				break;
			case OWNER:
				if(filterName.equals(Filter.ALL)){
					selectedOwnerFilterPanel.clear();
					return l;
				}
				selectedOwnerFilterPanel.add(l);
				break;
			case DATE:
				if(filterName.equals(Filter.ALL)){
					selectedDateFilterPanel.clear();
					return l;
				}
				selectedDateFilterPanel.add(l);
				break;
			default:
				break;
		}

		l.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				switch(category){
					case CATEGORY:
						selectedCategoriesFilterPanel.remove(l);
						break;
					case PRICE:
						break;
					case EXTRA:
						selectedExtraFilterPanel.remove(l);
						break;
					case OWNER:
						selectedOwnerFilterPanel.remove(l);
						break;
					case DATE:
						selectedDateFilterPanel.remove(l);
						break;
					default:
						break;
				}
			}
		});
		
		return l;
	}
	
	@Override
	public void clearFilterPanels(){
		categorieTagsContainer.clear();
		dateFilterContainer.clear();
		extraFilterContainer.clear();
		ownerFilterContainer.clear();
	}

	@UiField VerticalPanel container;
	@UiField Label totalLabel;
	@UiField VerticalPanel filtersPanel;
	@UiField Label categoriersFilterLabel;
	@UiField VerticalPanel categorieTagsContainer;
	@UiField VerticalPanel dateFilterPanel;
	@UiField VerticalPanel categoriesFilterPanel;
	@UiField Label dateFilterLabel;
	@UiField VerticalPanel dateFilterContainer;
	@UiField VerticalPanel extraFilterPanel;
	@UiField Label extraFilterLabel;
	@UiField VerticalPanel extraFilterContainer;
	@UiField VerticalPanel ownerFilterPanel;
	@UiField Label ownerFilterLabel;
	@UiField VerticalPanel ownerFilterContainer;
	@UiField VerticalPanel usedFiltersPanel;
	@UiField VerticalPanel selectedCategoriesFilterPanel;
	@UiField VerticalPanel selectedDateFilterPanel;
	@UiField VerticalPanel selectedExtraFilterPanel;
	@UiField VerticalPanel selectedOwnerFilterPanel;
}













































