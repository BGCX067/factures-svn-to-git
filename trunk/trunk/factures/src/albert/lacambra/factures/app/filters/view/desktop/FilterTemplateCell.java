package albert.lacambra.factures.app.filters.view.desktop;

import albert.lacambra.factures.app.filters.FilterItem;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DataTransfer;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.HTMLPanel;

public class FilterTemplateCell extends AbstractCell<FilterItem>{
	/**
     * Use a UiBinder template to generate the {@code Cell} contents and process
     * events.
     */
    @UiTemplate("FilterCell.ui.xml")
    interface Renderer extends UiRenderer {
      void render(SafeHtmlBuilder sb, String name, String notes);
      void onBrowserEvent(FilterTemplateCell o, NativeEvent n, Element e, Context context);
    }

    private Renderer renderer = GWT.create(Renderer.class);

    public FilterTemplateCell() {
      // Register the kinds of event this cell will manage.
      super("dragstart");
    }
    
    /**
     * Delegates event handling to the generated {@link UiRenderer}.
     */
    @Override
    public void onBrowserEvent(Context context, Element parent, FilterItem value, NativeEvent event,
        ValueUpdater<FilterItem> valueUpdater) {
      renderer.onBrowserEvent(this, event, parent, context);
    }

    @Override
    public void render(Context context, FilterItem value, SafeHtmlBuilder sb) {
    	if (value == null) {
    		return;
    	}

    	renderer.render(sb, value.getText(), "aqui text 2");
    }
    
    /**
     * Handles "drag-start" events inside the element named "root".
    */
    @UiHandler({"root"})
    void onDragStart(DragStartEvent event, Element parent, Context context) {
      // Save the ID of the TaskProxy.
      DataTransfer dataTransfer = event.getDataTransfer();
//      dataTransfer.setData("text", String.valueOf(context.getIndex()));
      dataTransfer.setData("text", "moving");

      // Set the image.
      dataTransfer.setDragImage(new HTMLPanel("on the road").getElement(), 25, 15);
    }
}



























































