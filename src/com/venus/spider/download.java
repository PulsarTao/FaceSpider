package com.venus.spider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;  
 
/*这个类用来处理抓取到的图片地址的临时文件下载*/
public class download {
	//声明临时文件存储路径
    private String Tempath="Temp/temp";
    public String DownloadS(String url) {  
    	//初始化图片名为空
    	String imageName=null;
        try {   
                imageName = url.substring(url.lastIndexOf("/") + 1, url.length());  
                URL uri = new URL(url);  
                InputStream in = uri.openStream();
                File sf=new File("Temp");  
                //判断文件夹是否存在如果不在,新建一个
                if(!sf.exists()){  
                    sf.mkdirs();  
                }
                //用文件流的方式输出到临时文件
                FileOutputStream fo = new FileOutputStream(new File(Tempath));  
                //设置每次读取的字节大小
                byte[] buf = new byte[1024];  
                int length = 0;  
                //读取远程文件后写入到本地
                while ((length = in.read(buf, 0, buf.length)) != -1) {  
                    fo.write(buf, 0, length);  
                }  
             // 完毕，关闭所有链接
                in.close();  
                fo.close();  
                return imageName;
        } catch (Exception e) {  
           System.out.println(e);
        } 
        return imageName;
    }  
}
