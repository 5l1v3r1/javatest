package com.orleven.javatest.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码工具包
 * @author orleven
 * @date 2017年3月22日
 */
public class CodeUtil {

    public static byte[] base64Decode(String text)
    {
        return Base64.decodeBase64(text);
    }

    public static byte[] base64Decode(byte[] text)
    {
        return Base64.decodeBase64(text);
    }
    
    /**
     * base64 加密
     * @data 2017年5月3日
     * @param text
     * @return
     */
    public static String base64Encode(byte[] text)
    {
        return Base64.encodeBase64String(text);
    }



    public static String base64Encode(String text)
    {
        return Base64.encodeBase64String(text.getBytes());
    }


    public static boolean  isBase64(String text)
    {
        return Base64.isBase64(text);
    }
    /**
     * url 加密
     * @data 2017年5月3日
     * @param text
     * @return
     */
    public static String urlEncode(String text){
    	try {
    		text = URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
    	return text;
    }


    
    /**
     * url 解密
     * @data 2017年5月3日
     * @param text
     * @return
     */
    public static String urlDecode(String text){
    	try {
    		text = URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
    	return text;
    }
    
    public static String getMD5(String str) {
        String md5 = null;
		md5 = DigestUtils.md5Hex(str.getBytes());
        return md5;
    }
    
    /**
     * 计算byte  MD5
     * @param byt
     * @return
     */
    public static String getMD5(byte[] byt) {
        String md5 = null;
		md5 = DigestUtils.md5Hex(byt);
        return md5;
    }

    /**
     * 计算byte  SHA256
     * @param byt
     * @return
     */
    public static String getSHA256(byte[] byt) {
        String sha256 = null;
        sha256 = DigestUtils.sha256Hex(byt);
        return sha256;
    }

    public static String getSHA256(String str) {
        String sha256 = null;
        sha256 = DigestUtils.sha256Hex(str.getBytes());
        return sha256;
    }

    public static String getSHA384(byte[] byt) {
        String sha384 = null;
        sha384 = DigestUtils.sha384Hex(byt);
        return sha384;
    }

    public static String getSHA384(String str) {
        String sha384 = null;
        sha384 = DigestUtils.sha384Hex(str.getBytes());
        return sha384;
    }

    public static String getSHA512(byte[] byt) {
        String sha512 = null;
        sha512 = DigestUtils.sha512Hex(byt);
        return sha512;
    }

    public static String getSHA512(String str) {
        String sha512 = null;
        sha512 = DigestUtils.sha512Hex(str.getBytes());
        return sha512;
    }

    public static String getMD2(String str) {
        String md2 = null;
        md2 = DigestUtils.md2Hex(str.getBytes());
        return md2;
    }

    public static String getMD2(byte[] byt) {
        String md2 = null;
        md2 = DigestUtils.md2Hex(byt);
        return md2;
    }

    /**
     * 计算文件MD5
     * @author orleven
     * @param file
     * @return
     */
//    public static String getMd5ByFile(File file) {
//        String md5 = null;
//        FileInputStream fis = null;
//		try {
//			fis = new FileInputStream(file);
//			md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
//	        IOUtils.closeQuietly(fis); 
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}    
//        return md5;
//    }
    
    /**
     * 计算字符串Sha1
     * @author orleven
     * @param str
     * @return
     */
    public static String getSHA1(String str) {
        return DigestUtils.sha1Hex(str.getBytes());
    }
    
    /**
     * 计算byte  Sha1
     * @param byt
     * @return
     */
    public static String getSHA1(byte[] byt) {
        return DigestUtils.sha1Hex(byt);
    }
    
    /**
     * 计算文件Sha1
     * @author orleven
     * @param file
     * @return
     */
//    public static String getSHA1ByFile(File file) {
//        String md5 = null;
//        FileInputStream fis = null;
//		try {
//			fis = new FileInputStream(file);
//			md5 = DigestUtils.sha1Hex(IOUtils.toByteArray(fis));
//	        IOUtils.closeQuietly(fis); 
//		} catch (FileNotFoundException e) {
//		} catch (IOException e) {
//		}    
//        return md5;
//    }

    /**
     * Native to ascii string. It's same as execut native2ascii.exe.
     * @param str native string
     * @return ascii string
     */
    public static String nativeToAscii(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(charToAscii(chars[i]));
        }
        return sb.toString();
    }

    /**
     * Native character to ascii string.
     * @param c native character
     * @return ascii string
     */
    private static String charToAscii(char c) {
//        if (c > 255) {
            StringBuilder sb = new StringBuilder();
            sb.append("\\u");
            int code = (c >> 8);
            String tmp = Integer.toHexString(code);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            code = (c & 0xFF);
            tmp = Integer.toHexString(code);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
            return sb.toString();
//        } else {
//            return Character.toString(c);
//        }
    }

    /**
     * Ascii to native string. It's same as execut native2ascii.exe -reverse.
     * @param str ascii string
     * @return native string
     */
    public static String asciiToNative(String str) {
        StringBuilder sb = new StringBuilder();
        int begin = 0;
        int index = str.indexOf("\\u");
        while (index != -1) {
            sb.append(str.substring(begin, index));
            sb.append(asciiToChar(str.substring(index, index + 6)));
            begin = index + 6;
            index = str.indexOf("\\u", begin);
        }
        sb.append(str.substring(begin));
        return sb.toString();
    }

    /**
     * Ascii to native character.
     * @param str ascii string
     * @return native character
     */
    private static char asciiToChar(String str) {
        if (str.length() != 6) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must be 6 character.");
        }
        if (!"\\u".equals(str.substring(0, 2))) {
            throw new IllegalArgumentException(
                    "Ascii string of a native character must start with \"\\u\".");
        }
        String tmp = str.substring(2, 4);
        int code = Integer.parseInt(tmp, 16) << 8;
        tmp = str.substring(4, 6);
        code += Integer.parseInt(tmp, 16);
        return (char) code;
    }
}
