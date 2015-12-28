package albert.lacambra.factures.app.presenter;

import java.util.List;

import com.google.gwt.view.client.ListDataProvider;

public class ListProvider<T> extends ListDataProvider<T>{
	public void loadData(List<T> list)
	{
		List<T> oldList = getList();
		oldList.clear();
		oldList.addAll(list);
		setList(oldList);
		refresh();
	}
}
