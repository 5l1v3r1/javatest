package com.orleven.javatest.util;

import com.google.gson.Gson;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;

public class DataUtil {

	/**
	 * 获取时间
	 * @data 2017年5月13日
	 * @return
	 */
	public static String getTime() {
		return DateFormat.getDateTimeInstance().format(new Date());
	}

	/**
	 * 获取时间戳
	 * @data 2017年5月13日
	 * @return
	 */
	public static String getTimeStamp() {
   		return String.valueOf(new Date().getTime()).substring(0,10);
	}

	/**
	 * Port  Discern
	 * @param port
	 * @param info
	 * @return
	 */
	public static String portDiscern (int port,String info){
		info = info.toLowerCase();
		if (info.indexOf("https")>=0){
			return "HTTPS";
		}else if(info.indexOf("http")>=0||info.indexOf("html")>=0){
			return "HTTP";
		}else if(info.indexOf("vmware")>=0){
			return "VMware";
		}

		switch( port ){
			case 21:
				return  "FTP";
			case 22:
				return "SSH";
			case 23:
				return "TELNET";
			case 25:
				return  "SMTP";
			case 53:
				return "DNS";
			case 80:
				return "HTTP";
			case 110:
				return  "RPC";
			case 135:
				return  "RPC";
			case 139:
				return  "NETBIOS";
			case 389:
				return  "LDAP";
			case 443:
				return  "HTTPS";
			case 445:
				return  "SMB";
			case 512:
				return  "REXEC";
			case 513:
				return  "REXEC";
			case 514:
				return  "REXEC";
			case 993:
				return "IMAP";
			case 873:
				return "Rsync";
			case 1433:
				return "SQLServer";
			case 1521:
				return  "Oracle";
			case 3306:
				return  "Mysql";
			case 3389:
				return  "RDP";
			case 5432:
				return  "PostgreSQL";
			case 5900:
				return  "VNC";
			case 6379:
				return  "Redis";
			case 7001:
				return  "HTTP";
			case 9200:
				return  "Elasticsearch";

			case 27017:
				return  "MongoDB";
		}
		return "Unknown";
	}

	/**
	 * 去重
	 * @data 2017年5月13日
	 * @param arrayList
	 * @return
	 */
    public static String[] removeDuplicate(String[] arrayList){
        Set<String> set = new HashSet<>();
        for(int i=0;i<arrayList.length;i++){
        	if(arrayList[i]!=null&&!arrayList[i].equals("")){
        		set.add(arrayList[i]);
        	}
        }
        return (String[]) set.toArray(new String[set.size()]);
    }

    /**
     * 操作系统斜杆处理
     * @data 2017年5月13日
     * @param text
     * @return
     */
    public static String slashDeal(String text){
    	if(File.separator.equals("\\")){
    		text = text.replaceAll("/", "\\\\");
    	}else{
    		text = text.replaceAll("\\\\", "/");
    	}
    	return text;
    }

    /**
     * 换行处理
     * @data 2017年5月13日
     * @param text
     * @return
     */
    public static String removeLinefeed(String text){
    	text = text.replaceAll("\r", "").replaceAll("\n", "");
    	return text;
    }



    /**
     * 打印漂亮二进制代码，填充左零
     * @data 2017年5月17日
     * @param binary
     */
    private static void printPrettyBinary(String binary) {
        String s1 = String.format("%32s", binary).replace(' ', '0');
        System.out.format("%8s %8s %8s %8s %n", s1.substring(0, 8), s1
                .substring(8, 16), s1.substring(16, 24), s1.substring(24, 32));
    }


    public static String strXorTest(String str1,String str2) {
    	byte[] bytes = str1.getBytes();
    	for(int i=0 ;i<bytes.length;i++){
    		bytes[i] =(byte) ((int)bytes[i]^(int)str2.charAt(0));
    	}
    	return new String(bytes);
    }

    /**
     * Map trans to Json
     * @param params
     * @return String
     */
    public static String toJson(Map params){
        Gson gson=new Gson();
        return gson.toJson(params);
    }


    public static String toJson(Object src){
        Gson gson=new Gson();
        return gson.toJson(src);
    }

    public static String bytesToHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }

	public static byte[] hexStringToBytes(String hexString) {
		if ((hexString != null) && (!hexString.equals("")))
		{
			hexString = hexString.toUpperCase();
			int length = hexString.length() / 2;
			char[] hexChars = hexString.toCharArray();
			byte[] d = new byte[length];
			for (int i = 0; i < length; i++)
			{
				int pos = i * 2;
				d[i] = ((byte)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)])));
			}
			return d;
		}
		return null;
	}

	private static byte charToByte(char c)
	{
		return (byte)"0123456789ABCDEF".indexOf(c);
	}

//
//
//    public static Object fromJsonToObject(String jsonStr){
//        Gson gson=new Gson();
//        Type type=new TypeToken<Object>(){}.getType();
//        return gson.fromJson(jsonStr,type);
//    }
//
//    /**
//     * Json string to HashMap<String,Object>
//     * @param jsonStr
//     * @return HashMap<String,Object>
//     */
//    public static HashMap<String,Object> fromJson(String jsonStr){
//    	Gson gson=new Gson();
//    	Type type=new TypeToken<HashMap<String,Object>>(){}.getType();
//
//        return gson.fromJson(jsonStr,type);
//    }
}
