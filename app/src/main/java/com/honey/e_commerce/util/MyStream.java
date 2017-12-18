package com.honey.e_commerce.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 1.类的用途
 * 2.@authorAdministrator
 */

public class MyStream {
    public static String getInputStream(InputStream inputStream){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        byte[] bytes=new byte[2048];
        int len=0;
        try {
            while ((len=inputStream.read(bytes))!=-1){
                bos.write(bytes,0,len);
            }
            String json = bos.toString();
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
