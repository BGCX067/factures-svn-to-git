package albert.lacambra.factures.app.presenter.newinvoice;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewInvoicePlace extends Place{
	
	
	public static class Tokenizer implements PlaceTokenizer<NewInvoicePlace> {

		public NewInvoicePlace getPlace(String token) {
			return new NewInvoicePlace();
		}

		public String getToken(NewInvoicePlace place) {
			return "newinvoice";
		}
	}

	public NewInvoicePlace() {
	}
}
