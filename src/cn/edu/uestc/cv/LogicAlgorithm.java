package cn.edu.uestc.cv;

import org.opencv.core.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;

/**
 * Created by V on 17-4-13.
 */
public class LogicAlgorithm {
    /**
     * Convert the matrix to Image and Buffer Image
     *
     * @param matrix
     * @return
     */
    static public BufferedImage martixToBufferedImage(Mat matrix) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        int bufferSize = matrix.channels() * matrix.cols() * matrix.rows();
        byte[] buffer = new byte[bufferSize];
        matrix.get(0, 0, buffer); // get all pixel from martix
        BufferedImage image = new BufferedImage(matrix.cols(), matrix.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        return image;
    }

    /**
     * convert BufferImage to Mat
     *
     * @param image
     * @return
     */
    static public Mat bufferToMartix(BufferedImage image) {
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        if (mat != null) {
            try {
                mat.put(0, 0, data);
            } catch (Exception e) {
                //throw new UnsupportedOperationException("byte is null");
                return null;
            }
        }
        return mat;
    }

    /**
     * convert byte to Mat
     *
     * @param image
     * @return
     * @throws IOException
     */
    static public Mat bufferToMartix(byte[] image) throws IOException {
        BufferedImage bImage = ImageIO.read(new ByteArrayInputStream(image));
        byte[] bytes = ((DataBufferByte) bImage.getRaster().getDataBuffer()).getData();
        Mat data = new Mat(bImage.getHeight(), bImage.getWidth(), CvType.CV_8UC3);
        data.put(0, 0, bytes);
        return data;
    }
}
