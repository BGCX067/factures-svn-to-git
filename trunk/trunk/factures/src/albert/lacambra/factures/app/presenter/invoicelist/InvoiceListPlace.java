package albert.lacambra.factures.app.presenter.invoicelist;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class InvoiceListPlace extends Place{
	  @Prefix("il")
	  public static class Tokenizer implements PlaceTokenizer<InvoiceListPlace> {

	    public InvoiceListPlace getPlace(String token) {
	      return new InvoiceListPlace();
	    }

	    public String getToken(InvoiceListPlace place) {
	      return "invoicelist";
	    }
	  }

	  public InvoiceListPlace() {
	  }

}
