package cn.edu.uestc.datebase;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class MainConfig{
    BufferedInputStream in=null;
    FileOutputStream file=null;

    /**
     * inint an mainConfig class toload the config file
     */
    public MainConfig(){
        try {
            try {
                in = new BufferedInputStream(new FileInputStream("./config/config.properties"));
            } catch (IOException e) {
                try {
                    file = new FileOutputStream("./config/config.properties", true);
                    in = new BufferedInputStream(new FileInputStream("./config/config.properties"));
                    file.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }catch (Exception e){

        }
    }

    /**
     *
     * set a new rules for spider config
     *
     * @param info an config Map
     * @throws IOException
     */
    private void setDefaultConfig(Map<String,String> info) throws IOException {
        Properties pps = new Properties();
        Iterator<String> it=info.keySet().iterator();
        while (it.hasNext()){
            String key=it.next();
            pps.setProperty(key,info.get(key));
        }
        pps.store(file, "New Update");
    }
    public Map<String,String> getConfig(){
        Properties pps = new Properties();
        Map<String,String> info=new HashMap<String,String>();
        try {
            pps.load(in);
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
