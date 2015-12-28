package albert.lacambra.factures.app.view.desktop;

import java.util.Comparator;
import java.util.List;

import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.presenter.InvoiceListPresenter;

import com.google.gwt.cell.client.ClickableTextCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;

public class InvoiceCellList extends CellTable<Invoice>{

	private Column<Invoice, String> date;
	private Column<Invoice, String> idInvoice;
	private Column<Invoice, String> extra;
	private Column<Invoice, String> price;
	private Column<Invoice, String> category;
	private Column<Invoice, String> personId;
	private Column<Invoice, String> delete;
	private ListHandler<Invoice> sortHandler;

	public InvoiceCellList()
	{
		super(Invoice.KEY_PROVIDER);
		initColumns();
	}

	public void build(InvoiceListPresenter presenter,  List<Invoice> ressource) {
		loadSortHandlers(ressource);
		loadFieldUpdaters(presenter);
	}

	private void initColumns()
	{
		this.sortHandler = new ListHandler<Invoice>(null);
		addColumnSortHandler(sortHandler);

		idInvoice = new Column<Invoice, String>(new TextCell()) {
			@Override
			public String getValue(Invoice object) {
				return String.valueOf(object.getId());
			}
		};

		date = new Column<Invoice, String>(new EditTextCell()) {
			@Override
			public String getValue(Invoice object) {
				return object.getDate();
			}
		};

		price = new Column<Invoice, String>(new EditTextCell()) {
			@Override
			public String getValue(Invoice object) {
				return String.valueOf(object.getPrice());
			}
		};

		category = new Column<Invoice, String>(new EditTextCell()) {
			@Override
			public String getValue(Invoice object) {
				return object.getCategory();
			}
		};

		extra = new Column<Invoice, String>(new EditTextCell()) {
			@Override
			public String getValue(Invoice object) {
				return object.getExtra();
			}
		};

		personId = new Column<Invoice, String>(new TextCell()) {
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
				return String.valueOf(object.getOwnerId());
			}
		};
		
		delete = new Column<Invoice, String>(new ClickableTextCell()){

			@Override
			public String getValue(Invoice object) {
				return "delete";
			}
		};
		
		idInvoice.setSortable(true);
		idInvoice.setDefaultSortAscending(false);
		extra.setSortable(true);
		date.setSortable(true);
		category.setSortable(true);
		price.setSortable(true);

		this.addColumn(idInvoice, "id");
		this.addColumn(personId);
		this.addColumn(date, "date");
		this.addColumn(price, "price");
		this.addColumn(category, "category");
		this.addColumn(extra, "extra");
		this.addColumn(delete, "delete");
		this.setPageSize(25);
		this.setKeyboardPagingPolicy(KeyboardPagingPolicy.CHANGE_PAGE);
	}

	public void loadSortHandlers(List<Invoice> ressources) {

		sortHandler.setList(ressources);
		
		sortHandler.setComparator(idInvoice , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				Integer integer1 = new Integer(o1.getId());
				Integer integer2 = new Integer(o2.getId());
				return integer1.compareTo(integer2);
			}
		});

		sortHandler.setComparator(date , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});

		sortHandler.setComparator(price, new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				Float p1 = new Float(o1.getPrice());
				Float p2 = new Float(o2.getPrice());
				return p1.compareTo(p2);
			}
		});

		sortHandler.setComparator(category , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				return o1.getCategory().compareTo(o2.getCategory());
			}
		});

		sortHandler.setComparator(extra , new Comparator<Invoice>() {
			@Override
			public int compare(Invoice o1, Invoice o2) {
				return o1.getExtra().compareTo(o2.getExtra());
			}
		});
	}

	private void loadFieldUpdaters(final InvoiceListPresenter presenter) {
		date.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				if(!value.equals(object.getDate())) {
					object.setDate(value);
					presenter.invoiceUpdate(object);
				}
			}
		});

		category.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				if(!value.equals(object.getCategory())) {
					object.setCategory(value);
					presenter.invoiceUpdate(object);
				}
			}
		});

		price.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				Float price = new Float(value);
				if(price != object.getPrice()) {
					object.setPrice(price);
					presenter.invoiceUpdate(object);
				}
			}
		});

		extra.setFieldUpdater(new FieldUpdater<Invoice, String>() {
			public void update(int index, Invoice object, String value) {
				if(!value.equals(object.getExtra())) {
					object.setExtra(value);
					presenter.invoiceUpdate(object);
				}
			}
		});
		
		delete.setFieldUpdater(new FieldUpdater<Invoice, String>() {

			@Override
			public void update(int index, Invoice object, String value) {
				presenter.deleteInvoice(object);
				
			}
		});

	}

}
