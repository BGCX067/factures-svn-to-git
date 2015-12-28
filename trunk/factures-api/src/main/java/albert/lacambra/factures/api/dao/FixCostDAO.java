package albert.lacambra.factures.api.dao;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import albert.lacambra.factures.api.HibernateUtil;
import albert.lacambra.factures.api.models.FixCost;

public class FixCostDAO {

	public int addCost(FixCost cost){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if(cost.isActive()) {
			cost.setEndDate(null);
		}
		session.save(cost);
		return cost.getFixCostId();
	}
	
	public int updateCost(FixCost cost){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.update(cost);
		return cost.getFixCostId();
	}
	
	public int deleteCost(int costId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = session.getNamedQuery("fixCost.delete").setParameter("fixCostId", costId);
		int deleted = q.executeUpdate();
		return deleted;
	}
	
	public List<FixCost> getAllCosts(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = session.getNamedQuery("fixCost.select.all");
		
		@SuppressWarnings("unchecked")
		List<FixCost> list = q.list();
		return list;
	}
	
	public FixCost getCost(int costId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query q = session.getNamedQuery("fixCost.select").setParameter("fixCostId", costId);
		return (FixCost) q.uniqueResult();
	}
}
