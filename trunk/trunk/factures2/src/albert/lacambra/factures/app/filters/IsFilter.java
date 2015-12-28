package albert.lacambra.factures.app.filters;

import java.util.List;
import albert.lacambra.factures.app.model.Payable;
import albert.lacambra.factures.app.presenter.FiltersPresenter.FilterTypes;

/**
 * A filter consist of several matchers associated to a <i>Type</i>. The matchers are taken from a given resources list. 
 * When a filter is fired (clicked) all the values from a given list that match with the fired matcher are returned. 
 * @author albert
 * @param <T>
 */
public interface IsFilter<T extends Payable> {
	
	/**
	 * applies the filter value to the received value list 
	 * @param toFilter
	 * @return
	 */
	public List<T> doFilter(List<T> toFilter);
	
	/**
	 * fetch the filter tags to be used from a given resources list
	 * @param partialList
	 * @return
	 */
	List<String> getFiltersValues(List<T> partialList);
	
	/**
	 * Adds a matcher to the filter
	 * @param matcher
	 */
	public void addMatcher(String matcher);
	public void enableMatcher(String matcher);
	public void disableMatcher(String matcher);
	public void disableAllMatchers();
	
	/**
	 * Test if the given matcher exists and if it is enabled
	 * @param matcher
	 * @return
	 */
	public boolean matcherIsEnabled(String matcher);
	
	/**
	 * Returns the type of the current filter
	 * @return
	 */
	public abstract FilterTypes getType();
	
	
	public List<String> getMatchers(); 
}
