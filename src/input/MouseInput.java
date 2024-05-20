package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{
	
	public static int x,y;
	public static boolean MouseLeftBotton;

	

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton()== MouseEvent.BUTTON1) {
			 MouseLeftBotton = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton()== MouseEvent.BUTTON1) {
			 MouseLeftBotton = false;
		}
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		x= e.getX();
		y= e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x= e.getX();
		y= e.getY();
	}

}
