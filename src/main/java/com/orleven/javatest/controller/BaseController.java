package com.orleven.javatest.controller;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public Map<String,Object> map = new HashMap();

    public BaseController(){

    }

    /**
     * Init map
     * @param map
     * @return
     */
    public Map<String,Object> initMap(Map<String,Object> map){
        return map;
    }
}
