package org.zlp.swing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.zlp.app.Application;
import org.zlp.util.JsonDeal;
import org.zlp.util.PropertiesRead;
import org.zlp.util.WebDataReceive;

/**
 * Created with eclipse
 * 
 * @Description: 容器
 * @author: programmer.zlp@qq.com
 * @Date: 2013-7-11
 * @Time: 上午10:15:15
 * 
 */
public enum ContainerPanel {

	INSTANCE {
		@Override
		public JPanel createContainerPanel() {
			JScrollPane scrollPane = new JScrollPane();
			textPanel.setContentType("text/json; charset=UTF-8");
			scrollPane.setViewportView(textPanel);
			panel.setLayout(new BorderLayout());
			panel.add(scrollPane, BorderLayout.CENTER);

			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new FlowLayout());
			bottomPanel.add(new JButton(new ButtonCreateAction()));
			panel.add(bottomPanel, BorderLayout.SOUTH);

			return panel;
		}

		@Override
		public JTextPane getTextPane() {
			return textPanel;
		}
	};

	protected JPanel panel = new JPanel();

	protected JTextPane textPanel = new JTextPane();

	public abstract JPanel createContainerPanel();

	public abstract JTextPane getTextPane();

	/**
	 * 执行按钮创建
	 */
	class ButtonCreateAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public ButtonCreateAction() {
			super("创建按钮");
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			String receiveJson = WebDataReceive.HTTPS
					.receiveData(PropertiesRead.INSTANCE.getProperty("get_menu",
							Application.ACCESS_TOKEN), null);
			Object obj = JsonDeal.INSTANCE.jsonStr2Obj(receiveJson, Object.class);
			Object buttons = (Map<?, ?>) ((Map<?, ?>) obj).get("menu");
			String jsonStr = JsonDeal.INSTANCE.jsonObj2PrettyLine(buttons);
			String jsonOneLine = JsonDeal.INSTANCE.jsonStr2OneLine(jsonStr);
			try {
				// 删除现有按钮
				WebDataReceive.HTTPS.receiveData(PropertiesRead.INSTANCE.getProperty("delete_menu",
						Application.ACCESS_TOKEN), null);
				// 创建新按钮
				String textPanelJsonStr = ContainerPanel.INSTANCE.getTextPane().getText();
				String textPanelJsonStrOneLine = JsonDeal.INSTANCE
						.jsonStr2OneLine(textPanelJsonStr);
				receiveJson = WebDataReceive.HTTPS.receiveData(PropertiesRead.INSTANCE.getProperty(
						"create_menu", Application.ACCESS_TOKEN), textPanelJsonStrOneLine);
				org.zlp.domain.Error error = JsonDeal.INSTANCE.jsonStr2Obj(receiveJson,
						org.zlp.domain.Error.class);
				if ("0".equals(error.getErrcode())) {
					JOptionPane.showMessageDialog(MainFrame.INSTANCE.getFrame(), "创建成功！");
				}
			} catch (Exception e) {
				WebDataReceive.HTTPS.receiveData(PropertiesRead.INSTANCE.getProperty("delete_menu",
						Application.ACCESS_TOKEN), null);
				receiveJson = WebDataReceive.HTTPS.receiveData(PropertiesRead.INSTANCE.getProperty(
						"create_menu", Application.ACCESS_TOKEN), jsonOneLine);
				JOptionPane.showMessageDialog(MainFrame.INSTANCE.getFrame(), "创建失败！");
			}
		}

	}

}