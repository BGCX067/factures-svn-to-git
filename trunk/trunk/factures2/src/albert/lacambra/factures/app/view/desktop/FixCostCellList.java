package albert.lacambra.factures.app.view.desktop;

import java.util.Comparator;
import java.util.List;

import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.presenter.FixCostListPresenter;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;

public class FixCostCellList extends CellTable<FixCost>{

	private ListHandler<FixCost> sortHandler;
	
	Column<FixCost, String> idFixCost;
	Column<FixCost, String> startDate;
	Column<FixCost, String> endDate;
	Column<FixCost, String> quota;
	Column<FixCost, String> frequency; 
	Column<FixCost, String> monathlyQuota;
	Column<FixCost, String> category;
	Column<FixCost, String> extra;
	Column<FixCost, String> personId;
	
	public FixCostCellList()
	{
		super(FixCost.KEY_PROVIDER);
		sortHandler = initSortHandler();
		initColumns();
	}
	
	public void build(FixCostListPresenter presenter,  List<FixCost> ressource) {
		loadSortHandlers(ressource);
		loadFieldUpdaters(presenter);
	}
	
	public ListHandler<FixCost> initSortHandler()
	{
		 sortHandler = new ListHandler<FixCost>(null);
		 addColumnSortHandler(sortHandler);
		 
		 return sortHandler;
	}
	
	public void initColumns()
	{
		this.sortHandler = new ListHandler<FixCost>(null);
		addColumnSortHandler(sortHandler);
		
		idFixCost = new Column<FixCost, String>(new TextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getId() + "";
			}
		};
		
		startDate = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getStartDate();
			}
		};
		
		endDate = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getEndDate();
			}
		};
		
		quota = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getQuota() + "";
			}
		};
		
		frequency = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getFrequency() + "";
			}
		};
		
		monathlyQuota = new Column<FixCost, String>(new TextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getMonthlyPrice() + "";
			}
		};
		
		category = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getCategory();
			}
		};

		extra = new Column<FixCost, String>(new EditTextCell()) {
			@Override
			public String getValue(FixCost object) {
				return object.getExtra();
			}
		};

		Column<FixCost, String> personId = new Column<FixCost, String>(new TextCell())
		{
			@Override
			public String getValue(FixCost object) {
				int id = object.getOwnerId();
				switch(id) {
				case 0:
					return "unknown";
				case 1:
					return "albert";
				case 2:
					return "ruth";
				case 3:
					return "both";
				
				}
				return object.getOwnerId() + "";
			}
		};
		
		extra.setSortable(true);
		category.setSortable(true);
		monathlyQuota.setSortable(true);
		quota.setSortable(true);
		endDate.setSortable(true);
		startDate.setSortable(true);
		idFixCost.setSortable(true);
		frequency.setSortable(true);
		
		this.addColumn(idFixCost, "id");
		this.addColumn(personId);
		this.addColumn(startDate, "start date");
		this.addColumn(endDate, "end date");
		this.addColumn(quota, "price");
		this.addColumn(frequency, "frequency");
		this.addColumn(monathlyQuota, "monthly quota");
		this.addColumn(category, "category");
		this.addColumn(extra, "extra");
		this.setPageSize(25);
		this.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
	}

	private void loadFieldUpdaters(final FixCostListPresenter presenter) {
		
		category.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				if(!value.equals(object.getCategory())) {
					object.setCategory(value);
					presenter.fixCostUpdate(object);
				}
			}
		});
		
		frequency.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				Integer frequency = new Integer(value);
				if(frequency != object.getFrequency()) {
					object.setFrequency(frequency);
					presenter.fixCostUpdate(object);
//					presenter.getDataProvider().refresh();
				}
			}
		});
		
		quota.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				Float quota = new Float(value);
				if(quota != object.getQuota()) {
					object.setQuota(quota);
					presenter.fixCostUpdate(object);
//					presenter.getDataProvider().refresh();
				}
			}
		});
		extra.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				if(!value.equals(object.getExtra())) {
					object.setExtra(value);
					presenter.fixCostUpdate(object);
				}
			}
		});
		endDate.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				if(!value.equals(object.getEndDate())) {
					object.setEndDate(value);
					presenter.fixCostUpdate(object);
				}
			}
		});
		startDate.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				if(!value.equals(object.getStartDate())) {
					object.setStartDate(value);
					presenter.fixCostUpdate(object);
				}
			}
		});
	}

	public void loadSortHandlers(List<FixCost> ressource) {
		sortHandler.setList(ressource);
		
		sortHandler.setComparator(category , new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				return o1.getCategory().compareTo(o2.getCategory());
			}
		});
		
		sortHandler.setComparator(extra , new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				return o1.getExtra().compareTo(o2.getExtra());
			}
		});
		
		sortHandler.setComparator(frequency, new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				Integer p1 = new Integer(o1.getFrequency());
				Integer p2 = new Integer(o2.getFrequency());
				return p1.compareTo(p2);
			}
		});
	}
}





































