package albert.lacambra.factures.app.events;

import java.util.List;

import albert.lacambra.factures.app.model.Invoice;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.HasHandlers;

public class InvoicesListReceivedEvent extends
		GwtEvent<InvoicesListReceivedEvent.InvoicesListReceivedHandler> {

	public static Type<InvoicesListReceivedHandler> TYPE = new Type<InvoicesListReceivedHandler>();

	public interface InvoicesListReceivedHandler extends EventHandler {
		void onInvoicesListReceived(InvoicesListReceivedEvent event);
	}

	private List<Invoice> invoices;
	
	public InvoicesListReceivedEvent(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Override
	protected void dispatch(InvoicesListReceivedHandler handler) {
		handler.onInvoicesListReceived(this);
	}

	@Override
	public Type<InvoicesListReceivedHandler> getAssociatedType() {
		return TYPE;
	}

	public static Type<InvoicesListReceivedHandler> getType() {
		return TYPE;
	}

	public static void fire(HasHandlers source, List<Invoice> invoices) {
		source.fireEvent(new InvoicesListReceivedEvent(invoices));
	}
	
	public List<Invoice> getInvoices() {
		return invoices;
	}
}
