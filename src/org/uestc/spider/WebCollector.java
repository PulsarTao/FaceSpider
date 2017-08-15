package org.uestc.spider;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.util.Config;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.video.Video;
import org.uestc.cv.faceDetcet;
import org.uestc.cv.logicAlgorithm;
import org.uestc.form.ImageViewer;
import org.uestc.form.videoViewer;

import javax.imageio.ImageIO;

/**
 * WebCollector抓取图片的例子
 *
 * @author hu
 */
public class WebCollector extends BreadthCrawler {
    logicAlgorithm logic = new logicAlgorithm();
    File downloadDir;
    AtomicInteger imageId;
    ImageViewer imshow = new ImageViewer();

    /**
     * @param crawlPath    用于维护URL的文件夹
     * @param downloadPath 用于保存图片的文件夹
     */
    public WebCollector(String crawlPath, String downloadPath) {
        super(crawlPath, true);
        downloadDir = new File(downloadPath);
        if (!downloadDir.exists()) {
            downloadDir.mkdirs();
        }
        computeImageId();
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        //acrroding the http head Content-Type info to judge the sourse
        String contentType = page.getResponse().getContentType();
        if (contentType == null) {
            return;
        } else if (contentType.contains("html")) {
            Elements imgs = page.select("img[src]");
            for (Element img : imgs) {
                String imgSrc = img.attr("abs:src");
                next.add(imgSrc);
            }

        } else if (contentType.startsWith("image")) {
            //String extensionName = contentType.split("/")[1];
            //String imageFileName = imageId.incrementAndGet() + "." + extensionName;
            //File imageFile = new File(downloadDir, imageFileName);
            try {
                //FileUtils.writeFile(imageFile, page.getContent());
                byte[] imagebyte = page.getContent();
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imagebyte));
                Mat image = logic.bufferToMartix(img);
                faceDetcet dec = new faceDetcet();
                if (image != null)
                    dec.faceDetcet(image, 0);
                //imshow.imshow(,"Collection");
                System.out.println(page.getUrl());
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        WebCollector demoImageCrawler = new WebCollector("./config/url", "download");
        demoImageCrawler.addSeed("https://u15.info/");
        demoImageCrawler.addRegex("https://u15.info/.*");
        demoImageCrawler.setResumable(false);
        demoImageCrawler.setThreads(300);
        Config.MAX_RECEIVE_SIZE = 1000 * 1000 * 1000;
        demoImageCrawler.start(10);
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
