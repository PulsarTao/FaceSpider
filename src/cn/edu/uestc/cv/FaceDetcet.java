package cn.edu.uestc.cv;

import cn.edu.uestc.theard.SaveTheard;
import cn.edu.uestc.ui.ImageViewer;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.core.*;

import java.awt.image.BufferedImage;

/**
 * Created by norse on 17-4-13.
 * <p>
 * to
 */
public class FaceDetcet {
    private CascadeClassifier face = new CascadeClassifier("data/haarcascades/haarcascade_frontalface_alt.xml");
    private CascadeClassifier eye = new CascadeClassifier("data/haarcascades/haarcascade_eye.xml");
    public FaceDetcet(){

    }
    public FaceDetcet(String faceModelPath){
        this.face=new CascadeClassifier(faceModelPath);
    }
    public boolean detcet(String imagePath) {
        Mat image = Imgcodecs.imread(imagePath);
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.imshow(image, null);
        return true;
    }

    public boolean detcet(Mat image) {
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.imshow(image, null);
        return true;
    }

    public boolean detcet(BufferedImage image) {
        ImageViewer imageViewer = new ImageViewer();
        LogicAlgorithm decoder = new LogicAlgorithm();
        imageViewer.imshow(image, null);
        return true;
    }

    /**
     * this function to paint the  dect arear to the image martix
     *
     * @param frame
     * @return
     */
    public Mat faceDetcet(Mat frame, int paint) {
        if (paint == 1)
            return painDetcetTarget(frame, this.face);
        if (paint != 1) {
            decetSave(frame, null);
        }
        return frame;
    }

    private Mat eyeDetcet(Mat frame) {
        return painDetcetTarget(frame, this.eye);
    }

    /**
     * decet a face and save it by a new Theard
     *
     * @param frame
     */
    public void decetSave(Mat frame, String savePath) {
        MatOfRect decet = new MatOfRect();
        Mat face_image = null;
        Mat image = new Mat();
        Imgproc.cvtColor(frame, image, Imgproc.COLOR_RGB2GRAY);
        this.face.detectMultiScale(frame, decet);
        if (decet.dataAddr() != 0) {
            for (Rect rect : decet.toArray()) {
                face_image = new Mat(frame, rect);
            }
            decet = new MatOfRect();
            this.eye.detectMultiScale(face_image, decet);

            if (decet.dataAddr() != 0) {
                //TODO: set a theard to decet the image characteristic

                new SaveTheard(face_image, savePath).start();
                //Mat martix=new Mat(image.getHeight(),image.getWidth(),CvType.CV_8UC1);//Imgcodecs.imdecode(new MatOfByte(buffer),Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
            }
        }
    }

    /**
     * pain the detcet area by a squre
     *
     * @param frame
     * @param type
     * @return
     */
    public Mat painDetcetTarget(Mat frame, CascadeClassifier type) {
        Mat image = new Mat();
        Imgproc.cvtColor(frame, image, Imgproc.COLOR_RGB2GRAY);
        Imgproc.equalizeHist(image, image);
        MatOfRect decet = new MatOfRect();
        type.detectMultiScale(image, decet);
        if (!decet.empty()) {
            for (Rect rect : decet.toArray()) {
                Imgproc.rectangle(frame,
                        new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 0));

            }
        }
        return frame;
    }

    /**
     * get the characteristic
     *
     * @param image
     * @return
     */
    public Mat getImageCharacteristic(Mat image) {
        return new Mat();
    }
}
