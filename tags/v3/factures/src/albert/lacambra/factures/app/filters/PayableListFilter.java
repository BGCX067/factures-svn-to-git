package albert.lacambra.factures.app.filters;

import java.util.ArrayList;
import java.util.List;

import albert.lacambra.factures.app.model.Payable;

public abstract class PayableListFilter<T extends Payable>
{
	public float getTotalFromList(List<T> payables)
	{
		float total = 0;

		for(T payable : payables) {
			total += payable.getMonthlyPrice();
		}

		return total;
	}
	
	public List<String> getCategoryFilters(List<T> partialList)
	{
		List<String> categories = new ArrayList<String>();
		for(T payable: partialList) {
			
			String cat = payable.getCategory();
			
			if(!categories.contains(cat)) {
				categories.add(cat);
			}
		}
		categories.add("all");
		
		return categories;
	}
	
	public List<String> getOwnerFilters(List<T> partialList)
	{
		List<String> people = new ArrayList<String>();
		for(T payable: partialList) {
			
			String person = Integer.toString(payable.getOwnerId());
			
			if(!people.contains(person)) {
				people.add(person);
			}
		}
		people.add("all");
		
		return people;
	}
}
