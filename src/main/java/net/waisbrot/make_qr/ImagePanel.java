package net.waisbrot.make_qr;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ImagePanel extends JPanel{
	public ImagePanel() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component c = e.getComponent();
				if (c.getHeight() != c.getWidth()) {
					int hw = Math.min(c.getHeight(), c.getWidth());
					c.setSize(hw, hw);
				}
			}
		});
	}
	private static final long serialVersionUID = -2948196757738085157L;

	private Image image;

	public void setImage(Image image) {
		this.image = image;
		this.repaint();
	}
	
	public void setImage(String text) {
		if (text.isEmpty()) {
			setImage((Image)null);
			return;
		}
		ByteArrayOutputStream os = QRCode.from(text).to(ImageType.PNG).withSize(100, 100).stream();
		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
		try {
			setImage(ImageIO.read(is));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null)
        	g.drawImage(image, 5, 5, getWidth() - 5, getHeight() - 5, 0, 0, 100, 100, null);
    }
}
