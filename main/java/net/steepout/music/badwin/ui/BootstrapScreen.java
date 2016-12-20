package net.steepout.music.badwin.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class BootstrapScreen extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = -8990599807301604837L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BootstrapScreen dialog = new BootstrapScreen();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			System.out.println("executed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final JPanel contentPanel = new JPanel();

	JLabel status;

	/**
	 * Create the dialog.
	 */
	public BootstrapScreen() {
		setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		ImageIcon icon = new ImageIcon(BootstrapScreen.class.getResource("splash_mini.png"));
		JLabel image = new JLabel(icon);
		contentPanel.add(image, BorderLayout.CENTER);

		status = new JLabel("Now loading...");
		status.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		status.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPanel.add(status, BorderLayout.SOUTH);
		setSize(icon.getIconWidth(), icon.getIconHeight() + status.getHeight());
		setLocationRelativeTo(null);
	}

	public void updateStatus(String string) {
		this.updateStatus(string, Color.BLACK);
	}

	public void updateStatus(String string, Color color) {
		System.out.println(string);
		status.setForeground(color);
		status.setText(string);
	}

}
