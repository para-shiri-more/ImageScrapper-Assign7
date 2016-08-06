/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shreeka.imagescrapper;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Decode
 */
public class ImageScrapper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        Scanner input= new Scanner(System.in);
        System.out.print("Enter URL: ");
        String link=input.next();
        
        URL url =new URL(link);
       URLConnection conn=url.openConnection();
       StringBuilder content=new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    String line="";
                    content = new StringBuilder();
                    while((line=reader.readLine())!=null)
                    {
                        content.append(line).append("\n");
                    }
                reader.close();
                }
                
       String regex="<img class=\"border\" src=\"(.*?)\"\\s width=\"(.*?) alt=(.*?)/></a><br />";
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher=pattern.matcher(content.toString());
       
        while(matcher.find())
        {
           
            String imgLink= matcher.group(1);
            URL imgUrl=new URL(imgLink);
            URLConnection conn1=imgUrl.openConnection();
            InputStream is = conn1.getInputStream();
         
           String[] tokens=imgLink.split("/");
           String path="C:\\Users\\User\\Desktop\\javaExamples\\red velvet\\";
           FileOutputStream os = new FileOutputStream(path+tokens[tokens.length-1]);
            byte[] data=new byte[1024];
                int i=0;
                while((i=is.read(data))!=-1)
                {
                    os.write(data, 0, i);
                }
                os.close();
                is.close();
        }
       
        
        
    }
    
}
