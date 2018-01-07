package org.uestc.theard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.uestc.form.videoViewer;

/**
 * Created by norse on 17-4-13.
 */
public class videoTheard{
    Map<Integer,String> camera=new HashMap<Integer,String>();
    public videoTheard(Map<Integer,String> cameraList){
        camera=cameraList;
    }
    public void video_start(){
        Iterator<Integer> cap=camera.keySet().iterator();
        while (cap.hasNext()){
            final int index = cap.next();
            new Thread(new Runnable() {
                @Override
                public void run() {
            videoViewer video=new videoViewer();
            video.openWindow(index,camera.get(index),100);
                }
            }).start();
        }
    }
}
