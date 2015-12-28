package albert.lacambra.factures.app.events;

import com.google.gwt.event.shared.EventHandler;

public interface PayableUpdatedHandler extends EventHandler{
	public void onPayableUpdated(PayableUpdatedEvent e);

}
