package com.venus.form;
//构建主窗体类和窗体动作
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.venus.spider.SpiderThead;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mainform {

	public JFrame frmVenusspider;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the application.
	 */
	public Mainform() {
		initialize();
	}
	private void initialize() {
		frmVenusspider = new JFrame();
		frmVenusspider.setTitle("VenusSpider");
		frmVenusspider.setBackground(SystemColor.textHighlight);
		frmVenusspider.setResizable(false);
		frmVenusspider.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
			}
		});
		frmVenusspider.setBounds(100, 100, 450, 300);
		frmVenusspider.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSetUrl = new JLabel("Set Url");
		JLabel lblNewLabel = new JLabel("State:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		
		JButton btnStartSpider = new JButton("Start Spider");
		btnStartSpider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (    textField_1.getText().isEmpty()==false && 
						textField_2.getText().isEmpty()==false &&
						textField_3.getText().isEmpty()==false &&
						textField_4.getText().isEmpty()==false){
					//正则表达式表达式判断是否为正确的url
					String Target=textField_1.getText();
					String regex="^http://[\\w-\\.]+(?:/|(?:/[\\w\\.\\-]+)*(?:/[\\w\\.\\-]+\\.do))?$";
					//匹配正则表达式
					if(Target.matches(regex)==true){
						//判断输入框内的内容是否都在取值范围内
						if(     Integer.valueOf(textField_2.getText())>=1 &&
								Integer.valueOf(textField_2.getText())<=200 &&
								Integer.valueOf(textField_3.getText())>=1 &&
								Integer.valueOf(textField_3.getText())<=5 &&
								Integer.valueOf(textField_4.getText())>=1 &&
								Integer.valueOf(textField_4.getText())<=99999){
						lblNewLabel.setText("State:Run in  "+Target);
						SpiderThead spider= new SpiderThead("Spider"+Target);
						//设置爬虫信息
						spider.Target=Target;
						spider.Deep=textField_3.getText();
						spider.Thead=textField_2.getText();
						spider.UrlMax=textField_4.getText();
						//打开爬虫线程
						spider.start();
						/*try {
							//限制线程运行速度
							Thread.sleep(1);
						} catch (InterruptedException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}*/
						}else {
							lblNewLabel.setText("Error");
						}
					}else{
						lblNewLabel.setText("URL Format Is Error");
					}
				}else{
					lblNewLabel.setText("State:Error!!! Url is Empty");
				}
			}
		});
		JLabel lblSetThead = new JLabel("Set thead");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel label = new JLabel("0-200");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblDeep = new JLabel("Deep");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblUrlMax = new JLabel("Url Max");
		
		JLabel label_1 = new JLabel("1-5");
		
		JLabel label_2 = new JLabel("1-99999");
		
		JLabel lblHttpxxxcom = new JLabel("http://xxx.com/");
		GroupLayout groupLayout = new GroupLayout(frmVenusspider.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(40, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblDeep)
								.addComponent(lblSetThead)
								.addComponent(lblSetUrl)
								.addComponent(lblUrlMax))
							.addGap(27)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnStartSpider, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(textField_3, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_2, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_4, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_2)
								.addComponent(lblHttpxxxcom)
								.addComponent(label)
								.addComponent(label_1))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSetUrl)
						.addComponent(lblHttpxxxcom)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSetThead)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDeep)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUrlMax)
						.addComponent(label_2))
					.addGap(41)
					.addComponent(btnStartSpider)
					.addGap(18)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(16))
		);
		frmVenusspider.getContentPane().setLayout(groupLayout);
		
		
	}
}
