package com.orleven.javatest.controller;

import com.orleven.javatest.bean.MySaxHandler;
import com.orleven.javatest.bean.User;
import com.orleven.javatest.util.DataUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Name: com.orleven.javatest.controller
 * @Author: orleven
 * @Date: 18-1-8
 * @Description:
 */
@Controller
@RequestMapping("/Exec")
public class ExecTestController {
    @ResponseBody
    @RequestMapping(value= "/Exec/ExecInject",method= RequestMethod.POST)
    public String ExecInject(HttpServletRequest request){
        String command = request.getParameter("command");
        Map<String,Object> result = new HashMap<>();
        result.put("success",false);
        if (command!=null&&!command.isEmpty()){
            // 1.获取基于SAX的解析器的实例
            try {
                Runtime rt = Runtime.getRuntime();
                Process proc = rt.exec("cmd.exe /C ping -n 1 "+command);
                //   Process proc = rt.exec(new String [] {"sh", "-c", " ping -c 1"+order});
                int res = proc.waitFor();
                if(res !=0){
                    System.out.println("process error: "+ res);
                }
                InputStream in = (res == 0)? proc.getInputStream() : proc.getErrorStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                StringBuffer  buffer=new StringBuffer();
                String line;
                while((line = reader.readLine())!=null){
                    buffer.append(line+"\n");
                }
                System.out.print(buffer.toString());
                result.put("data", buffer.toString());
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
    @RequestMapping(value= "/Exec/ExecFormat",method= RequestMethod.POST)
        public String ExecFormat(HttpServletRequest request){
        String command = request.getParameter("command");
        Map<String,Object> result = new HashMap<>();
        result.put("success",false);
        if (command!=null&&!command.isEmpty()){
            // 1.获取基于SAX的解析器的实例
            try {
                // 严格校验参数
                if (!Pattern.matches("[A-Za-z@.]+", command)){ ;
                    result.put("message", "Error!");
                    return DataUtil.toJson(result);
                }
                Runtime rt = Runtime.getRuntime();
                Process proc = rt.exec("cmd.exe /C  ping -n 1 "+command);
                //   Process proc = rt.exec(new String [] {"sh", "-c", " ping  -c 1 "+order});
                int res = proc.waitFor();
                if(res !=0){
                    System.out.println("process error: "+ result);
                }
                InputStream in = (res == 0)? proc.getInputStream() : proc.getErrorStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                StringBuffer  buffer=new StringBuffer();
                String line;
                while((line = reader.readLine())!=null){
                    buffer.append(line+"\n");
                }
                System.out.print(buffer.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
            return DataUtil.toJson(result);
        }
        result.put("message", "Error!");
        return DataUtil.toJson(result);
    }

}
