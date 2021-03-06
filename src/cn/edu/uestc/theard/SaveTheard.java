package cn.edu.uestc.theard;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import cn.edu.uestc.cv.LogicAlgorithm;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by norse on 17-4-13.
 */
public class SaveTheard extends Thread {
    Mat out_image=null;
    String savePath=null;
    Size none=new Size(0,0);
    public SaveTheard(Mat out_image, String savePath){
        this.out_image=out_image;
        this.savePath=savePath;
    }
    @Override
    public void run(){
        if(out_image!=null&&out_image.size()!=none){
            savePath=savePath!=null?savePath:("./image/"+UUID.randomUUID().toString());
            BufferedImage save=(LogicAlgorithm.martixToBufferedImage(out_image));
            byte[]data=((DataBufferByte)save.getData().getDataBuffer()).getData();
            if(data.length>1024)
                try {
                    ImageIO.write(save, "jpg", new File(savePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
