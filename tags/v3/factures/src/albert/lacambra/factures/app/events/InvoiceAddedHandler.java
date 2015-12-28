package albert.lacambra.factures.app.events;

import com.google.gwt.event.shared.EventHandler;

public interface InvoiceAddedHandler extends EventHandler{
	public void onAddInvoice(InvoiceAddedEvent e);

}
