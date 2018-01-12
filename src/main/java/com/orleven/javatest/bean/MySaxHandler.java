package com.orleven.javatest.bean;

import java.util.List;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
/**
 * @Name: com.orleven.javatest
 * @Author: orleven
 * @Date: 18-1-8
 * @Description:
 */
public class MySaxHandler extends DefaultHandler{

    //声明一个装载User类型的List
    private List<User> userList = new ArrayList();
    //声明一个User类型的变量
    private User user;
    //声明一个字符串变量
    private String content;



    /**
     * 当SAX解析器解析到XML文档开始时，会调用的方法
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    /**
     * 当SAX解析器解析到XML文档结束时，会调用的方法
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    /**
     * 当SAX解析器解析到某个属性值时，会调用的方法
     * 其中参数ch记录了这个属性值的内容
     */
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        content = new String(ch, start, length);
    }

    /**
     * 当SAX解析器解析到某个元素开始时，会调用的方法
     * 其中localName记录的是元素属性名
     */
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if("user".equals(qName)){
            user = new User(); //新建User对象
        }
    }

    /**
     * 当SAX解析器解析到某个元素结束时，会调用的方法
     * 其中localName记录的是元素属性名
     */
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
;
        if("name".equals(qName)){
            user.setName(content);
        }else if("uname".equals(qName)){
            user.setUname(content);
        }else if("id".equals(qName)){
            user.setId(content);
        }else if("passwd".equals(qName)){
            user.setPasswd(content);
        }else if("user".equals(qName)){
            userList.add(user); //将Beauty对象加入到List中
        }
    }

    public String getList(){
        String result = "";

        for (User b : userList) {
            result += b.toString();
        }
        return  result;
    }

    public List<User> getUserList() {
        return userList;
    }
}
