package albert.lacambra.factures.app.view.desktop;

import albert.lacambra.factures.app.presenter.NewFixCostPresenter;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class NewFixCostView extends ViewImpl implements
		NewFixCostPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, NewFixCostView> {
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

	@Inject
	public NewFixCostView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	public Button getButton() {
		return button;
	}

	public RadioButton getWhoAlbert() {
		return whoAlbert;
	}

	public RadioButton getWhoRuth() {
		return whoRuth;
	}

	public RadioButton getWhoBoth() {
		return whoBoth;
	}

	public RadioButton getWhoUnknown() {
		return whoUnknown;
	}

	public TextBox getPrice() {
		return price;
	}

	public TextBox getStartDay() {
		return startDay;
	}

	public TextBox getStartMonth() {
		return startMonth;
	}

	public TextBox getStartYear() {
		return startYear;
	}

	public TextBox getEndDay() {
		return endDay;
	}

	public TextBox getEndMonth() {
		return endMonth;
	}

	public TextBox getEndYear() {
		return endYear;
	}

	public TextBox getCategory() {
		return category;
	}

	public TextBox getExtra() {
		return extra;
	}

	public Label getInfoLabel() {
		return infoLabel;
	}

	public TextBox getFrequency() {
		return frequency;
	}

	public CheckBox getActive() {
		return active;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public void setWhoAlbert(RadioButton whoAlbert) {
		this.whoAlbert = whoAlbert;
	}

	public void setWhoRuth(RadioButton whoRuth) {
		this.whoRuth = whoRuth;
	}

	public void setWhoBoth(RadioButton whoBoth) {
		this.whoBoth = whoBoth;
	}

	public void setWhoUnknown(RadioButton whoUnknown) {
		this.whoUnknown = whoUnknown;
	}

	public void setPrice(TextBox price) {
		this.price = price;
	}

	public void setStartDay(TextBox startDay) {
		this.startDay = startDay;
	}

	public void setStartMonth(TextBox startMonth) {
		this.startMonth = startMonth;
	}

	public void setStartYear(TextBox startYear) {
		this.startYear = startYear;
	}

	public void setEndDay(TextBox endDay) {
		this.endDay = endDay;
	}

	public void setEndMonth(TextBox endMonth) {
		this.endMonth = endMonth;
	}

	public void setEndYear(TextBox endYear) {
		this.endYear = endYear;
	}

	public void setCategory(TextBox category) {
		this.category = category;
	}

	public void setExtra(TextBox extra) {
		this.extra = extra;
	}

	public void setInfoLabel(Label infoLabel) {
		this.infoLabel = infoLabel;
	}

	public void setFrequency(TextBox frequency) {
		this.frequency = frequency;
	}

	public void setActive(CheckBox active) {
		this.active = active;
	}
	
	
}
