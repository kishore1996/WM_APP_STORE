/*Copyright (c) 2015-2016 imaginea.com All Rights Reserved.
 This software is the confidential and proprietary information of imaginea.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with imaginea.com*/
package com.wm_app_store.wm_app_store.dao;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wavemaker.runtime.data.dao.WMGenericDaoImpl;
import com.wavemaker.runtime.data.dao.query.types.wmql.WMQLTypeHelper;

import com.wm_app_store.wm_app_store.AppScreenshots;

/**
 * Specifies methods used to obtain and modify AppScreenshots related information
 * which is stored in the database.
 */
@Repository("WM_APP_STORE.AppScreenshotsDao")
public class AppScreenshotsDao extends WMGenericDaoImpl<AppScreenshots, Integer> {

    @Autowired
    @Qualifier("WM_APP_STORETemplate")
    private HibernateTemplate template;

    @Autowired
    @Qualifier("WM_APP_STOREWMQLTypeHelper")
    private WMQLTypeHelper wmqlTypeHelper;


    @Override
    public HibernateTemplate getTemplate() {
        return this.template;
    }

    @Override
    public WMQLTypeHelper getWMQLTypeHelper() {
        return this.wmqlTypeHelper;
    }

}