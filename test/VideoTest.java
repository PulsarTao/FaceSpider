import cn.edu.uestc.config.MainConfig;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.*;
import cn.edu.uestc.theard.VideoTheard;
import cn.edu.uestc.ui.VideoViewer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VideoTest {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    public static void main(String[] args) {
        MainConfig Config = new MainConfig();
        Map<String, String> info = Config.getUserConfig();
        Iterator<String> it = info.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key + ":" + info.get(key));
        }
        Mat mat = new Mat();
        System.out.println(info.get("max_camera"));
        for (int i = 0; i < Integer.parseInt(info.get("max_camera")); i += 1) {
            Map<Integer, String> cap = new HashMap<Integer, String>();
            VideoCapture cae = new VideoCapture();
            if (cae.open(i)) {
                cap.put(i, "Hacker" + i);
                VideoTheard video = new VideoTheard(cae, i);
                video.video_start();
            } else {
                break;
            }
        }
    }
}
