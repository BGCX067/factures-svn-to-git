package albert.lacambra.factures.app.view.desktop;

import albert.lacambra.factures.app.model.Invoice;
import albert.lacambra.factures.app.presenter.newinvoice.NewInvoiceView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewInvoiceViewImpl extends Composite implements NewInvoiceView {

	private Timer t;
	
	private static NewInvoiceViewImplUiBinder uiBinder = GWT
			.create(NewInvoiceViewImplUiBinder.class);

	interface NewInvoiceViewImplUiBinder extends
			UiBinder<Widget, NewInvoiceViewImpl> {
	}

	public NewInvoiceViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		attachHandlers();
		
		t = new Timer(){
			@Override
			public void run() {
				infoLabel.setVisible(false);
			}
		};
	}

	@UiField Button button;
	@UiField RadioButton whoAlbert;
	@UiField RadioButton whoRuth;
	@UiField RadioButton whoBoth;
	@UiField RadioButton whoUnknown;
	@UiField TextBox price;
	@UiField TextBox day;
	@UiField TextBox month;
	@UiField TextBox year;
	@UiField TextBox category;
	@UiField TextBox extra;
	@UiField Label infoLabel;
	
	private NewInvoicePresenter presenter;

	@Override
	public void setPresenter(NewInvoicePresenter presenter) {
		this.presenter = presenter;
		restartFields();
	}
	
	@UiHandler("button")
	void onClick(ClickEvent e) {
		Invoice invoice = new Invoice();
		invoice.setCategory(category.getText());
		invoice.setDate(getDate());
		invoice.setExtra(extra.getText());
		invoice.setPersonId(getWho());
		invoice.setPrice(new Float(price.getText()));
		presenter.addInvoice(invoice);
	}
	
	private String getDate()
	{
		String y = year.getValue().matches("^[0-9]{4}$") ? year.getValue() : "0000";
		String m = month.getValue().matches("^[0-1]{0,1}[0-9]{1}$") ? month.getValue() : "00";
		String d = day.getValue().matches("^[0-3]{0,1}[0-9]{1}$") ? day.getValue() : "00";
		String date = y + "-" + m + "-" + d;
		
		return date;
	}
	
	private Integer getWho() 
	{
		if(whoAlbert.getValue()) {
			return 1;
		}
		if(whoRuth.getValue()) {
			return 2;
		}
		if(whoBoth.getValue()) {
			return 3;
		}
		if(whoUnknown.getValue()) {
			return 0;
		}
		return 3;
	}
	
	private void restartFields() {
		whoBoth.setValue(true);
		price.setValue("");
		price.setFocus(true);
		day.setValue("");
		month.setValue("");
		year.setValue("2012");
		category.setValue("");
		extra.setValue("");
	}
	
	@Override
	public void onSuccess(String invoice) 
	{
		restartFields();
		
		t.cancel();
		t.schedule(5000);
		infoLabel.setText("Success:" + invoice);
		infoLabel.setVisible(true);
	}
	
	private void attachHandlers()
	{
		day.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(day.getValue().length() >= 2 && event.getNativeKeyCode() != 9) {
					System.out.println(event.getNativeKeyCode());
					month.setFocus(true);
				}
			}
		});
		
		month.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(month.getValue().length() >= 2 && event.getNativeKeyCode() != 9) {
					System.out.println(event.getNativeKeyCode());
					year.setFocus(true);
				}
			}
		});
		
		year.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(year.getValue().length() >= 2 && event.getNativeKeyCode() != 9) {
					System.out.println(event.getNativeKeyCode());
					category.setFocus(true);
				}
			}
		});
	}
}













































