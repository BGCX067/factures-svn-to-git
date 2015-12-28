package albert.lacambra.factures.app.filters;

import java.util.ArrayList;
import java.util.List;

import albert.lacambra.factures.app.model.Payable;
import albert.lacambra.factures.app.presenter.FiltersPresenter.FilterTypes;

public class FilterByOwner<T extends Payable> extends Filter<T> {

	@Override
	public List<T> doFilter(List<T> toFilter) {

		if(!hasActiveMatchers()) return toFilter;
		
		List<T> filtered = new ArrayList<T>();

		System.out.println("Current status: " + matchers);
		
		for(T payable: toFilter) {
			if(matchers.containsKey(String.valueOf(payable.getOwnerId())) && matchers.get(String.valueOf(payable.getOwnerId()))){
				filtered.add(payable);
			}
		}

		return filtered;
	}

	@Override
	public List<String> getFiltersValues(List<T> partialList)
	{
		List<String> people = new ArrayList<String>();
		for(T payable: partialList) {

			String person = Integer.toString(payable.getOwnerId());

			if(!people.contains(person)) {
				people.add(person);
			}
		}
		people.add(Filter.ALL);

		return people;
	}
	
	@Override
	public FilterTypes getType() {
		return FilterTypes.OWNER;
	}

}
