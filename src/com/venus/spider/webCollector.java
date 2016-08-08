package com.venus.spider;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import java.io.File;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.venus.opencv.*;
//这个类用来处理爬虫的爬取方式和对比识别
public class webCollector extends BreadthCrawler {

	public webCollector(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
	}

	@Override
	public void visit(Page page, CrawlDatums venus) {
		String imgsrc;
		page.getUrl();
		Document doc = page.doc();
		Elements img = doc.getElementsByTag("img");
		download VenusImg = new download();
		DetectFace check = new DetectFace();
		//判断存储路径是否存在若不存在就新建一个
		File sf=new File("Save");
		if(!sf.exists()){  
            sf.mkdirs();  
        }
		for (Element x : img) {
			//每一次赋值用属性选择器选出DOM的SRC属性
			imgsrc = (String) x.attr("src");
			// 将爬取出来的图片地址丢给download下载临时文件
			String imgname = VenusImg.DownloadS(imgsrc);
			//若文件名不为空则判断是否有人脸有则opencv会主动保存文件
			if (imgname != null) {
				check.Start("Save", imgname);
			}else {
				System.out.println("图片名缺失");
			}
		}
	}
}
