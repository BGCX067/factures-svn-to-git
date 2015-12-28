package albert.lacambra.factures.app.filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import albert.lacambra.factures.app.model.Payable;
import albert.lacambra.factures.app.presenter.FiltersPresenter.FilterTypes;

public class FilterByExtra<T extends Payable> extends Filter<T> {

	@Override
	public List<T> doFilter(List<T> toFilter) {
		
		if(!hasActiveMatchers()) return toFilter;
		
		List<T> filtered = new ArrayList<T>();
		
		for(T payable: toFilter) {
			if(matchers.containsKey(payable.getExtra()) && matchers.get(payable.getExtra())){
				filtered.add(payable);
			}
		}
		
		return filtered;
	}
	
	@Override
	public List<String> getFiltersValues(List<T> partialList)
	{
		List<String> extras = new ArrayList<String>();
		for(T payable: partialList) {
			
			String extra = payable.getExtra();
			
			if(!extras.contains(extra)) {
				extras.add(extra);
			}
		}
		
		Collections.sort(extras);
		extras.add(Filter.ALL);
		return extras;
	}
	
	@Override
	public FilterTypes getType() {
		return FilterTypes.EXTRA;
	}
}
