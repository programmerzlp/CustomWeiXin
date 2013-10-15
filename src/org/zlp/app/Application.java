package org.zlp.app;

import javax.swing.SwingUtilities;

import org.zlp.swing.MainFrame;

/**
 * Created with eclipse
 * 
 * @Description: 应用程序入口
 * @author: programmer.zlp@qq.com
 * @Date: 2013-7-11
 * @Time: 上午10:27:39
 * 
 */
public class Application {

	public static String ACCESS_TOKEN = "";

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainFrame.INSTANCE.createMainFrame();
			}
		});
	}

}