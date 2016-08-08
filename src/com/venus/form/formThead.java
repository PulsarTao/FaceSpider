package com.venus.form;

import java.awt.EventQueue;

public class formThead extends Thread {
	
	public formThead(String name) {
		// TODO 自动生成的构造函数存根
		super(name);
	}
	//声明主窗口线程
	public static void mainForm(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainform window = new Mainform();
					window.frmVenusspider.setVisible(true);;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void run(){
		mainForm();
	}

}
