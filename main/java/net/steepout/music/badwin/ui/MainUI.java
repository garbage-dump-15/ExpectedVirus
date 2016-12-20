package net.steepout.music.badwin.ui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.TooManyListenersException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent.EventType;

import net.steepout.music.badwin.ListenerCallback;
import net.steepout.music.badwin.NoteUtil;

public class MainUI extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 7929094953767612372L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainUI frame = new MainUI();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private JPanel contentPane;
	public JTextField fileName;
	public JRadioButton Style7;
	public JRadioButton StyleOOM;
	public JRadioButton styleCrash;
	public JRadioButton styleNonResponse;
	public JRadioButton styleVC;
	JList<String> statusList;
	private JTextField notePop;
	public JSpinner spinner;
	public JTextField countDown;
	public JTextField aliveTime;
	public JTextField txtCrashTitle;
	public JTextField txtDefault;
	public JCheckBox chkScript;

	public JRadioButton styleNvidia;

	/**
	 * Create the frame.
	 */
	public MainUI() {
		setResizable(false);
		setTitle("Expected Virus - Phosphorus15");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 437, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		DropTarget target = new DropTarget();

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Source", null, panel_1, null);
		panel_1.setLayout(null);

		target.setComponent(panel_1);
		try {
			target.addDropTargetListener(new DropTargetAdapter() {

				@Override
				public void drop(DropTargetDropEvent event) {
					if (event.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
						event.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
						try {
							@SuppressWarnings("unchecked")
							File file = ((java.util.List<File>) event.getTransferable()
									.getTransferData(DataFlavor.javaFileListFlavor)).get(0);
							ListenerCallback.openFileManually(file);
						} catch (UnsupportedFlavorException | IOException e) {
						}
					} else
						event.rejectDrop();
				}
			});
		} catch (TooManyListenersException e2) {
			e2.printStackTrace();
		}

		fileName = new JTextField();
		fileName.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		fileName.setEditable(false);
		fileName.setBounds(10, 10, 293, 21);
		panel_1.add(fileName);
		fileName.setColumns(10);

		JButton btnSelectFile = new JButton("File...");
		btnSelectFile.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		btnSelectFile.addActionListener(e -> ListenerCallback.onTriggerFileSelection());
		btnSelectFile.setBounds(313, 9, 93, 23);
		panel_1.add(btnSelectFile);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 41, 396, 195);
		panel_1.add(scrollPane);

		statusList = new JList<String>();
		scrollPane.setViewportView(statusList);
		statusList.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));

		JPanel panel = new JPanel();
		tabbedPane.addTab("Settings", null, panel, null);
		panel.setLayout(null);

		JLabel lblSelectListeningChannel = new JLabel("Select Listening Channel : ");
		lblSelectListeningChannel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		lblSelectListeningChannel.setBounds(10, 10, 159, 15);
		panel.add(lblSelectListeningChannel);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 127, 1));
		spinner.setFont(new Font("Consolas", Font.PLAIN, 12));
		spinner.setToolTipText(
				"The channel might vary unstably(from 0~127) especially when your MIDI is multi-track.\nHowever , it could usually be 0 when your MIDI is single-track.");
		spinner.setBounds(179, 7, 41, 22);
		panel.add(spinner);

		JLabel lblSelectPopupNote = new JLabel("Select Popup Note : ");
		lblSelectPopupNote.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		lblSelectPopupNote.setBounds(10, 48, 159, 15);
		panel.add(lblSelectPopupNote);

		notePop = new JTextField();
		notePop.setText("C5");
		notePop.setBounds(179, 45, 41, 21);
		panel.add(notePop);
		notePop.setColumns(3);
		notePop.setFont(new Font("Consolas", Font.PLAIN, 12));
		notePop.setToolTipText("Must be a valid piano note");

		JLabel lblSelectPopupStyle = new JLabel("Select Popup Style : ");
		lblSelectPopupStyle.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblSelectPopupStyle.setBounds(14, 96, 159, 15);
		panel.add(lblSelectPopupStyle);

		StyleOOM = new JRadioButton("Out of Memory Warning");
		StyleOOM.setSelected(true);
		StyleOOM.setBounds(10, 126, 163, 23);
		panel.add(StyleOOM);

		Style7 = new JRadioButton("Explorer Crash (Offline)");
		Style7.setBounds(10, 151, 174, 23);
		panel.add(Style7);

		styleCrash = new JRadioButton("Memory 'read' Error");
		styleCrash.setBounds(10, 176, 159, 23);
		panel.add(styleCrash);

		styleNvidia = new JRadioButton("NVIDIA OpenGL Error");
		styleNvidia.setBounds(186, 126, 220, 23);
		panel.add(styleNvidia);

		styleNonResponse = new JRadioButton("Program not response");
		styleNonResponse.setBounds(186, 151, 163, 23);
		panel.add(styleNonResponse);

		styleVC = new JRadioButton("Visual C++ Crash");
		styleVC.setBounds(186, 176, 163, 23);
		panel.add(styleVC);

		ButtonGroup group = new ButtonGroup();
		group.add(styleCrash);
		group.add(Style7);
		group.add(StyleOOM);
		group.add(styleNvidia);
		group.add(styleNonResponse);
		group.add(styleVC);

		JButton btnPreview = new JButton("Preview");
		btnPreview.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		btnPreview.addActionListener(e -> ListenerCallback.onTriggerPreview());
		btnPreview.setBounds(139, 92, 119, 23);
		panel.add(btnPreview);

		final JButton customization = new JButton("Customize");
		customization.setEnabled(false);
		customization.addActionListener(e -> ListenerCallback.showCostomizationPopup());
		customization.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		customization.setBounds(230, 43, 108, 23);
		panel.add(customization);

		chkScript = new JCheckBox("Use Script");
		chkScript.addActionListener(e -> {
			if (chkScript.isSelected()) {
				spinner.setEnabled(false);
				notePop.setEnabled(false);
				customization.setEnabled(true);
			} else {
				spinner.setEnabled(true);
				notePop.setEnabled(true);
				customization.setEnabled(false);
			}
		});
		chkScript.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		chkScript.setBounds(230, 6, 123, 23);
		panel.add(chkScript);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Ready!", null, panel_2, null);
		panel_2.setLayout(null);

		JButton btnNewButton = new JButton("Start");
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		btnNewButton.addActionListener(e -> ListenerCallback.onTriggerStart());
		btnNewButton.setBounds(10, 213, 93, 23);
		panel_2.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Count Down Before Start :");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 10, 154, 15);
		panel_2.add(lblNewLabel);

		countDown = new JTextField();
		countDown.setFont(new Font("Consolas", Font.PLAIN, 12));
		countDown.setText("6000");
		countDown.setBounds(174, 7, 66, 21);
		panel_2.add(countDown);
		countDown.setColumns(10);

		JLabel alive = new JLabel("Popup Alive Time :");
		alive.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		alive.setBounds(10, 50, 154, 15);
		panel_2.add(alive);

		JLabel lblms = new JLabel("(ms)");
		lblms.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblms.setBounds(250, 10, 45, 15);
		panel_2.add(lblms);

		aliveTime = new JTextField();
		aliveTime.setFont(new Font("Consolas", Font.PLAIN, 12));
		aliveTime.setText("600");
		aliveTime.setColumns(10);
		aliveTime.setBounds(127, 50, 66, 21);
		panel_2.add(aliveTime);

		JLabel label = new JLabel("(ms)");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		label.setBounds(203, 51, 45, 15);
		panel_2.add(label);

		JLabel lblCrashWindowApplicaion = new JLabel("Crash Window Application Name : ");
		lblCrashWindowApplicaion.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblCrashWindowApplicaion.setBounds(10, 92, 230, 15);
		panel_2.add(lblCrashWindowApplicaion);

		txtCrashTitle = new JTextField();
		txtCrashTitle.setText("Windows \u8D44\u6E90\u7BA1\u7406\u5668");
		txtCrashTitle.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		txtCrashTitle.setColumns(10);
		txtCrashTitle.setBounds(213, 89, 136, 21);
		panel_2.add(txtCrashTitle);

		JLabel lblCrashWindowCustom = new JLabel("Crash Window Custom Icon : ");
		lblCrashWindowCustom.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		lblCrashWindowCustom.setBounds(10, 132, 183, 15);
		panel_2.add(lblCrashWindowCustom);

		txtDefault = new JTextField();
		txtDefault.setEditable(false);
		txtDefault.setText("Default");
		txtDefault.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		txtDefault.setColumns(10);
		txtDefault.setBounds(189, 129, 160, 21);
		panel_2.add(txtDefault);

		JButton btnFile = new JButton("File...");
		btnFile.addActionListener(e -> ListenerCallback.onTriggerIconSelection());
		btnFile.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		btnFile.setBounds(350, 128, 66, 23);
		panel_2.add(btnFile);

		JButton btnDefault = new JButton("Default");
		btnDefault.addActionListener(e -> txtDefault.setText("Default"));
		btnDefault.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 12));
		btnDefault.setBounds(329, 152, 87, 23);
		panel_2.add(btnDefault);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("About", null, panel_3, null);
		panel_3.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_3.add(scrollPane_1, BorderLayout.CENTER);

		JEditorPane dtrpnabout = new JEditorPane();
		dtrpnabout.setEditable(false);
		dtrpnabout.setContentType("text/html");
		dtrpnabout.setText(
				"<html>\r\n\t<head>\r\n\t\t<title>About</title>\r\n\t</head>\r\n\t<body>\r\n\t\t<font face='Consolas'>\r\n\t\t\t<font size='36'><center>Expected Virus</center></font><center>Alpha 1.0</center>\r\n\t\t\t<hr/>\r\n\t\t</font>\r\n\t\t<font face='Consolas'>\r\n\t\t\tDeveloper & UI Design : Phosphorus15 <br/>\r\n\t\t\tInspiration & Splash Screen : Ununpentium <br/>\r\n\t\t\tExtension : Java Native Access (JNA) <br/>\r\n\t\t\t<br/>\r\n\t\t\t<font face='Consolas' size='3'>\r\n\t\t\t\tThis is a freeware , you can modify & use it freely under \r\n\t\t\t\t<a href='http://www.gnu.org/licenses/gpl.html'>GNU Public License</a> .\r\n\t\t\t</font>\r\n\t\t</font>\r\n\t</body>\r\n</html>");
		dtrpnabout.addHyperlinkListener((event) -> {
			if (event.getEventType() == EventType.ACTIVATED)
				if (Desktop.isDesktopSupported())
					try {
						Desktop.getDesktop().browse(event.getURL().toURI());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		});
		scrollPane_1.setViewportView(dtrpnabout);
	}

	public void appendListItem(String str) {
		ListModel<String> model = statusList.getModel();
		DefaultListModel<String> nmodel = new DefaultListModel<String>();
		for (int x = 0; x != model.getSize(); x++) {
			nmodel.addElement(model.getElementAt(x));
		}
		nmodel.addElement(str);
		statusList.setModel(nmodel);
		statusList.repaint();
	}

	public void clearList() {
		statusList.setModel(new DefaultListModel<String>());
		statusList.repaint();
	}

	public int getNote() {
		return NoteUtil.toNote(notePop.getText());
	}
}
