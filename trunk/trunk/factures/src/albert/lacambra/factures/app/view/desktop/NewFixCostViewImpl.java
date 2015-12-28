package albert.lacambra.factures.app.view.desktop;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import albert.lacambra.factures.app.model.FixCost;
import albert.lacambra.factures.app.presenter.newfixcost.NewFixCostView;

public class NewFixCostViewImpl extends Composite implements NewFixCostView {

	private Timer t;
	
	private static NewFixCostViewImplUiBinder uiBinder = GWT
			.create(NewFixCostViewImplUiBinder.class);

	interface NewFixCostViewImplUiBinder extends
			UiBinder<Widget, NewFixCostViewImpl> {
	}

	
	public NewFixCostViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		attachHandlers(startDay, startMonth, startYear);
		attachHandlers(endDay, endMonth, endYear);
		
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
	@UiField TextBox startDay;
	@UiField TextBox startMonth;
	@UiField TextBox startYear;
	@UiField TextBox endDay;
	@UiField TextBox endMonth;
	@UiField TextBox endYear;
	@UiField TextBox category;
	@UiField TextBox extra;
	@UiField Label infoLabel;
	@UiField TextBox frequency;
	@UiField CheckBox active;
	
	private NewInvoicePresenter presenter;

	@UiHandler("button")
	void onClick(ClickEvent e) {
		FixCost fixCost = new FixCost();
		fixCost.setCategory(category.getText());
		fixCost.setStartDate(getStartDate());
		fixCost.setEndDate(getEndDate());
		fixCost.setFrequency(Integer.parseInt(frequency.getValue()));
		fixCost.setExtra(extra.getText());
		fixCost.setPersonId(getWho());
		fixCost.setQuota(new Float(price.getText()));
		fixCost.setActive(active.getValue());
		presenter.addFixCost(fixCost);
	}
	
	private String getStartDate()
	{
		return getDate(startDay, startMonth, startYear);
	}
	
	private String getEndDate()
	{
		return getDate(endDay, endMonth, endYear);
	}
	
	private String getDate(TextBox day, TextBox month, TextBox year)
	{
		if(year.getValue() == null || month.getValue() == null || year.getValue() == null)
		{
			return null;
		}
		
		String y = year.getValue().matches("^[0-9]{4}$") ? year.getValue() : "0000";
		String m = month.getValue().matches("^[0-1]{0,1}[0-9]{1}$") ? month.getValue() : "00";
		String d = day.getValue().matches("^[0-3]{0,1}[0-9]{1}$") ? day.getValue() : "00";
		String date = y + "-" + m + "-" + d;
		
		return date;
	}
	
	@Override
	public void setPresenter(NewInvoicePresenter presenter) {
		this.presenter = presenter;
		restartFields();
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
		return 1;
	}
	
	private void restartFields() {
		whoAlbert.setValue(true);
		price.setValue("");
		price.setFocus(true);
		startDay.setValue("");
		startMonth.setValue("");
		startYear.setValue("2012");
		endDay.setValue("");
		endMonth.setValue("");
		endYear.setValue("");
		category.setValue("");
		extra.setValue("");
		frequency.setValue("");
		active.setValue(true);
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
	
	
	
	private void attachHandlers(final TextBox day, final TextBox month, final TextBox year)
	{
		day.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(day.getValue().length() >= 2 && event.getNativeKeyCode() != 9) {
					month.setFocus(true);
				}
			}
		});
		
		month.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(month.getValue().length() >= 2 && event.getNativeKeyCode() != 9) {
					year.setFocus(true);
				}
			}
		});
		
		year.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(year.getValue().length() >= 2 && event.getNativeKeyCode() != 9) {
//					category.setFocus(true);
				}
			}
		});
		
		
	}

}
