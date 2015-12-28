package albert.lacambra.factures.app.presenter.newfixcost;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class NewFixCostPlace extends Place{
	
	
	public static class Tokenizer implements PlaceTokenizer<NewFixCostPlace> {

		public NewFixCostPlace getPlace(String token) {
			return new NewFixCostPlace();
		}

		public String getToken(NewFixCostPlace place) {
			return "newfixcost";
		}
	}

	public NewFixCostPlace() {
	}
}
