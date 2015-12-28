package albert.lacambra.factures.app.view.desktop;

import java.util.Comparator;

import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.presenter.invoicelist.InvoiceListProvider;
import albert.lacambra.factures.app.presenter.invoicelist.InvoiceListView;
import albert.lacambra.factures.app.presenter.invoicelist.InvoiceListView.InvoiceListPresenter;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;

public class InvoiceCellList extends CellTable<Invoice>{

	private InvoiceListPresenter presenter;
	private InvoiceListProvider provider;
	private ListHandler<Invoice> sortHandler;
	
	public InvoiceCellList()
	{
		super(Invoice.KEY_PROVIDER);
		sortHandler = initSortHandler();
		initColumns();
	}
	
	public void setPresenter(InvoiceListView.InvoiceListPresenter presenter)
	{
		this.presenter = presenter;
		provider = presenter.getDataProvider();
		sortHandler.setList(provider.getList());
	}
	
	public ListHandler<Invoice> initSortHandler()
	{
		 sortHandler = new ListHandler<Invoice>(null);
		 addColumnSortHandler(sortHandler);
		 
		 return sortHandler;
	}
	
	public void initColumns()
	{
		setAutoHeaderRefreshDisabled(true);
		setAutoFooterRefreshDisabled(true);
		
		Column<Invoice, String> idInvoice = new Column<Invoice, String>(new TextCell())
		{
			@Override
			public String getValue(Invoice object) {
				return object.getId() + "";
			}
		};
		
		idInvoice.setSortable(true);
		idInvoice.setDefaultSortAscending(false);
		
		sortHandler.setComparator(idInvoice , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				Integer integer1 = new Integer(o1.getId());
				Integer integer2 = new Integer(o2.getId());
				return integer1.compareTo(integer2);
			}
		});
		
		Column<Invoice, String> date = new Column<Invoice, String>(new EditTextCell())
		{
			@Override
			public String getValue(Invoice object) {
				return object.getDate();
			}
		};
		
		date.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				if(!value.equals(object.getDate())) {
					object.setDate(value);
					presenter.invoiceUpdate(object);
				}
			}
		});
		
		date.setSortable(true);
		sortHandler.setComparator(date , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
		
		Column<Invoice, String> price = new Column<Invoice, String>(new EditTextCell())
		{
			@Override
			public String getValue(Invoice object) {
				return object.getPrice() + "";
			}
		};
		
		price.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				Float price = new Float(value);
				if(price != object.getPrice()) {
					object.setPrice(price);
					presenter.invoiceUpdate(object);
				}
			}
		});
		
		price.setSortable(true);
		sortHandler.setComparator(price, new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				Float p1 = new Float(o1.getPrice());
				Float p2 = new Float(o2.getPrice());
				return p1.compareTo(p2);
			}
		});

		Column<Invoice, String> category = new Column<Invoice, String>(new EditTextCell())
		{
			@Override
			public String getValue(Invoice object) {
				return object.getCategory();
			}
		};
		
		category.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				if(!value.equals(object.getCategory())) {
					object.setCategory(value);
					presenter.invoiceUpdate(object);
				}
			}
		});
		
		sortHandler.setComparator(category , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				return o1.getCategory().compareTo(o2.getCategory());
			}
		});
		
		category.setSortable(true);
		
		Column<Invoice, String> extra = new Column<Invoice, String>(new EditTextCell())
		{
			@Override
			public String getValue(Invoice object) {
				return object.getExtra();
			}
		};
		
		extra.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				if(!value.equals(object.getExtra())) {
					object.setExtra(value);
					presenter.invoiceUpdate(object);
				}
			}
		});
		
		sortHandler.setComparator(extra , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				return o1.getExtra().compareTo(o2.getExtra());
			}
		});
		
		extra.setSortable(true);
		
		Column<Invoice, String> personId = new Column<Invoice, String>(new TextCell())
		{
			@Override
			public String getValue(Invoice object) {
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
		
		this.addColumn(idInvoice, "id");
		this.addColumn(personId);
		this.addColumn(date, "date");
		this.addColumn(price, "price");
		this.addColumn(category, "category");
		this.addColumn(extra, "extra");
		this.setPageSize(25);
		this.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
	}

	public void updateInvoiceList() {
		sortHandler.setList(provider.getList());
	}
}
