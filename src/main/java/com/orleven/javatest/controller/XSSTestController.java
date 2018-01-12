package com.orleven.javatest.controller;

import com.orleven.javatest.util.DataUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Name: com.orleven.javatest.controller
 * @Author: orleven
 * @Date: 18-1-9
 * @Description:
 */
@Controller
@RequestMapping("/XXSTest")
public class XSSTestController {

    public XSSTestController(){
        super();
    }

    @ResponseBody
    @RequestMapping(value= "/XXS/XXSInject",method= RequestMethod.POST)
    public String XXSXXSInject(HttpServletRequest request){
        String data = request.getParameter("data");
        Map<String,Object> result = new HashMap<>();

        result.put("success",false);
        if (data!=null&&!data.isEmpty()){

            return data;
        }
        result.put("message", "Error!");
        return "404";
    }

    @ResponseBody
    @RequestMapping(value= "/XXS/XXSFormat",method= RequestMethod.POST)
    public String XXSXXSFormat(HttpServletRequest request){
        String data = request.getParameter("data");
        Map<String,Object> result = new HashMap<>();

        result.put("success",false);
        if (data!=null&&!data.isEmpty()){

            return HtmlUtils.htmlEscape(data);
        }
        result.put("message", "Error!");
        return "404";
    }


}
