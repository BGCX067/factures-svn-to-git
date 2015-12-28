package albert.lacambra.factures.app.model;

public abstract class Payable implements HasCategory, HasMonthlyPrice, HasOwner, Serializable{
	public enum Type 
	{
		FIX_COST, INVOICE
	}
}
