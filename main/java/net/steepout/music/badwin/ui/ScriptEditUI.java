package net.steepout.music.badwin.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.steepout.music.badwin.ListenerCallback;

public class ScriptEditUI extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = -8139072623301135741L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ScriptEditUI dialog = new ScriptEditUI();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final JPanel contentPanel = new JPanel();

	JEditorPane scriptEditor;

	/**
	 * Create the dialog.
	 */
	public ScriptEditUI() {
		setTitle("Edit Script Content");
		setModal(true);
		setBounds(100, 100, 439, 252);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				scriptEditor = new JEditorPane();
				scriptEditor.setFont(new Font("Consolas", Font.PLAIN, 12));
				scriptEditor.setText(ListenerCallback.defaultScript);
				scrollPane.setViewportView(scriptEditor);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e -> {
					ListenerCallback.defaultScript = scriptEditor.getText();
					setModal(false);
					setVisible(false);
				});
				okButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e -> {
					setModal(false);
					setVisible(false);
				});
				cancelButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
