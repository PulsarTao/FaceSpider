package com.venus.spider;

import com.venus.spider.SpiderThead;
import com.venus.spider.webCollector;

public class SpiderThead extends Thread {
	public String Thead;
	public String Deep;
	public String UrlMax;
	public String Target;
	public SpiderThead(String name) {
		// TODO 自动生成的构造函数存根
		super(name);
	}
	public void run(){
	int T=Integer.valueOf(Thead);
	int D=Integer.valueOf(Deep);
	int UM=Integer.valueOf(UrlMax);
	//取得用户输入信息
	webCollector VenusSpider = new webCollector("Venus", true);
    //添加网址种子
    VenusSpider.addSeed(Target);
    VenusSpider.addRegex(".*");
	//设置爬虫线程数
    VenusSpider.setThreads(Integer.valueOf(T));
    
    //设置每次迭代中爬取数量的上限
    VenusSpider.setTopN(Integer.valueOf(UM));
    /*设置是否为断点爬取，如果设置为false，任务启动前会清空历史数据。
       如果设置为true，会在已有crawlPath(构造函数的第一个参数)的基础上继
       续爬取。对于耗时较长的任务，很可能需要中途中断爬虫，也有可能遇到
       死机、断电等异常情况，使用断点爬取模式，可以保证爬虫不受这些因素
       的影响，爬虫可以在人为中断、死机、断电等情况出现后，继续以前的任务
       进行爬取。断点爬取默认为false*/
    /*----------------------------------------------------------------------------------
    VenusSpider.setResumable(true);
     -----------------------------------------------------------------------------------*/
    /*开始深度为4的爬取，这里深度和网站的拓扑结构没有任何关系
        可以将深度理解为迭代次数，往往迭代次数越多，爬取的数据越多*/
    try {
		VenusSpider.start(Integer.valueOf(D));
	} catch (Exception e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}
}
}
