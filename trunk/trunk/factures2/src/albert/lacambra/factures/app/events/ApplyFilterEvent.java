package albert.lacambra.factures.app.events;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;

import com.google.gwt.event.shared.HasHandlers;

public class ApplyFilterEvent extends
		GwtEvent<ApplyFilterEvent.ApplyFilterHandler> {

	public static Type<ApplyFilterHandler> TYPE = new Type<ApplyFilterHandler>();

	public interface ApplyFilterHandler extends EventHandler {
		void onApplyFilter(ApplyFilterEvent event);
	}

	public ApplyFilterEvent() {
	}


	@Override
	protected void dispatch(ApplyFilterHandler handler) {
		handler.onApplyFilter(this);
	}

	@Override
	public Type<ApplyFilterHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<ApplyFilterHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source) {
		source.fireEvent(new ApplyFilterEvent());
	}
}
