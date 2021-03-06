package net.shopxx.dao.impl;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.shopxx.Filter;
import net.shopxx.Order;

import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.FiBankbookBalanceDao;
import net.shopxx.entity.Country;
import net.shopxx.entity.FiBankbookBalance;
import net.shopxx.entity.Member;

import org.springframework.stereotype.Repository;

/**
 * Dao - 存折
 * 
 * @author gaoxiang
 * @version 5.0.3
 */
@Repository
public class FiBankbookBalanceDaoImpl extends BaseDaoImpl<FiBankbookBalance, Long> implements FiBankbookBalanceDao {
	/**
	 * 根据会员编号查询存折
	 */
	public List<FiBankbookBalance> findList(Member member,String type, Integer count,List<Filter> filters, List<Order> orders){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FiBankbookBalance> criteriaQuery = criteriaBuilder.createQuery(FiBankbookBalance.class);
		Root<FiBankbookBalance> root = criteriaQuery.from(FiBankbookBalance.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if(member != null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
		}
		if(type != null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("type"), type));
		}
		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, count, filters, orders);
	}
	 /* 查找实体对象分页
	 *
	 * @param member
	 *            会员
	 * @param country
	 *            国家
	 * @param pageable
	 *            分页信息
	 * @return 实体对象分页
	 */
	public Page<FiBankbookBalance> findPage(Member member, Country country, Pageable pageable){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<FiBankbookBalance> criteriaQuery = criteriaBuilder.createQuery(FiBankbookBalance.class);
		Root<FiBankbookBalance> root = criteriaQuery.from(FiBankbookBalance.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (member != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
		}
		if (country != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("country"), country));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}
	
	/**
	 * 根据member和type获取FiBankbookBalance
	 * @param member
	 * @param type
	 * @return
	 */
	public FiBankbookBalance find(Member member, FiBankbookBalance.Type type){
		String jpql = "SELECT fiBankbookBalance FROM FiBankbookBalance fiBankbookBalance WHERE fiBankbookBalance.member.usercode=:usercode AND fiBankbookBalance.type=:type";
		TypedQuery<FiBankbookBalance> query = entityManager.createQuery(jpql, FiBankbookBalance.class);
		query.setParameter("usercode", member.getUsercode());
		query.setParameter("type", type);
		query.setMaxResults(1);
		List<FiBankbookBalance> list= query.getResultList();
		if (null != list && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}
