package albert.lacambra.factures.app.presenter.fixcost;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class FixCostListPlace extends Place{
	  public static class Tokenizer implements PlaceTokenizer<FixCostListPlace> {

	    public FixCostListPlace getPlace(String token) {
	      return new FixCostListPlace();
	    }

	    public String getToken(FixCostListPlace place) {
	      return "fixcostlist";
	    }
	  }

	  public FixCostListPlace() {
	  }

}
