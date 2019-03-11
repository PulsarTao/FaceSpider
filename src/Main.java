//import com.venus.form.*;

import cn.edu.uestc.ui.FaceSpider;
import org.opencv.core.Core;
import java.awt.*;

/**
 * Created by norse on 17-4-12.
 */
public class Main  {
    static{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
 }
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FaceSpider faceSpider=new FaceSpider();
                    faceSpider.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
}
//Union test code

//import cn.edu.uestc.config.MainConfig;
//import org.opencv.videoio.VideoCapture;
//import org.opencv.core.*;
//import cn.edu.uestc.theard.VideoTheard;
//import cn.edu.uestc.ui.VideoViewer;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//public class Main {
//    static{
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
// }
//
//    public static void main(String[] args){
//
////        System.loadLibrary("opencv_java341");
//
//        System.load("E:\\opencv\\build\\java\\x64\\opencv_java341.dll");
//        MainConfig Config=new MainConfig();
//        Map<String,String> info=Config.getUserConfig();
//        Iterator<String> it=info.keySet().iterator();
//        while (it.hasNext()){
//            String key =it.next();
//            System.out.println(key+":"+info.get(key));
//        }
//        Mat mat=new Mat();
//        System.out.println(info.get("max_camera"));
//        for(int i=0;i<Integer.parseInt(info.get("max_camera"));i+=1){
//            Map<Integer,String> cap=new HashMap<Integer,String>();
//            VideoCapture cae=new VideoCapture();
//            if (cae.open(i)){
//                cap.put(i,"Hacker"+i);
//                VideoTheard video=new VideoTheard(cae,i);
//                video.video_start();
//            }else {
//                break;
//            }
//        }
//    }
//}

