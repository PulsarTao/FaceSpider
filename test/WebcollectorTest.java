import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import cn.edu.hfut.dmic.webcollector.util.ExceptionUtils;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;
import cn.edu.hfut.dmic.webcollector.util.MD5Utils;

import java.io.File;

/**
 * WebCollector抓取图片的例子
 * @author hu
 */
public class WebcollectorTest extends BreadthCrawler {
    File baseDir = new File("images");
    /**
     * 构造一个基于伯克利DB的爬虫
     * 伯克利DB文件夹为crawlPath，crawlPath中维护了历史URL等信息
     * 不同任务不要使用相同的crawlPath
     * 两个使用相同crawlPath的爬虫并行爬取会产生错误
     *
     * @param crawlPath 伯克利DB使用的文件夹
     */
    public WebcollectorTest(String crawlPath) {
        super(crawlPath, true);

        //只有在autoParse和autoDetectImg都为true的情况下
        //爬虫才会自动解析图片链接
        getConf().setAutoDetectImg(true);

        //如果使用默认的Requester，需要像下面这样设置一下网页大小上限
        //否则可能会获得一个不完整的页面
        //下面这行将页面大小上限设置为10M
        //getConf().setMaxReceiveSize(1024 * 1024 * 10);

        //添加种子URL
        addSeed("https://www.baidu.com/");
        //限定爬取范围
        addRegex("https://www.baidu.com/.*");
        addRegex("https://www.baidu.com/.*");
        addRegex("-.*#.*");
        addRegex("-.*\\?.*");
        //设置为断点爬取，否则每次开启爬虫都会重新爬取
//        demoImageCrawler.setResumable(true);
        setThreads(30);

    }

    public void visit(Page page, CrawlDatums next) {
        //根据http头中的Content-Type信息来判断当前资源是网页还是图片
        String contentType = page.contentType();
        //根据Content-Type判断是否为图片
        if(contentType!=null && contentType.startsWith("image")){
            //从Content-Type中获取图片扩展名
            String extensionName=contentType.split("/")[1];
            try {
                byte[] image = page.content();
                //根据图片MD5生成文件名
                String fileName = String.format("%s.%s",MD5Utils.md5(image), extensionName);
                File imageFile = new File(baseDir, fileName);
                FileUtils.write(imageFile, image);
                System.out.println("保存图片 "+page.url()+" 到 "+ imageFile.getAbsolutePath());
            } catch (Exception e) {
                ExceptionUtils.fail(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        WebcollectorTest webcollectorTest = new WebcollectorTest("crawl");
        webcollectorTest.setRequester(new OkHttpRequester());
        //设置为断点爬取，否则每次开启爬虫都会重新爬取
        webcollectorTest.setResumable(false);
        webcollectorTest.start(3);
    }
}