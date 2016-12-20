package net.steepout.music.badwin.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import net.steepout.music.badwin.BadWindows;
import net.steepout.music.badwin.ListenerCallback;

public class OOMPopup extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 7300747143489375593L;

	public static ImageIcon warning;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			OOMPopup.preparePopupIcon();
			OOMPopup dialog = new OOMPopup();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void preparePopupIcon() {
		OOMPopup.warning = new ImageIcon(OOMPopup.class.getResource("warn.png"));
	}

	/**
	 * Create the dialog.
	 */
	public OOMPopup() {
		setTitle("Microsoft Windows");
		setResizable(false);
		setIconImage(OOMPopup.warning.getImage());
		setBounds(100, 100, 350 + 2, 158);
		getContentPane().setLayout(null);

		JLabel wicon = new JLabel();
		wicon.setIcon(OOMPopup.warning);
		wicon.setBounds(10, 10, 32, 32);// 然后关闭或重新启动所有打开的程序。
		getContentPane().add(wicon);

		JLabel label = new JLabel("\u8BA1\u7B97\u673A\u7684\u5185\u5B58\u4E0D\u8DB3");
		label.setForeground(new Color(0, 51, 153));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(52, 10, 263, 22);
		getContentPane().add(label);

		JLabel lbln = new JLabel(
				"\u82E5\u8981\u8FD8\u539F\u8DB3\u591F\u7684\u5185\u5B58\u4EE5\u4F7F\u7A0B\u5E8F\u5DE5\u4F5C\u6B63\u786E\uFF0C\u8BF7\u4FDD\u5B58\u6587\u4EF6\uFF0C");
		lbln.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lbln.setBounds(52, 42, 296, 22);
		getContentPane().add(lbln);

		JLabel label_1 = new JLabel(
				"\u7136\u540E\u5173\u95ED\u6216\u91CD\u65B0\u542F\u52A8\u6240\u6709\u6253\u5F00\u7684\u7A0B\u5E8F\u3002");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		label_1.setBounds(52, 62, 296, 22);
		getContentPane().add(label_1);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(-13, 94, 372, 56);
		getContentPane().add(panel);
		panel.setLayout(null);

		JButton btnConfirm = new JButton("\u786E\u5B9A");
		btnConfirm.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnConfirm.setBounds(277, 3, 72, 26);
		panel.add(btnConfirm);
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(ListenerCallback.slpGlobal);
				} catch (InterruptedException e) {
				} finally {
					OOMPopup.this.setVisible(false);
				}
			}
		}.start();
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible) {
			BadWindows.MessageBoxInterface.instance.MessageBeep(0x10);
		}
		super.setVisible(visible);
	}
}
