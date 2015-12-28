package albert.lacambra.factures.app.model;

public abstract class Payable implements HasCategory, HasMonthlyPrice, HasOwner, Serializable{
	public enum Type 
	{
		FIX_COST, INVOICE
	}
	
	@Override
	public String toString() {
		return getOwnerId() + " " + getCategory() + " " + getMonthlyPrice() + " " +  super.toString();
	}

	public abstract int getId();
	public abstract String getDate();
	public abstract String getExtra();
}
