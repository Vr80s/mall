/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.shopxx.Filter;
import net.shopxx.Order;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.dao.ArticleTagDao;
import net.shopxx.entity.ArticleTag;
import net.shopxx.entity.Country;
import net.shopxx.service.ArticleTagService;

/**
 * Service - 文章标签
 * 
 * @author SHOP++ Team
 * @version 5.0.3
 */
@Service
public class ArticleTagServiceImpl extends BaseServiceImpl<ArticleTag, Long> implements ArticleTagService {

	@Inject
	private ArticleTagDao articleTagDao;

	@Transactional(readOnly = true)
	@Cacheable(value = "articleTag", condition = "#useCache")
	public List<ArticleTag> findList(Integer count, List<Filter> filters, List<Order> orders, boolean useCache) {
		return articleTagDao.findList(null, count, filters, orders);
	}
	
	/**
	 * 搜索文章标签分页
	 * 
	 * @param country
	 *            国家
	 * @param pageable
	 *            分页信息
	 * @return 文章分页
	 */
	@Transactional(readOnly = true)
	public Page<ArticleTag> findPage(Country country, Pageable pageable){
		return articleTagDao.findPage(country, pageable);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "articleTag", allEntries = true)
	public ArticleTag save(ArticleTag articleTag) {
		return super.save(articleTag);
	}

	@Override
	@Transactional
	@CacheEvict(value = "articleTag", allEntries = true)
	public ArticleTag update(ArticleTag articleTag) {
		return super.update(articleTag);
	}

	@Override
	@Transactional
	@CacheEvict(value = "articleTag", allEntries = true)
	public ArticleTag update(ArticleTag articleTag, String... ignoreProperties) {
		return super.update(articleTag, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "articleTag", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "articleTag", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "articleTag", allEntries = true)
	public void delete(ArticleTag articleTag) {
		super.delete(articleTag);
	}

}