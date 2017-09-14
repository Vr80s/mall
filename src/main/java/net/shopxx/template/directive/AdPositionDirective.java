/*
 * Copyright 2005-2017 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import net.shopxx.entity.Ad;
import net.shopxx.entity.AdPosition;
import net.shopxx.entity.Country;
import net.shopxx.service.AdPositionService;
import net.shopxx.service.CountryService;

/**
 * 模板指令 - 广告位
 * 
 * @author SHOP++ Team
 * @version 5.0.3
 */
@Component
public class AdPositionDirective extends BaseDirective {

	/**
	 * 变量名称
	 */
	private static final String VARIABLE_NAME = "adPosition";

	@Inject
	private AdPositionService adPositionService;
	@Inject
	private CountryService countryService;
	/**
	 * 执行
	 * 
	 * @param env
	 *            环境变量
	 * @param params
	 *            参数
	 * @param loopVars
	 *            循环变量
	 * @param body
	 *            模板内容
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Long id = getId(params);
		boolean useCache = useCache(params);
		
		Country  country = countryService.getDefaultCountry();
		AdPosition adPosition = adPositionService.find(id, useCache);
		/*Set<Ad> ads = adPosition.getAds();
		if(ads.size() > 0){
			for(Ad ad : ads){
				if(!ad.getCountry().equals(country)){
					ads.remove(ad);
				}
			}
		}*/
		
		setLocalVariable(VARIABLE_NAME, adPosition, env, body);
		
	}

}