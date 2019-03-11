package cn.edu.uestc.spider;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import cn.edu.hfut.dmic.webcollector.util.Config;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import cn.edu.hfut.dmic.webcollector.util.ExceptionUtils;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;
import cn.edu.hfut.dmic.webcollector.util.MD5Utils;
import cn.edu.uestc.cv.FaceDetcet;
import okhttp3.Request;
import org.opencv.core.Core;
import cn.edu.uestc.cv.LogicAlgorithm;
import cn.edu.uestc.ui.ImageViewer;
import org.opencv.core.Mat;

import javax.imageio.ImageIO;

/**
 * WebCollector抓取图片的例子
 *
 * @author hu
 */
public class WebCollector extends BreadthCrawler {
    File downloadDir;
    String modelPath;
    String downloadPath;
    AtomicInteger imageId;
    ImageViewer imageViewer = new ImageViewer();

    /**
     * @param crawlPath    用于维护URL的文件夹
     * @param downloadPath 用于保存图片的文件夹
     */
    public WebCollector(String crawlPath, String downloadPath, String modelPath, String userAgent, String cookie) {
        super(crawlPath, true);
        setRequester(new FaceRequester(userAgent, cookie));
        getConf().setAutoDetectImg(true);
        this.modelPath = modelPath;
//        addRegex("-.*#.*");
//        addRegex("-.*\\?.*");

        this.downloadPath = downloadPath;
        downloadDir = new File(downloadPath);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }
//        computeImageId();
    }

    public static class FaceRequester extends OkHttpRequester {

        String userAgent = "";
        String cookie = "";

        public FaceRequester(String userAgent, String cookie) {
            super();
            this.userAgent = userAgent;
            this.cookie = cookie;
        }

        @Override
        public Request.Builder createRequestBuilder(CrawlDatum crawlDatum) {
            return super.createRequestBuilder(crawlDatum)
                    .addHeader("User-Agent", userAgent)
                    .addHeader("Cookie", cookie);
        }
    }

    public void visit(Page page, CrawlDatums next) {
        String contentType = page.contentType();
        if (contentType == null){
            return;
        }
        else if(contentType.startsWith("image")) {
            String extensionName = contentType.split("/")[1];
            try {
                byte[] image = page.content();
                String fileName = String.format("%s.%s", MD5Utils.md5(image), extensionName);
                File imageFile = new File(downloadPath, fileName);
                try {
                    byte[] imagebyte = page.content();
                    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagebyte));
                    Mat image_mat = LogicAlgorithm.bufferToMartix(bufferedImage);
                    FaceDetcet dec = new FaceDetcet(modelPath);
                    if (image_mat != null) {
                        boolean haveFace = dec.faceDetcet(image_mat);
                        if (haveFace) {
                            FileUtils.write(imageFile, image);
                            System.out.println("保存图片 " + page.url() + " 到 " + imageFile.getAbsolutePath());
//                            imageViewer.imshow(image_mat, "Collection");
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            } catch (Exception e) {
                ExceptionUtils.fail(e);
            }
        }
        else if (contentType.contains("html")) {
//            Elements imgs = page.select("img[src]");
//            for (Element img : imgs) {
//                String imgSrc = img.attr("abs:src");
//                next.add(imgSrc);
//            }
        }
    }


    public void computeImageId() {

        int maxId = -1;
        for (File imageFile : downloadDir.listFiles()) {
            String fileName = imageFile.getName();
            String idStr = fileName.split("\\.")[0];
            int id = Integer.valueOf(idStr);
            if (id > maxId) {
                maxId = id;
            }
        }
        imageId = new AtomicInteger(maxId);
    }

}
