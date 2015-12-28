package albert.lacambra.factures.app.presenter.navibar;

import com.google.gwt.user.client.ui.IsWidget;


public interface NaviBarView extends IsWidget
{
	public void setPresenter(Presenter presenter);
	
	public interface Presenter
	{
		public void goToPlace(int place);
	}
}
