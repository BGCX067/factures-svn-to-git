package albert.lacambra.factures.app.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import albert.lacambra.factures.app.model.Payable;

public abstract class Filter<T extends Payable> implements IsFilter<T> {
	
	/**
	 * MAp with all the matchers used and a boolean value indicating if the marched is used or not
	 */
	protected HashMap<String, Boolean> matchers = new HashMap<String, Boolean>();
	public final static String ALL = "all";
	
	@Override
	public void addMatcher(String matcher) {
		if(!matchers.containsKey(matcher)){
			matchers.put(matcher, false);
		}
	}

	@Override
	public void enableMatcher(String matcher) {
		if(matcher.equals(Filter.ALL)){ 
			disableAllMatchers();
		} else {
			matchers.put(matcher, true);
		}
	}

	@Override
	public void disableAllMatchers() {
		for( String key : matchers.keySet()) {
			matchers.put(key, false);
		}
	}
	
	protected boolean hasActiveMatchers(){
		return matchers.values().contains(true);
	}
	
	@Override
	public boolean matcherIsEnabled(String matcher) {
		return matchers.containsKey(matcher) && matchers.get(matcher);
	}
	
	@Override
	public void disableMatcher(String matcher) {
		if(matcherIsEnabled(matcher)) {
			matchers.put(matcher, false);
		}
	}
	
	@Override
	public List<String> getMatchers() {
		List<String> l = new ArrayList<String>();
		l.addAll(matchers.keySet());
		return l;
	}
}




















































