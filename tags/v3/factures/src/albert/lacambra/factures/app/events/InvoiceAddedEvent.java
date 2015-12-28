package albert.lacambra.factures.app.events;

import albert.lacambra.factures.app.model.Invoice;

import com.google.gwt.event.shared.GwtEvent;

public class InvoiceAddedEvent extends GwtEvent<InvoiceAddedHandler> {

	public static final Type<InvoiceAddedHandler> TYPE = new Type<InvoiceAddedHandler>();
	
	private Invoice invoice;
	
	public InvoiceAddedEvent(Invoice invoice) {
		this.invoice = invoice;
	}
	 
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<InvoiceAddedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InvoiceAddedHandler handler) {
		handler.onAddInvoice(this);
	}

	public Invoice getInvoice() {
		return this.invoice;
	}
}
