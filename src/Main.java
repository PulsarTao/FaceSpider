import com.venus.form.*;

public class Main  {
	public static void main(String[] args) throws Exception {
		//加载opencv246运行库
		System.loadLibrary("opencv_java246");
		//启动主窗口线程
		formThead formThead=new formThead("MainForm");
		formThead.run();
	}
}
