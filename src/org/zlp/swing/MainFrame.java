package org.zlp.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.zlp.app.Application;
import org.zlp.domain.AccessToken;
import org.zlp.util.JsonDeal;
import org.zlp.util.PropertiesRead;
import org.zlp.util.ThreadGenerate;
import org.zlp.util.WebDataReceive;

/**
 * Created with eclipse
 * 
 * @Description: 主框架
 * @author: programmer.zlp@qq.com
 * @Date: 2013-7-11
 * @Time: 上午10:25:34
 * 
 */
public enum MainFrame {

	INSTANCE {
		@Override
		public JFrame createMainFrame() {
			mainFrame.setJMenuBar(MenuBar.INSTANCE.createMenuBar());
			mainFrame.setContentPane(ContainerPanel.INSTANCE.createContainerPanel());

			mainFrame.setTitle("微信自定义按钮简易编辑器");
			mainFrame.setSize(400, 400);
			mainFrame.setLocationRelativeTo(null);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setVisible(true);

			mainFrame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent event) {
					ContainerPanel.INSTANCE.getTextPane().setText("正在加载内容，请稍等...");
					// 加载配置文件
					try {
						PropertiesRead.INSTANCE.load("/config.properties");
					} catch (Exception e) {
						e.printStackTrace();
					}
					ThreadGenerate.INSTANCE.createExecutorService().execute(new Runnable() {
						@Override
						public void run() {
							try {
								// 加载现在的菜单
								String accessTokenStr = WebDataReceive.HTTPS.receiveData(
										PropertiesRead.INSTANCE.getProperty("receive_access_token",
												PropertiesRead.INSTANCE.getProperty("app_id"),
												PropertiesRead.INSTANCE.getProperty("app_secret")),
										null);
								System.out.println(accessTokenStr);
								AccessToken accessToken = JsonDeal.INSTANCE.jsonStr2Obj(
										accessTokenStr, AccessToken.class);
								Application.ACCESS_TOKEN = accessToken.getAccess_token();
								String receiveJson = WebDataReceive.HTTPS.receiveData(
										PropertiesRead.INSTANCE.getProperty("get_menu",
												Application.ACCESS_TOKEN), null);
								Object obj = JsonDeal.INSTANCE.jsonStr2Obj(receiveJson,
										Object.class);
								Object buttons = (Map<?, ?>) ((Map<?, ?>) obj).get("menu");
								ContainerPanel.INSTANCE.getTextPane().setText(
										JsonDeal.INSTANCE.jsonObj2PrettyLine(buttons));
							} catch (Exception e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(mainFrame,
										"获取菜单出错，请检查网络是否连接或者已经超出接口连接调用数！");
							}
						}
					});

				}

			});

			return mainFrame;
		}

		@Override
		public JFrame getFrame() {
			return mainFrame;
		}
	};

	protected JFrame mainFrame = new JFrame();

	public abstract JFrame createMainFrame();

	public abstract JFrame getFrame();

}