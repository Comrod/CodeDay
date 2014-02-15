package platformer.Window;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Text implements MouseMotionListener, MouseListener {

	public Text(StringBuffer text, int x, int y) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.isMousedOver = false;
	}

	public Text(StringBuffer text, int x, int y, boolean shadow) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.isMousedOver = false;
		this.shadow = shadow;
	}

	public void render(Graphics g) {
		if(fontm == null)
			fontm = g.getFontMetrics(font);
		height = fontm.getHeight();
		length = fontm.stringWidth(text.toString());
		g.setFont(font);
		if(shadow) {
			g.setColor(Color.black);
			g.drawString(text.toString(), x-2, y-2);
		}
		if(!isMousedOver) {
			g.setColor(notMousedOver);
		} else {
			g.setColor(MousedOver);
		}
		g.drawString(text.toString(), x, y);
	}

	public void setFont(String name) {
		font = new Font(name, font.getStyle(), font.getSize());
	}
	public void setStyle(int style) {
		font = new Font(font.getName(), style, font.getSize());
	}
	public void setSize(int size) {
		font = new Font(font.getName(), font.getStyle(), size);
	}
	public Font getFont() {
		return font;
	}

	public void setMousedOver(boolean isMousedOver) {
		this.isMousedOver = isMousedOver;
	}
	public boolean getMousedOver() {
		return isMousedOver;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if((x+4 < e.getX() && e.getX() < x+length+4) && (y+24 > e.getY() && e.getY() > 24+y-(height*.55))) {
			isMousedOver = true;
		} else {
			isMousedOver = false;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	int[] textColor = {250, 250, 20, 255};
	int[] textHover = {100, 200, 100, 205};
	Color notMousedOver = new Color(textColor[0], textColor[1], textColor[2], textColor[3]);
	Color MousedOver = new Color(textHover[0], textHover[1], textHover[2], textHover[3]);
	Font font = new Font("Monospaced", Font.BOLD, 24);

	FontMetrics fontm;
	public StringBuffer text;
	public boolean shadow = false;
	boolean isMousedOver;
	public int x, y, height, length;
}
