package com.orleven.javatest.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Name: com.orleven.javatest.bean
 * @Author: orleven
 * @Date: 18-1-8
 * @Description:
 */
@Component
@Scope("prototype")
public class User {

    private String id;

    private String uname;

    private String passwd;

    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUname() {
        return uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setId(String id) {
        this.id = id;
    }
}
