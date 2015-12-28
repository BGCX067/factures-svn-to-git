package albert.lacambra.factures.app.view.desktop;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.HasData;

import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.presenter.fixcost.FixCostListView;
import albert.lacambra.factures.app.presenter.newfixcost.FixCostCellList;

public class FixCostListViewImpl extends Composite implements FixCostListView {
	
	FixCostListPresenter presenter;
	FixCostCellList fixCostCellList = new FixCostCellList();
	
	private static FixCostListViewImplUiBinder uiBinder = GWT
			.create(FixCostListViewImplUiBinder.class);

	interface FixCostListViewImplUiBinder extends
			UiBinder<Widget, FixCostListViewImpl> {
	}
	
	@UiField HTMLPanel fixcCosts;
	@UiField HTMLPanel totalPrice;
	@UiField HTMLPanel filterPanel;
	@UiField HTMLPanel categoryFilter;
	@UiField HTMLPanel dateFilter;
	@UiField HTMLPanel personFilter;
	@UiField HTMLPanel activeFilters;

	public FixCostListViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(fixCostCellList);
		fixcCosts.add(pager);
		fixcCosts.add(fixCostCellList);
		fixcCosts.add(pager);
		loadDraggableCatcher();
	}
	
	@Override
	public void setPresenter(FixCostListPresenter presenter) {
		this.presenter = presenter;
		fixCostCellList.setPresenter(presenter);
	}

	@Override
	public HasData<FixCost> getCellList() {
		return fixCostCellList;
	}

	@Override
	public void updateFixCostList() {
		fixCostCellList.updateFixCostList();
	}

	@Override
	public void setTotalPrice(String total) {
		SafeHtmlBuilder price = new SafeHtmlBuilder();
		price.appendEscaped(total);
		totalPrice.clear();
		totalPrice.add(new HTML(price.toSafeHtml()));
	}

	@Override
	public void setCategories(List<String> categoriesArray) {
		
		categoryFilter.clear();
		for(final String cat : categoriesArray) {
			Label l = new Label(cat);
			l.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					presenter.setListByCategory(cat);
				}
			});
			categoryFilter.add(l);
		}
	}

	@Override
	public void setOwner(List<String> ownerArray) {
		personFilter.clear();
		for(final String owner : ownerArray) {
			final Label l = new Label(owner);
			l.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					presenter.setListByOwner(owner);
				}
			});
			l.getElement().setDraggable(Element.DRAGGABLE_TRUE);
			l.addDragStartHandler(new DragStartHandler() {
				
				@Override
				public void onDragStart(DragStartEvent event) {
					event.setData("text", l.getText());					
				}
			});
			personFilter.add(l);
		}
	}
	
	private void loadDraggableCatcher() 
	{
		activeFilters.addDomHandler(new DragOverHandler(){

			@Override
			public void onDragOver(DragOverEvent event) {
				
			}
			
		}, DragOverEvent.getType());
		
		activeFilters.addDomHandler(new DropHandler() {
			
			@Override
			public void onDrop(DropEvent event) {
				final Label l = new Label(event.getData("text"));
				l.getElement().setDraggable(Element.DRAGGABLE_TRUE);
				l.addDragStartHandler(new DragStartHandler() {
					
					@Override
					public void onDragStart(DragStartEvent event) {
						event.setData("text", l.getText());					
					}
				});
				activeFilters.add(l);
			}
		}, DropEvent.getType());
	}
}
























































