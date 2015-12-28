package albert.lacambra.factures.app.events;

import com.google.gwt.event.shared.EventHandler;

public interface PayableAddedHandler extends EventHandler{
	public void onPayableAdded(PayableAddedEvent e);

}
