import com.venus.form.*;

/**
 * Created by norse on 17-4-12.
 */
public class Main  {
	public static void main(String[] args) throws Exception {
		//加载opencv246运行库
		System.loadLibrary("opencv_java246");
		//启动主窗口线程
		formThead formThead=new formThead("MainForm");
		formThead.run();
	}
}
//Union test code
/*

import org.opencv.videoio.VideoCapture;
import org.uestc.config.*;
import org.opencv.core.*;
import org.uestc.theard.videoTheard;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        mainConfig Config=new mainConfig();
        Map<String,String> info=Config.getUserConfig();
        Iterator<String> it=info.keySet().iterator();
        while (it.hasNext()){
            String key =it.next();
            //System.out.println(key+":"+info.get(key));
        }
        VideoCapture cae=new VideoCapture();
        //System.out.println(info.get("max_camera"));
        for(int i=0;i<Integer.parseInt(info.get("max_camera"));i+=1){
            Map<Integer,String> cap=new HashMap<Integer,String>();
            if (cae.open(i)){
                cap.put(i,"Hacker"+i);
                videoTheard video=new videoTheard(cap);
                video.video_start();
            }else {
                break;
            }
        }
    }
}

*/