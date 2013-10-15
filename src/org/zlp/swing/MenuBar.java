package org.zlp.swing;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Created with eclipse
 * 
 * @Description: 工具栏
 * @author: programmer.zlp@qq.com
 * @Date: 2013-7-11
 * @Time: 上午9:57:21
 * 
 */
public enum MenuBar {

	INSTANCE {
		@Override
		public JMenuBar createMenuBar() {
			JMenu opMenu = new JMenu("操作");
			JMenu heMenu = new JMenu("帮助");
			opMenu.add(new JMenuItem(new UploadJSONAction()));
			opMenu.add(new JMenuItem(new UpdateTokenAction()));
			heMenu.add(new JMenuItem(new ViewMessageAction()));
			heMenu.add(new JMenuItem(new CopyrightAction()));
			menuBar.add(opMenu);
			menuBar.add(heMenu);
			return menuBar;
		}
	};

	protected final JMenuBar menuBar = new JMenuBar();

	protected static final String[] menuArray = { "操作", "帮助" };

	protected static final String[] menuItemArray01 = { "上传JSON文件", "更新Token" };

	protected static final String[] menuItemArray02 = { "查看信息", "技术支持" };

	public abstract JMenuBar createMenuBar();

	/**
	 * 上传JSON文件Action
	 */
	class UploadJSONAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public UploadJSONAction() {
			super("上传JSON文件");
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	/**
	 * 更新TokenAction
	 */
	class UpdateTokenAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public UpdateTokenAction() {
			super("更新Token");
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	/**
	 * 查看信息
	 */
	class ViewMessageAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public ViewMessageAction() {
			super("查看信息");
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	/**
	 * 技术支持
	 */
	class CopyrightAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		public CopyrightAction() {
			super("技术支持");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(MainFrame.INSTANCE.getFrame(), "E-mail：programmer.zlp@qq.com");
		}

	}

}