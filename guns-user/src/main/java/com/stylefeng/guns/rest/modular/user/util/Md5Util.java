package com.stylefeng.guns.rest.modular.user.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author YangShuo
 * @date 2019-07-18 9:12
 */
public class Md5Util {

    //获取md5字符串
    public static String getMD5(String pwd){
        //盐值
        String salt = "cskaoyan";

        pwd = pwd + salt;

        String md5hashvalue= "";

        try {
            //一个长度可扩展的String
            StringBuffer stringBuffer = new StringBuffer();

            //1、传一个参数：algorithm 算法
            MessageDigest md5 = MessageDigest.getInstance("MD5");

            //2、****MD5值生成算法，传入密码，返回一个MD5字节数组(16字节长度)**** 16*8=128
            byte[] digest = md5.digest(pwd.getBytes());//要先将输入的密码字符串变成字节数组

            //3、对结果进行处理： 将MD5值（16个字节的字节数组）还原成字符串
            for(byte b:digest){

                //3.1 将byte变为int （一个字节对应一个两位的16进制数）
                int i = b & 0x000000FF;
                //3.2 int转为16进制的字符串（将一个两位数的16进制数 转为 一个字节）

                String s = Integer.toHexString(i);//要求传一个int值

                //3.3 将每个字节对应的字符串一次放入StringBuffer 中
                if(s.length()==1){
                    stringBuffer.append(0);
                }
                stringBuffer.append(s);
            }
            md5hashvalue = stringBuffer.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //返回一个MD5 hash值
        return md5hashvalue;
    }
}
