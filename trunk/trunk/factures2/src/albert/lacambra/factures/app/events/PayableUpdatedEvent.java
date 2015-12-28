package albert.lacambra.factures.app.events;

import com.google.gwt.event.shared.GwtEvent;

public class PayableUpdatedEvent extends GwtEvent<PayableUpdatedHandler> {

	public static final Type<PayableUpdatedHandler> TYPE = new Type<PayableUpdatedHandler>();
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<PayableUpdatedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PayableUpdatedHandler handler) {
		handler.onPayableUpdated(this);
	}
}
