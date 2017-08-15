package org.uestc.form;

/**
 * Created by norse on 17-4-13.
 */

import org.opencv.core.*;
import org.opencv.videoio.VideoCapture;
import org.uestc.cv.faceDetcet;
import org.uestc.cv.logicAlgorithm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by kofee on 2016/3/28.
 */
public class videoViewer extends JComponent {
    private static final long serialVersionUID = 1L;
    private BufferedImage image;
    private logicAlgorithm cv = new logicAlgorithm();

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (image == null) {
            g2d.setPaint(Color.BLACK);
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        } else {
            g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
            g2d.drawString(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()),20,20);
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
        this.image = this.cv.toBufferedImage(image);
        this.repaint();
    }
    public void imshow(byte[] image) {
        try {
            this.image =ImageIO.read(new ByteArrayInputStream(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.repaint();
    }
    public void openWindow(int index,String name,int times){
        this.createWin(name);
        VideoCapture cap=new VideoCapture(index);
        Mat frame=new Mat();
        faceDetcet dec=new faceDetcet();
        while (true){
        cap.read(frame);
        this.imshow(dec.faceDetcet(frame,0 ));
            try {
                Thread.sleep(times);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}