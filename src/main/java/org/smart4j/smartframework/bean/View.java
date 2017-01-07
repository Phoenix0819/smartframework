package org.smart4j.smartframework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016-12-29.
 */
public class View {
    private String path;

    public String getPath() {
        return path;
    }

    private Map<String,Object> model;

    public Map<String, Object> getModel() {
        return model;
    }

    public View(String path) {
        this.path = path;
        model=new HashMap<>();
    }

    public View addModel(String key,Object value){
        model.put(key,value);
        return this;
    }

}
