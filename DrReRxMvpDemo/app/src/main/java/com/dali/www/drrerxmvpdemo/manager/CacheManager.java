package com.dali.www.drrerxmvpdemo.manager;

import com.dali.www.drrerxmvpdemo.base.Constant;
import com.dali.www.drrerxmvpdemo.bean.User;
import com.dali.www.drrerxmvpdemo.utils.ACache;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qinkangli on 2017/2/13.
 */

public class CacheManager {

    private volatile static CacheManager singleton;

    private CacheManager() {

    }

    public static CacheManager getInstance() {
        if (singleton == null) {
            synchronized (CacheManager.class) {
                if (singleton == null) {
                    singleton = new CacheManager();
                }
            }
        }
        return singleton;
    }

    /**
     * 获取缓存列表
     *
     * @return
     */
    public List<User.DataBean> getCacheList() {
        List<User.DataBean> list = (ArrayList<User.DataBean>) ACache.get(new File(Constant.PATH_DATA)).getAsObject("cache");
        return list == null ? null : list;
    }

    /**
     * 存储缓存里列表
     * @param list
     */
    public void putCacheList(List<User.DataBean> list) {
        ACache.get(new File(Constant.PATH_DATA)).put("cache", (Serializable) list);
    }

}
