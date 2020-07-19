package com.itlike.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5Util {

    public static String code(String str){
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            byte[] byteDigest = md5.digest();
            int i;
            StringBuffer stringBuffer = new StringBuffer("");
            for (int offset=0;offset<byteDigest.length;offset++){
                i=byteDigest[offset];
                if(i<0)
                    i+=256;
                if(i<16)
                    stringBuffer.append("0");
                stringBuffer.append(Integer.toHexString(i));
            }
            //32位加密
            return stringBuffer.toString();
            //16位加密
//            return stringBuffer.toString().substring(8,24);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

    public static String codeShiro(String password,String salt,Integer num){
        Md5Hash hash=new Md5Hash(password,salt,num);
        return hash.toString();
    }

    public static void main(String[] args) {
        System.out.println(code("123"));
        System.out.println(codeShiro("123","月",2));
    }
}
