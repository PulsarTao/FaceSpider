package cn.edu.uestc.theard;

import cn.edu.uestc.ui.VideoViewer;
import org.opencv.videoio.VideoCapture;

/**
 * Created by norse on 17-4-13.
 */
public class VideoTheard {
    VideoCapture camera = new VideoCapture();
    int ID = 0;

    public VideoTheard(VideoCapture cap, int id) {
        camera = cap;
        ID = id;
    }

    public void video_start() {
        new Thread(new Runnable() {
            public void run() {
                VideoViewer video = new VideoViewer();
                video.openWindow(camera, String.valueOf(ID), 100);
            }
        }).start();
    }
}
