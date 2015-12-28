package albert.lacambra.factures.app.filters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import albert.lacambra.factures.app.model.Payable;
import albert.lacambra.factures.app.presenter.FiltersPresenter.FilterTypes;

public class FilterByCategory<T extends Payable> extends Filter<T> {

	@Override
	public List<T> doFilter(List<T> toFilter) {

		if(!hasActiveMatchers()) return toFilter;

		List<T> filtered = new ArrayList<T>();

		for(T payable: toFilter) {
			if(matchers.containsKey(payable.getCategory()) && matchers.get(payable.getCategory())){
				filtered.add(payable);
			}
		}

		return filtered;
	}

	@Override
	public List<String> getFiltersValues(List<T> partialList)
	{
		List<String> categories = new ArrayList<String>();
		for(T payable: partialList) {

			String cat = payable.getCategory();

			if(!categories.contains(cat)) {
				categories.add(cat);
			}
		}

		Collections.sort(categories);
		categories.add(Filter.ALL);

		return categories;
	}

	@Override
	public FilterTypes getType() {
		return FilterTypes.CATEGORY;
	}
}





































