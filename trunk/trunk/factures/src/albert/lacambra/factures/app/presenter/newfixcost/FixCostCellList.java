package albert.lacambra.factures.app.presenter.newfixcost;

import java.util.Comparator;

import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.presenter.fixcost.FixCostListView;
import albert.lacambra.factures.app.presenter.fixcost.FixCostListView.FixCostListPresenter;
import albert.lacambra.factures.app.presenter.ListProvider;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;

public class FixCostCellList extends CellTable<FixCost>{

	private FixCostListPresenter presenter;
	private ListProvider<FixCost> provider;
	private ListHandler<FixCost> sortHandler;
	
	public FixCostCellList()
	{
		super(FixCost.KEY_PROVIDER);
		sortHandler = initSortHandler();
		initColumns();
	}
	
	public void setPresenter(FixCostListView.FixCostListPresenter presenter)
	{
		this.presenter = presenter;
		provider = presenter.getDataProvider();
		sortHandler.setList(provider.getList());
	}
	
	public ListHandler<FixCost> initSortHandler()
	{
		 sortHandler = new ListHandler<FixCost>(null);
		 addColumnSortHandler(sortHandler);
		 
		 return sortHandler;
	}
	
	public void initColumns()
	{
		setAutoHeaderRefreshDisabled(true);
		setAutoFooterRefreshDisabled(true);
		
		Column<FixCost, String> idFixCost = new Column<FixCost, String>(new TextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getId() + "";
			}
		};
		
		idFixCost.setSortable(true);
		idFixCost.setDefaultSortAscending(false);
		
		sortHandler.setComparator(idFixCost , new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				Integer integer1 = new Integer(o1.getId());
				Integer integer2 = new Integer(o2.getId());
				return integer1.compareTo(integer2);
			}
		});
		
		Column<FixCost, String> startDate = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getStartDate();
			}
		};
		
		startDate.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				if(!value.equals(object.getStartDate())) {
					object.setStartDate(value);
					presenter.fixCostUpdate(object);
				}
			}
		});
		
		startDate.setSortable(true);
		sortHandler.setComparator(startDate , new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				return o1.getStartDate().compareTo(o2.getStartDate());
			}
		});
		
		Column<FixCost, String> endDate = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getEndDate();
			}
		};
		
		endDate.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				if(!value.equals(object.getEndDate())) {
					object.setEndDate(value);
					presenter.fixCostUpdate(object);
				}
			}
		});
		
		endDate.setSortable(true);
		sortHandler.setComparator(endDate , new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				return o1.getEndDate().compareTo(o2.getEndDate());
			}
		});
		
		Column<FixCost, String> quota = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getQuota() + "";
			}
		};
		
		quota.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				Float quota = new Float(value);
				if(quota != object.getQuota()) {
					object.setQuota(quota);
					presenter.fixCostUpdate(object);
					presenter.getDataProvider().refresh();
				}
			}
		});
		
		quota.setSortable(true);
		sortHandler.setComparator(quota, new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				Float p1 = new Float(o1.getQuota());
				Float p2 = new Float(o2.getQuota());
				return p1.compareTo(p2);
			}
		});
		
		Column<FixCost, String> frequency = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getFrequency() + "";
			}
		};
		
		frequency.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				Integer frequency = new Integer(value);
				if(frequency != object.getFrequency()) {
					object.setFrequency(frequency);
					presenter.fixCostUpdate(object);
					presenter.getDataProvider().refresh();
				}
			}
		});
		
		frequency.setSortable(true);
		sortHandler.setComparator(frequency, new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				Integer p1 = new Integer(o1.getFrequency());
				Integer p2 = new Integer(o2.getFrequency());
				return p1.compareTo(p2);
			}
		});
		
		Column<FixCost, String> monathlyQuota = new Column<FixCost, String>(new TextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getMonthlyPrice() + "";
			}
		};
		
		monathlyQuota.setSortable(true);
		sortHandler.setComparator(monathlyQuota, new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				Integer p1 = new Integer(o1.getFrequency());
				Integer p2 = new Integer(o2.getFrequency());
				return p1.compareTo(p2);
			}
		});

		Column<FixCost, String> category = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getCategory();
			}
		};
		
		category.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				if(!value.equals(object.getCategory())) {
					object.setCategory(value);
					presenter.fixCostUpdate(object);
				}
			}
		});
		
		sortHandler.setComparator(category , new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				return o1.getCategory().compareTo(o2.getCategory());
			}
		});
		
		category.setSortable(true);
		
		Column<FixCost, String> extra = new Column<FixCost, String>(new EditTextCell())
		{
			@Override
			public String getValue(FixCost object) {
				return object.getExtra();
			}
		};
		
		extra.setFieldUpdater(new FieldUpdater<FixCost, String>() {
			public void update(int index, FixCost object, String value) {
				if(!value.equals(object.getExtra())) {
					object.setExtra(value);
					presenter.fixCostUpdate(object);
				}
			}
		});
		
		sortHandler.setComparator(extra , new Comparator<FixCost>() {
			@Override
			public int compare(FixCost o1, FixCost o2) {
				return o1.getExtra().compareTo(o2.getExtra());
			}
		});
		
		extra.setSortable(true);
		
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

	public void updateFixCostList() {
		sortHandler.setList(provider.getList());
	}
}
