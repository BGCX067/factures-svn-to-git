package albert.lacambra.factures.app.model;

import albert.lacambra.factures.app.model.jso.FixCostJso;

import com.google.gwt.view.client.ProvidesKey;

public class FixCost extends Payable
{
	private FixCostJso jso;

	public static final ProvidesKey<FixCost> KEY_PROVIDER = new ProvidesKey<FixCost>()
	{
		public Object getKey(FixCost item)
		{
			return item == null ? null : item.getId();
		}
	};

	public FixCost(String json)
	{
		jso = FixCostJso.buildFixCost(json);
	}

	public int getId()
	{
		return jso.getFixCostId();
	}

	public FixCost()
	{
		jso = FixCostJso.buildFixCost();
	}

	@Override
	public int getOwnerId()
	{
		return jso.getPersonId();
	}

	@Override
	public float getMonthlyPrice()
	{
		return jso.getQuota() * jso.getFrequency() / 12;
	}

	@Override
	public String getCategory()
	{
		return jso.getCategory();
	}

	@Override
	public String serialize()
	{
		return jso.serialize();
	}

	@Override
	public void setId(int id)
	{
		jso.setFixCostId(id);
	}

	public String getEndDate()
	{
		return jso.getEndDate();
	}

	public int getFrequency()
	{
		return jso.getFrequency();
	}

	public String getExtra()
	{
		return jso.getExtra();
	}

	public void setFrequency(int frequency)
	{
		jso.setFrequency(frequency);
	}

	public String getStartDate()
	{
		return jso.getStartDate();
	}

	public void setEndDate(String value)
	{
		jso.setEndDate(value);
	}

	public void setQuota(Float quota)
	{
		jso.setQuota(quota);
	}

	public void setStartDate(String value)
	{
		jso.setStartDate(value);
	}

	public float getQuota()
	{
		return jso.getQuota();
	}

	public void setExtra(String value)
	{
		jso.setExtra(value);
	}

	public void setCategory(String value)
	{
		jso.setCategory(value);
	}

	public void setActive(Boolean value)
	{
		jso.setActive(value);
	}

	public void setPersonId(Integer who)
	{
		jso.setPersonId(who);
	}

	@Override
	public String getDate() {
		return getStartDate();
	}

}
