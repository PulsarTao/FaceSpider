package cn.edu.uestc.ui;

/**
 * Created by norse on 17-4-13.
 */

import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import cn.edu.uestc.cv.FaceDetcet;
import cn.edu.uestc.cv.LogicAlgorithm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kofee on 2016/3/28.
 */
public class VideoViewer extends JComponent {
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private LogicAlgorithm cv = new LogicAlgorithm();

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (image == null) {
            g2d.setPaint(Color.BLACK);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        } else {
            g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
            g2d.drawString(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()), 20, 20);
        }
    }

    public void createWin(String title) {
        JDialog ui = new JDialog();
        ui.setTitle(title);
        ui.getContentPane().setLayout(new BorderLayout());
        ui.getContentPane().add(this, BorderLayout.CENTER);
        ui.setSize(new Dimension(640, 480));
        ui.setVisible(true);
    }

    public void createWin(String title, Dimension size) {
        JDialog ui = new JDialog();
        ui.setTitle(title);
        ui.getContentPane().setLayout(new BorderLayout());
        ui.getContentPane().add(this, BorderLayout.CENTER);
        ui.setSize(size);
        ui.setVisible(true);
    }

    public void imshow(BufferedImage image) {
        this.image = image;
        this.repaint();
    }

    public void imshow(Mat image) {
        this.image = LogicAlgorithm.martixToBufferedImage(image);
        this.repaint();
    }

    public void imshow(byte[] image) {
        try {
            this.image = ImageIO.read(new ByteArrayInputStream(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.repaint();
    }

    public void openWindow(VideoCapture cap,String name, int times) {
        this.createWin(name);
        Mat frame = new Mat();
        FaceDetcet dec = new FaceDetcet();
        if(!cap.isOpened()){
            System.out.print("Camera Error");
            return;
        }
        while (true) {
            cap.read(frame);
//            this.imshow(dec.faceDetcet(frame, 0));
            this.imshow(frame);
            try {
                Thread.sleep(times);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}