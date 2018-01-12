package com.orleven.javatest.controller;

import com.orleven.javatest.bean.MySaxHandler;
import com.orleven.javatest.bean.User;
import com.orleven.javatest.util.DataUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
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
@RequestMapping("/File")
public class FileTestController {

    @RequestMapping("/Index")
    public String FileFileUpload(Map<String,Object> map){
        return "Fileup";
    }

    @ResponseBody
    @RequestMapping(value= "/File/FileUpVul",method= RequestMethod.POST)
    public String FileFileUpVul(HttpServletRequest request,@RequestParam("file") MultipartFile file){
        String name = request.getParameter("name");
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("config/"+name)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into config/" + name;
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }


    @ResponseBody
    @RequestMapping(value= "/File/FileUpFormat",method= RequestMethod.POST)
    public String FileFileUpFormat(HttpServletRequest request,@RequestParam("file") MultipartFile file){
        String name = DataUtil.getTimeStamp()+".jpg";
        if (!file.isEmpty()) {
            try {
                // 判断大小等等,只要做好配置，并不需要检查文件头。
                if(file.getSize()<1024*1024*10){
                    byte[] bytes = file.getBytes();
                    BufferedOutputStream stream =
                            new BufferedOutputStream(new FileOutputStream(new File("config/"+name)));
                    stream.write(bytes);
                    stream.close();
                    return "You successfully uploaded " + name + " into config/" + name;
                }

            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        }
        return "You failed to upload " + name + " because the file was empty.";

    }



    @ResponseBody
    @RequestMapping(value= "/File/FileDownVul",method= RequestMethod.POST)
    public void FileFileDownVul(HttpServletRequest request,HttpServletResponse response){
        String path = request.getParameter("path");
        Map<String,Object> result = new HashMap<>();
        result.put("success",false);
        if (path!=null&&!path.isEmpty()){
            // 1.获取基于SAX的解析器的实例
            try {
                path = "config/"+path;
                File file = new File(path);
                System.out.println(path);
                response.setHeader("Content-Disposition", "attachment;filename=\""
                        + new String(path.getBytes(), "ISO8859-1") + "\"");
                response.setContentLength((int) file.length());
                byte[] buffer = new byte[4096];// 缓冲区
                BufferedOutputStream output = null;
                BufferedInputStream input = null;
                try {
                    output = new BufferedOutputStream(response.getOutputStream());
                    input = new BufferedInputStream(new FileInputStream(file));
                    int n = -1;
                    //遍历，开始下载
                    while ((n = input.read(buffer, 0, 4096)) > -1) {
                        output.write(buffer, 0, n);
                    }
                    output.flush();   //不可少
                    response.flushBuffer();//不可少
                } catch (Exception e) {
                    //异常自己捕捉
                } finally {
                    //关闭流，不可少
                    if (input != null)
                        input.close();
                    if (output != null)
                        output.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    @ResponseBody
    @RequestMapping(value= "/File/FileDownFormat",method= RequestMethod.POST)
    public void FileFileDownFormat(HttpServletRequest request,HttpServletResponse response){
        String path = request.getParameter("path");
        Map<String,Object> result = new HashMap<>();
        result.put("success",false);
        if (path!=null&&!path.isEmpty()){
            try {
                if (!Pattern.matches("^([a-z0-9]{32})$", path)){ ;
                    return ;
                }
                path = "config/"+path+".docx";
                File file = new File(path);
                System.out.println(path);
                response.setHeader("Content-Disposition", "attachment;filename=\""
                        + new String(path.getBytes(), "ISO8859-1") + "\"");
                response.setContentLength((int) file.length());
                byte[] buffer = new byte[4096];// 缓冲区
                BufferedOutputStream output = null;
                BufferedInputStream input = null;
                try {
                    output = new BufferedOutputStream(response.getOutputStream());
                    input = new BufferedInputStream(new FileInputStream(file));
                    int n = -1;
                    //遍历，开始下载
                    while ((n = input.read(buffer, 0, 4096)) > -1) {
                        output.write(buffer, 0, n);
                    }
                    output.flush();   //不可少
                    response.flushBuffer();//不可少
                } catch (Exception e) {
                    //异常自己捕捉
                } finally {
                    //关闭流，不可少
                    if (input != null)
                        input.close();
                    if (output != null)
                        output.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
