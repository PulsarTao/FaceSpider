package cn.edu.uestc.config;

import java.io.*;
import java.util.*;

public class MainConfig{
    BufferedInputStream user_in=null;
    BufferedInputStream sys_in=null;
    FileOutputStream user_file=null;
    FileOutputStream sys_file=null;
    private String sysPath="sys.properties";
    private String userPath="config.properties";
    public MainConfig(){
        try {
            try {
                user_in = new BufferedInputStream(new FileInputStream(this.userPath));
                sys_in = new BufferedInputStream(new FileInputStream(sysPath));
            } catch (IOException e) {
                try {
                    user_file = new FileOutputStream(userPath, true);
                    sys_file = new FileOutputStream(sysPath, true);
                    user_in = new BufferedInputStream(new FileInputStream(userPath));
                    sys_in = new BufferedInputStream(new FileInputStream(sysPath));
                    user_file.close();
                    sys_file.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                System.out.println("No Config file , System will auto Created");
            }
        }catch (Exception e){
            System.out.println("I/O Error");
        }
    }
    private void setDefaultConfig(Map<String,String> info) throws IOException {
        Properties pps = new Properties();
        Iterator<String> it=info.keySet().iterator();
        while (it.hasNext()){
            String key=it.next();
            pps.setProperty(key,info.get(key));
        }
        pps.store(user_file, "The New properties user_file");
    }
    public  Map<String,String> getUserConfig(){
        Properties pps = new Properties();
        Map<String,String> info=new HashMap<String,String>();
        try {
            pps.load(user_in);
            Iterator<String> it=pps.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                info.put(key,pps.getProperty(key));
            }
            return info;
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return info;
    }
}
