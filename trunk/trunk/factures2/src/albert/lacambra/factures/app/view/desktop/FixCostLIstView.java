package albert.lacambra.factures.app.view.desktop;

import albert.lacambra.factures.app.presenter.FixCostListPresenter;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class FixCostLIstView extends ViewImpl implements
		FixCostListPresenter.MyView {

	private final Widget widget;
	private FixCostCellList fixCostCellList = new FixCostCellList();
	
	@UiField HTMLPanel fixcCosts;
	@UiField HTMLPanel filterPanel;

	public interface Binder extends UiBinder<Widget, FixCostLIstView> {
	}

	@Inject
	public FixCostLIstView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(fixCostCellList);
		fixcCosts.add(fixCostCellList);
		fixcCosts.add(pager);
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		
		if(content == null) filterPanel.clear();
		
		if(slot == FixCostListPresenter.SLOT_filters) {
			filterPanel.clear();
			filterPanel.add(content);
		}
		else {
			super.setInSlot(slot, content);
		}
	}
	
	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public FixCostCellList getFixCostCellList() {
		return fixCostCellList;
	}
	
	
}
