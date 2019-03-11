package cn.edu.uestc.ui;

import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import cn.edu.hfut.dmic.webcollector.util.Config;
import cn.edu.uestc.spider.WebCollector;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FaceSpider {
    private boolean isspiderStarted = false;
    private JPanel panel1;
    private JTextField downloadTextField;
    private JTextField configUrlTextField;
    private JTextField ModelTextField;
    private JTextField URLTextField;
    private JTextField SpiderDeepTextField;
    private JTextField TheardNumTextField;
    private JButton StartButton;
    private JTextField RegextextField;
    private JTextArea userAngetTextArea;
    private JTextArea cookiesTextArea;
    private Thread spiderthread=null;
    public FaceSpider() {
        StartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!isspiderStarted){
                    StartButton.setText("停止");
                    startSpider().start();
                }
                else{
                    StartButton.setText("开始");
                    spiderthread.stop();
                }
                isspiderStarted = !isspiderStarted;
            }
        });
    }

    private Thread startSpider(){

        spiderthread=new Thread(new Runnable() {
            public void run() {
                try {
                    String downloaddir=downloadTextField.getText();
                    String configurldir=configUrlTextField.getText();
                    String modelpath=ModelTextField.getText();
                    String target=URLTextField.getText();
                    String regex=RegextextField.getText();
                    String userAgent=userAngetTextArea.getText();
                    String cookies=cookiesTextArea.getText();
                    int theardnum=Integer.parseInt(TheardNumTextField.getText());
                    int spiderdeep=Integer.parseInt(SpiderDeepTextField.getText());

                    WebCollector webCollector = new WebCollector(configurldir, downloaddir,modelpath,userAgent,cookies);
                    webCollector.setRequester(new OkHttpRequester());
                    webCollector.setResumable(false);
                    webCollector.addSeed(target);
                    webCollector.addRegex(regex);
                    webCollector.setThreads(theardnum);
                    Config.MAX_RECEIVE_SIZE = 1000 * 1000 * 1000;
                    webCollector.start(spiderdeep);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return spiderthread;
    }
    public static void start() {
        JFrame frame = new JFrame("FaceSpider");
        frame.setContentPane(new FaceSpider().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}
