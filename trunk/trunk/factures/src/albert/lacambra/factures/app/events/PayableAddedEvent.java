package albert.lacambra.factures.app.events;

import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.model.Invoice;

import com.google.gwt.event.shared.GwtEvent;

public class PayableAddedEvent extends GwtEvent<PayableAddedHandler> {

	public static final Type<PayableAddedHandler> TYPE = new Type<PayableAddedHandler>();
	
	private Object payable;
	
	public PayableAddedEvent(Object payable) {
		this.payable = payable;
	}
	 
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<PayableAddedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PayableAddedHandler handler) {
		handler.onPayableAdded(this);
	}

	public Invoice getInvoice() {
//		if(type == Payable.Type.INVOICE) {
			return (Invoice) payable;
//		}
		
//		return null;
	}
	
	public FixCost getFixCost() {
//		if(type == Payable.Type.FIX_COST) {
			return (FixCost) payable;
//		}
		
//		return null;
	}
}
