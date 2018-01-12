package com.orleven.javatest.controller;

import com.orleven.javatest.util.CodeUtil;
import com.orleven.javatest.util.DataUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * @Name: com.orleven.javatest.controller
 * @Author: orleven
 * @Date: 18-1-8
 * @Description:
 */
@Controller
@RequestMapping("/SqlTest")
public class SqlTestController {

    public SqlTestController(){
        super();
    }

    @ResponseBody
    @RequestMapping(value= "/Mysql/SQLInject",method= RequestMethod.POST)
    public String MysqlSqlInject(HttpServletRequest request){
        String id = request.getParameter("id");
        Map<String,Object> result = new HashMap<>();
        List<Object> res = new ArrayList();
        result.put("success",false);
        if (id!=null&&!id.isEmpty()){
            //声明Connection对象
            Connection con;
            //驱动程序名
            String driver = "com.mysql.jdbc.Driver";
            //URL指向要访问的数据库名mydata
            String url = "jdbc:mysql://localhost:3306/test";
            //MySQL配置时的用户名
            String user = "root";
            //MySQL配置时的密码
            String password = "123456";
            //遍历查询结果集
            try {
                //加载驱动程序
                Class<?> aClass = Class.forName(driver);
                //1.getConnection()方法，连接MySQL数据库！！
                con = DriverManager.getConnection(url,user,password);
                if(!con.isClosed()) {
                    //2.创建statement类对象，用来执行SQL语句！！
                    Statement statement = con.createStatement();
                    //要执行的SQL语句
                    String sql = "select * from users where id='" + id + "'";
                    //3.ResultSet类，用来存放获取的结果集！！
                    ResultSet rs = statement.executeQuery(sql);
                    System.out.println("-----------------");
                    System.out.println("执行Sql语句:    "+sql);
                    System.out.println("执行结果如下所示:");
                    String name, uname;
                    while (rs.next()) {
                        //获取stuname这列数据
                        uname = rs.getString("uname");
                        //获取stuid这列数据
                        name = rs.getString("name");
                        //输出结果
                        System.out.println(uname + "\t" + name);
                        res.add(new String[]{"id: "+rs.getString("id")
                                + ",uname:"+rs.getString("uname")
                                + ",passwd:"+rs.getString("passwd")
                                + ",name:"+rs.getString("name")}
                        );
                    }
                    rs.close();
                    con.close();
                }
            } catch(ClassNotFoundException e) {
                //数据库驱动类异常处理
                e.printStackTrace();
            } catch(SQLException e) {
                //数据库连接失败异常处理
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
            result.put("data",res);
            result.put("success",true);
            return DataUtil.toJson(result);
        }
        result.put("message", "Error!");
        return DataUtil.toJson(result);
    }


    @ResponseBody
    @RequestMapping(value= "/Mysql/SQLFormat",method= RequestMethod.POST)
    public String MysqlSQLFormat(HttpServletRequest request){
        String id = request.getParameter("id");
        Map<String,Object> result = new HashMap<>();
        List<Object> res = new ArrayList();
        result.put("success",false);
        if (id!=null&&!id.isEmpty()){
            //声明Connection对象
            Connection con;
            //驱动程序名
            String driver = "com.mysql.jdbc.Driver";
            //URL指向要访问的数据库名mydata
            String url = "jdbc:mysql://localhost:3306/test";
            //MySQL配置时的用户名
            String user = "root";
            //MySQL配置时的密码
            String password = "123456";
            //遍历查询结果集
            try {
                //加载驱动程序
                Class<?> aClass = Class.forName(driver);
                //1.getConnection()方法，连接MySQL数据库！！
                con = DriverManager.getConnection(url,user,password);
                if(!con.isClosed()) {
                    //要执行的SQL语句
                    String sql = "select * from users where id=?";
                    //创建statement类对象，ResultSet类，用来存放获取的结果集！！
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, id);
                    ResultSet rs = stmt.executeQuery();
                    System.out.println("-----------------");
                    System.out.println("执行Sql语句: "+stmt.toString());
                    System.out.println("执行结果如下所示:");
                    String uname,name;
                    while (rs.next()) {
                        //获取stuname这列数据
                        uname = rs.getString("uname");
                        //获取stuid这列数据
                        name = rs.getString("name");
                        //输出结果
                        System.out.println(uname + "\t" + name);
                        res.add(new String[]{"id: "+rs.getString("id")
                                + ",uname:"+rs.getString("uname")
                                + ",passwd:"+rs.getString("passwd")
                                + ",name:"+rs.getString("name")}
                        );
                    }
                    rs.close();
                    con.close();
                }
            } catch(ClassNotFoundException e) {
                //数据库驱动类异常处理
                e.printStackTrace();
            } catch(SQLException e) {
                //数据库连接失败异常处理
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
            result.put("data",res);
            result.put("success",true);
            return DataUtil.toJson(result);
        }
        result.put("message", "Error!");
        return DataUtil.toJson(result);
    }
}
