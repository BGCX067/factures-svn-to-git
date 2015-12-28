package albert.lacambra.factures.app.events;

import albert.lacambra.factures.app.filters.IsFilter;
import albert.lacambra.factures.app.model.Invoice;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class RemoveFilterEvent extends GwtEvent<RemoveFilterEvent.RemoveFilterHandler> {

	public static Type<RemoveFilterHandler> TYPE = new Type<RemoveFilterHandler>();

	public interface RemoveFilterHandler extends EventHandler {
		void onRemoveFilter(RemoveFilterEvent event);
	}

	private IsFilter<Invoice> filter;

	public RemoveFilterEvent(IsFilter<Invoice> filter, String matcher) {
		this.filter = filter;
	}

	@Override
	protected void dispatch(RemoveFilterHandler handler) {
		handler.onRemoveFilter(this);
	}

	@Override
	public Type<RemoveFilterHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<RemoveFilterHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, IsFilter<Invoice> filter, String matcher) {
		source.fireEvent(new RemoveFilterEvent(filter, matcher));
	}
	
	public IsFilter<Invoice> getFilter() {
		return filter;
	}
}
