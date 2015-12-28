package albert.lacambra.factures.app.filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

import albert.lacambra.factures.app.model.Payable;
import albert.lacambra.factures.app.presenter.FiltersPresenter.FilterTypes;

public class FilterByDate<T extends Payable> extends Filter<T> {

	@Override
	public List<T> doFilter(List<T> toFilter) {
		
		if(!hasActiveMatchers()) return toFilter;
		
		ArrayList<T> filtered = new ArrayList<T>();
		
		for(String m : matchers.keySet()) {

			if(!matchers.get(m)) continue;
			
			RegExp regExp = RegExp.compile("^" + m + "-[0-3]{1}[0-9]{1}$");

			for(T payable: toFilter) {
				String date = payable.getDate();
//				DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd"); 
//				Date d = dtf.parse(date);
//				d.getTime();
				
				MatchResult matcher = regExp.exec(date);

				if(matcher != null){
					filtered.add(payable);
				}
			}
		}

		return filtered;
	}

	@Override
	public List<String> getFiltersValues(List<T> partialList) {
		List<String> dates = new ArrayList<String>();
		for(T payable : partialList) {
			
			String date = payable.getDate().substring(0, 7);
			
			if(!dates.contains(date)) {
				dates.add(date);
			}
		}
		Collections.sort(dates);
		dates.add(Filter.ALL);
		return dates;
	}
	
	@Override
	public FilterTypes getType() {
		return FilterTypes.DATE;
	}
}
