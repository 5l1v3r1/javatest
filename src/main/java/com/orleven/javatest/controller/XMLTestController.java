package com.orleven.javatest.controller;

import com.orleven.javatest.bean.MySaxHandler;
import com.orleven.javatest.bean.User;
import com.orleven.javatest.util.DataUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;


/**
 * @Name: com.orleven.javatest.controller
 * @Author: orleven
 * @Date: 18-1-8
 * @Description:
 */
@Controller
@RequestMapping("/XmlTest")
public class XMLTestController {

    @ResponseBody
    @RequestMapping(value= "/XMl/Test",method= RequestMethod.POST)
    public String XMlSqlInject(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        List<User> res = new ArrayList();
        result.put("success",false);
        String url = "config/users.xml";
        try {
            //创建一个解析XML的工厂对象
            SAXParserFactory parserFactory=SAXParserFactory.newInstance();
            //创建一个解析XML的对象
            SAXParser parser=parserFactory.newSAXParser();
            //创建一个解析助手类
            MySaxHandler myhandler=new MySaxHandler();
            parser.parse(url, myhandler);
            res = myhandler.getUserList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.put("data",res);
        result.put("success",true);
        return DataUtil.toJson(result);
    }



    @ResponseBody
    @RequestMapping(value= "/XMl/XXEInject",method= RequestMethod.POST)
    public String XMlXXEInject(HttpServletRequest request){
        String data = request.getParameter("data");
        Map<String,Object> result = new HashMap<>();
        List<User> res = new ArrayList();
        result.put("success",false);
        if (data!=null&&!data.isEmpty()){
            // 1.获取基于SAX的解析器的实例
            try {
                System.out.println(data);
                SAXParserFactory factory = SAXParserFactory.newInstance();
                // 2.创建一个SAXParser实例
                SAXParser saxParser = factory.newSAXParser();
                // 3.解析
                MySaxHandler myhandler = new MySaxHandler();

                saxParser.parse(new ByteArrayInputStream(data.getBytes()), myhandler);
                res = myhandler.getUserList();
                result.put("data", res);
                result.put("success", true);
            }catch (Exception e){
                e.printStackTrace();
            }
            return DataUtil.toJson(result);
        }
        result.put("message", "Error!");
        return DataUtil.toJson(result);
    }


    @ResponseBody
    @RequestMapping(value= "/XMl/XXEFormat",method= RequestMethod.POST)
    public String XXEFormat(HttpServletRequest request){
        String data = request.getParameter("data");
        Map<String,Object> result = new HashMap<>();
        List<User> res = new ArrayList();
        result.put("success",false);
        if (data!=null&&!data.isEmpty()){
            // 1.获取基于SAX的解析器的实例
            try {
                System.out.println(data);
                SAXParserFactory factory = SAXParserFactory.newInstance();
                factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
                factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
                factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
                // 2.创建一个SAXParser实例
                SAXParser saxParser = factory.newSAXParser();
                // 3.解析
                MySaxHandler myhandler = new MySaxHandler();

                saxParser.parse(new ByteArrayInputStream(data.getBytes()), myhandler);
                res = myhandler.getUserList();
                result.put("data", res);
                result.put("success", true);
            }catch (Exception e){
                e.printStackTrace();
            }
            return DataUtil.toJson(result);
        }
        result.put("message", "Error!");
        return DataUtil.toJson(result);
    }
}
