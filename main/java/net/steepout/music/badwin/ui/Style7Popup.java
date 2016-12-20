package net.steepout.music.badwin.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.steepout.music.badwin.BadWindows;
import net.steepout.music.badwin.ListenerCallback;

public class Style7Popup extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = -6039865798466873764L;

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
			Style7Popup.preparePopupIcon();
			Style7Popup dialog = new Style7Popup();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void preparePopupIcon() {
		Style7Popup.explorer = new ImageIcon(Style7Popup.class.getResource("explorer.png"));
		Style7Popup.troubleshoot = new ImageIcon(Style7Popup.class.getResource("troubleshoot.png"));
		Style7Popup.arrow = new ImageIcon(Style7Popup.class.getResource("arrow.png"));
	}

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public Style7Popup() {
		setResizable(false);
		setTitle(Style7Popup.application);
		setIconImage(Style7Popup.troubleshoot.getImage());
		setBounds(100, 100, 364, 148);
		getContentPane().setLayout(null);

		JLabel lblWindows = new JLabel(Style7Popup.application + " \u5DF2\u505C\u6B62\u5DE5\u4F5C");
		lblWindows.setForeground(new Color(0, 51, 153));
		lblWindows.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		lblWindows.setBounds(49, 10, 263, 22);
		getContentPane().add(lblWindows);

		JLabel lblWindows_1 = new JLabel(
				"Windows \u53EF\u4EE5\u5C1D\u8BD5\u91CD\u65B0\u542F\u52A8\u8BE5\u7A0B\u5E8F\u3002");
		lblWindows_1.setBounds(49, 42, 233, 15);
		lblWindows_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		getContentPane().add(lblWindows_1);

		JLabel iconExp = new JLabel() {
			/**
			 *
			 */
			private static final long serialVersionUID = 4770047390147440995L;

			@Override
			public void paint(Graphics g) {
				g.drawImage(
						(Style7Popup.custom == null) ? Style7Popup.explorer.getImage() : Style7Popup.custom.getImage(),
						0, 0, 32, 32, Style7Popup.this);
			}
		};
		// iconExp.setIcon(new
		// ImageIcon(Style7Popup.class.getResource("/net/steepout/music/badwin/ui/explorer.png")));
		iconExp.setBounds(10, 10, 40, 40);
		getContentPane().add(iconExp);

		JLabel arrowlb = new JLabel("");
		arrowlb.setIcon(Style7Popup.arrow);
		arrowlb.setBounds(59, 74, 20, 20);
		getContentPane().add(arrowlb);

		JLabel label = new JLabel("\u91CD\u65B0\u542F\u52A8\u7A0B\u5E8F");
		label.setForeground(new Color(46, 75, 115));
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 16));
		label.setBounds(89, 74, 263, 22);
		getContentPane().add(label);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(ListenerCallback.slpGlobal);
				} catch (InterruptedException e) {
				} finally {
					Style7Popup.this.setVisible(false);
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
