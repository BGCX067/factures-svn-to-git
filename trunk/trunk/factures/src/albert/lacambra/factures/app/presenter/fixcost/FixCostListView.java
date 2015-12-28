package albert.lacambra.factures.app.presenter.fixcost;

import java.util.List;

import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.presenter.ListProvider;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;

public interface FixCostListView extends IsWidget{

	public interface FixCostListPresenter {

		ListProvider<FixCost> getDataProvider();
		void fixCostUpdate(FixCost fixCost);
		void setListByOwner(String owner);
		void setListByCategory(String cat);

	}

	public void setPresenter(FixCostListPresenter presenter);
	

	public HasData<FixCost> getCellList();

	public void setTotalPrice(String string);
	public void setCategories(List<String> categoriesArray);
	public void updateFixCostList();


	void setOwner(List<String> ownerArray);
}
