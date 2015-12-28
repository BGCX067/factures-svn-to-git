package albert.lacambra.factures.app.filters.view.desktop;

import java.util.List;

import albert.lacambra.factures.app.filters.FilterItem;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

public class FilterListViewImpl extends Composite {
	
	private HTMLPanel main = new HTMLPanel("");
	private HTMLPanel filterContainer = new HTMLPanel("");
	private HTMLPanel catcherContainer = new HTMLPanel("");
	
	private HashMap<Integer, FilterItem> filters = new HashMap<Integer, FilterItem>();
	private HashMap<Integer, Label> usedFilters = new HashMap<Integer, Label>();
	private HashMap<Integer, Label> notUsedFilters = new HashMap<Integer, Label>();
	
	public FilterListViewImpl()
	{
		main.add(filterContainer);
		main.add(catcherContainer);
	}
	
	public void loadFilterItems(List<FilterItem> list)
	{
		int i = 0;
		for (final FilterItem filter: list) {
			filter.setId(i);
			filters.put(i, filter);
			i++;
			
			final Label l = new Label(filter.getText());
			l.getElement().setDraggable(Element.DRAGGABLE_TRUE);
			l.addDragStartHandler(new DragStartHandler() {
				
				@Override
				public void onDragStart(DragStartEvent event) {
					event.setData("text", String.valueOf(filter.getId()));
				}
			});
			filterContainer.add(l);
			notUsedFilters.put(filter.getId(), l);
		}
		
		catcherContainer.addDomHandler(new DragOverHandler(){

			@Override
			public void onDragOver(DragOverEvent event) {
				
			}
			
		}, DragOverEvent.getType());
		
		catcherContainer.addDomHandler(new DropHandler() {
			
			@Override
			public void onDrop(DropEvent event) {
				final Label l = notUsedFilters.get(event.getData("text"));
//				l.getElement().setDraggable(Element.DRAGGABLE_TRUE);
//				l.addDragStartHandler(new DragStartHandler() {
//					
//					@Override
//					public void onDragStart(DragStartEvent event) {
//						event.setData("text", l.getText());			
//						
//					}
//				});
				catcherContainer.add(l);
			}
		}, DropEvent.getType());
	}
}
