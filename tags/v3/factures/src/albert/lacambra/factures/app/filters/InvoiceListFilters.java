package albert.lacambra.factures.app.filters;

import java.util.ArrayList;
import java.util.List;

import albert.lacambra.factures.app.model.Invoice;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class InvoiceListFilters extends PayableListFilter<Invoice>
{
	public InvoiceListFilters()
	{
		
	}
	
	public List<Invoice> filterDateInvoice(String whenMatcher, List<Invoice> invoicesList)
	{
		RegExp regExp = RegExp.compile("^" + whenMatcher + "-[0-3]{1}[0-9]{1}$");
		
		ArrayList<Invoice> newList = new ArrayList<Invoice>();
		
		for(Invoice invoice : invoicesList) {
			String date = invoice.getDate();
			MatchResult matcher = regExp.exec(date);
			
			if(matcher != null){
				newList.add(invoice);
			}
		}
		
		return newList;
	}
	
	public List<String> getFilterDates(List<Invoice> partialList) {
		List<String> dates = new ArrayList<String>();
		for(Invoice invoice : partialList) {
			
			String date = invoice.getDate().substring(0, 7);
			
			if(!dates.contains(date)) {
				dates.add(date);
			}
		}
		dates.add("all");
		return dates;
	}
}
