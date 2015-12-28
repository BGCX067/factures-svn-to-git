package albert.lacambra.factures.app.rest;

public interface PayableCallback<T> {
	public void onSuccess(T ressource);
	public void onFailure(Throwable e);
}
