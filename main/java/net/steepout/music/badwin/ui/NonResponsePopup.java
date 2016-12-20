package net.steepout.music.badwin.ui;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.steepout.music.badwin.BadWindows;
import net.steepout.music.badwin.ListenerCallback;

public class NonResponsePopup extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 5040226033639476694L;

	static ImageIcon explorer;

	static ImageIcon troubleshoot;

	static ImageIcon arrow;

	public static String application = "Windows \u8D44\u6E90\u7BA1\u7406\u5668";

	public static ImageIcon custom = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			NonResponsePopup.preparePopupIcon();
			NonResponsePopup dialog = new NonResponsePopup();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void preparePopupIcon() {
		NonResponsePopup.explorer = new ImageIcon(NonResponsePopup.class.getResource("explorer.png"));
		NonResponsePopup.troubleshoot = new ImageIcon(NonResponsePopup.class.getResource("troubleshoot.png"));
		NonResponsePopup.arrow = new ImageIcon(NonResponsePopup.class.getResource("arrow.png"));
	}

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public NonResponsePopup() {
		setResizable(false);
		setTitle("Microsoft Windows");
		setBounds(100, 100, 302, 134);
		getContentPane().setLayout(null);

		JLabel label = new JLabel(
				"\u5E94\u7528\u7A0B\u5E8F\u6CA1\u6709\u54CD\u5E94\u3002\u5982\u679C\u60A8\u7EE7\u7EED\u7B49\u5F85\uFF0C\u7A0B\u5E8F\u53EF\u80FD\u4F1A");
		label.setBounds(21, 10, 265, 15);
		getContentPane().add(label);

		JLabel label_1 = new JLabel("\u54CD\u5E94\u3002");
		label_1.setBounds(21, 25, 265, 15);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel("\u60A8\u60F3\u7ED3\u679C\u8FD9\u4E2A\u8FDB\u7A0B\u5417\uFF1F");
		label_2.setBounds(21, 50, 265, 15);
		getContentPane().add(label_2);

		JButton btnNewButton = new JButton("\u7ED3\u675F\u8FDB\u7A0B(E)");
		btnNewButton.setBounds(93, 75, 98, 22);
		getContentPane().add(btnNewButton);

		JButton button = new JButton("\u53D6\u6D88");
		button.setBounds(193, 75, 98, 22);
		getContentPane().add(button);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(ListenerCallback.slpGlobal);
				} catch (InterruptedException e) {
				} finally {
					NonResponsePopup.this.setVisible(false);
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
