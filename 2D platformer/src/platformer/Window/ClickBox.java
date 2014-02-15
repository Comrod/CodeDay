package platformer.Window;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ClickBox implements MouseMotionListener, MouseListener {

	public ClickBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if((e.getX() > x && e.getX() < x+(width+5)) && (e.getY() > y && e.getY() < y+(height+5))) {
			isMousedOver = true;
		} else {
			isMousedOver = false;
		}
	}

	int x, y, width, height;
	public boolean isMousedOver;
}
