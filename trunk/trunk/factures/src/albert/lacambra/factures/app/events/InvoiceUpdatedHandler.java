package albert.lacambra.factures.app.events;

import com.google.gwt.event.shared.EventHandler;

public interface InvoiceUpdatedHandler extends EventHandler{
	public void onUpdateInvoice(InvoiceUpdatedEvent e);
}
