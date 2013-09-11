package cs355.lab1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		
		System.out.printf("%d %d\n", e.getX(),e.getY());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// no need
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// no need
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
