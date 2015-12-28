package albert.lacambra.factures.app.filters;

public class FilterItem {

	private String text;
	private String type;
	private int id;
	
	public FilterItem(int id, String text)
	{
		this.text = text;
		this.id = id;
	}
	
	public FilterItem(String text)
	{
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
