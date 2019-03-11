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

