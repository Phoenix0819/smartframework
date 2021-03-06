package org.smart4j.smartframework.bean;

import org.smart4j.smartframework.util.CastUtil;

import java.util.Map;

/**
 * Created by lenovo on 2016-12-29.
 */
public class Param {
    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public long getLong(String name){
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String,Object> getMap(){
        return paramMap;
    }
}
