package net.waisbrot.make_qr;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class Application {

	private JFrame frame;
	private ImagePanel imagePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 528, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.3);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		imagePanel = new ImagePanel();
		splitPane.setRightComponent(imagePanel);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.getDocument().addDocumentListener(new DocumentListener(){
			public void insertUpdate(DocumentEvent e) {update(e);}
			public void removeUpdate(DocumentEvent e) {update(e);}
			public void changedUpdate(DocumentEvent e) {update(e);}
			private void update(DocumentEvent e) {
				try {
					String doctext = e.getDocument().getText(0, e.getDocument().getLength());
					imagePanel.setImage(doctext);
				} catch (BadLocationException e1) {
					throw new RuntimeException(e1);
				}
			}});
	}
}
