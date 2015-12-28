package albert.lacambra.factures.app.events;

import albert.lacambra.factures.app.model.Invoice;

import com.google.gwt.event.shared.GwtEvent;

public class InvoiceUpdatedEvent extends GwtEvent<InvoiceUpdatedHandler> {

	public static final Type<InvoiceUpdatedHandler> TYPE = new Type<InvoiceUpdatedHandler>();
	
	private Invoice invoice;
	
	public InvoiceUpdatedEvent(Invoice invoice) {
		this.invoice = invoice;
	}
	 
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<InvoiceUpdatedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(InvoiceUpdatedHandler handler) {
		handler.onUpdateInvoice(this);
	}

	public Invoice getInvoice() {
		return this.invoice;
	}
}
