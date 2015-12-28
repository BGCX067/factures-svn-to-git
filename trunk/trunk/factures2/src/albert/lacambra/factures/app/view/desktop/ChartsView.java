package albert.lacambra.factures.app.view.desktop;

import java.util.HashMap;
import java.util.Map;

import albert.lacambra.factures.app.presenter.ChartsPresenter;
import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.dev.javac.JdtCompiler.AdditionalTypeProviderDelegate;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.google.gwt.visualization.client.visualizations.corechart.PieChart;
import com.google.inject.Inject;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChartsView extends ViewImpl implements ChartsPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, ChartsView> {
	}

	@Inject
	public ChartsView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {

		if(slot == ChartsPresenter.SLOT_Charts){
			chartsPanel.clear();
			chartsPanel.add(content);
		} else if (slot == ChartsPresenter.SLOT_Filters){
			filtersPanel.clear();
			filtersPanel.add(content);
		} else if (slot == ChartsPresenter.SLOT_Menu){
			menuPanel.clear();
			menuPanel.add(content);
		} else {
			super.setInSlot(slot, content);
		}

	}

	@Override
	public void addToSlot(Object slot, Widget content) {
		
		if(slot == ChartsPresenter.SLOT_Charts){
			chartsPanel.add(content);
		} else {
			super.addToSlot(slot, content);
		}
	}

	private Options createOptions(String title) {
		Options options = Options.create();
		options.setWidth(400);
		options.setHeight(240);
		options.setTitle(title);
		return options;
	} 

	@Override
	public void loadChart(final HashMap<String, Float> categories, final String title) {


		Runnable onLoadCallback = new Runnable() {
			public void run() {

				final DataTable data = DataTable.create();

				data.addColumn(ColumnType.STRING, "Category");
				data.addColumn(ColumnType.NUMBER, "total");
				data.addRows(categories.size());
				int i = 0;

				for(Map.Entry<String, Float> cat : categories.entrySet()){

					data.setValue(i, 0, cat.getKey());
					data.setValue(i, 1, cat.getValue());
					i++;
				}

				PieChart pie = new PieChart(data, createOptions(title));
				addToSlot(ChartsPresenter.SLOT_Charts, pie);
			}
		};

		VisualizationUtils.loadVisualizationApi(onLoadCallback, PieChart.PACKAGE);
	}

	@UiField HTMLPanel container;
	@UiField VerticalPanel menuPanel;
	@UiField VerticalPanel chartsPanel;
	@UiField HTMLPanel filtersPanel;
}













































