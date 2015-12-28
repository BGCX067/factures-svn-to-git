package albert.lacambra.factures.app.filters;

import java.util.List;
import albert.lacambra.factures.app.model.Payable;
import albert.lacambra.factures.app.presenter.FiltersPresenter.FilterTypes;

public class FilterByPrice<T extends Payable> extends Filter<T> {

	@Override
	public List<T> doFilter(List<T> toFilter) {
		return toFilter;
	}

	@Override
	public List<String> getFiltersValues(List<T> partialList) {
		return null;
	}

	@Override
	public void addMatcher(String matcher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableMatcher(String matcher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableAllMatchers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FilterTypes getType() {
		return FilterTypes.PRICE;
	}
}
