package org.uestc.form;

/**
 * Created by norse on 17-4-13.
 */

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.uestc.cv.logicAlgorithm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by kofee on 2016/3/28.
 */
public class ImageViewer {
    private JLabel imageView;
    private Mat image;
    private String windowName;
    private logicAlgorithm cv=new logicAlgorithm();

    /**
     * 图片显示
     */
    public void imshow(Mat image, String windowName) {
        this.windowName = windowName;
        setSystemLookAndFeel();
        Image loadedImage = cv.toBufferedImage(image);
        System.out.print(image);
        JFrame frame = createJFrame(windowName, image.width(), image.height());
        imageView.setIcon(new ImageIcon(loadedImage));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 用户点击窗口关闭
    }
    public void imshow(BufferedImage image, String windowName) {
        this.windowName = windowName;
        setSystemLookAndFeel();
        JFrame frame = createJFrame(windowName, image.getWidth(), image.getHeight());
        imageView.setIcon(new ImageIcon(image));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 用户点击窗口关闭
    }
    public void imshow(byte[] image, String windowName) throws IOException {
        this.windowName = windowName;
        setSystemLookAndFeel();
        BufferedImage img= ImageIO.read(new ByteArrayInputStream(image));
        BufferedImage loadedImage = cv.toBufferedImage(cv.bufferToMartix(image));
        JFrame frame = createJFrame(windowName, loadedImage.getWidth(), loadedImage.getHeight());
        imageView.setIcon(new ImageIcon(loadedImage));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 用户点击窗口关闭
    }

    private void setSystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    private JFrame createJFrame(String windowName, int width, int height) {
        JFrame frame = new JFrame(windowName);
        imageView = new JLabel();
        final JScrollPane imageScrollPane = new JScrollPane(imageView);
        imageScrollPane.setPreferredSize(new Dimension(width, height));
        frame.add(imageScrollPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }
}