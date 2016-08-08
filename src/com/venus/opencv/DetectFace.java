package com.venus.opencv;

import java.io.File;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;
//这个类用opencv对比训练库来进行识别
public class DetectFace {
  public void Start(String path,String imgname) {
//------------------------------------------------------------------------------
/*
*打开文件得到文件绝对路径
*/
	//加载人脸识别训练库
    File xml=new File("loader/etc/lbpcascades/lbpcascade_frontalface.xml");
    //取得临时文件
    File img=new File("Temp/temp");
    String xmlpath=xml.getAbsolutePath();
    String imgpath=img.getAbsolutePath();
//------------------------------------------------------------------------------
    
    
    
//------------------------------------------------------------------------------
    //取得训练对比数据
    CascadeClassifier faceDetector = new CascadeClassifier(xmlpath);   
    //读取opencv图片识别对象
    Mat image = Highgui.imread(imgpath);
    //System.out.println(image);
    //加载对比类
    MatOfRect faceDetections = new MatOfRect();
    //开始比对数据
    faceDetector.detectMultiScale(image, faceDetections);
//-------------------------------------------------------------------------------
    //比对完成后会将人脸数据转移到地址中，若地址为空，则说明没有人脸
    //这儿做对比判断若有人脸则保存
    if (faceDetections.dataAddr()!=0){
    	System.out.println("发现人脸");
    	//声明文件存储路径在Save文件夹下
    	String Simg="Save/"+imgname;
    	System.out.println(Simg);
        Highgui.imwrite(Simg, image);
        //删除储存的临时文件
        img.delete();
    }else {
		System.out.println("未发现人脸");
		img.delete();
	}
  }
}
