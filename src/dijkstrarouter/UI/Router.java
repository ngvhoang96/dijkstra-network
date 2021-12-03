package dijkstrarouter.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Router extends JPanel implements MouseListener, MouseMotionListener {
	public String routerName;
	private int xPos, yPos;

	Router(String routerName) {
		this.routerName = routerName;
		setBounds(0, 0, 40, 40);
		setBackground(Color.ORANGE);
		addMouseListener(this);
		addMouseMotionListener(this);

		JLabel routerLabel = new JLabel(routerName);
		routerLabel.setBounds(25, 25, 10, 10);
		routerLabel.setBackground(Color.lightGray);
		add(routerLabel);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		e.getComponent().setLocation(e.getX() + e.getComponent().getX() - xPos, e.getY() + e.getComponent().getY() - yPos);
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		System.out.println("Clicked.." + routerName);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		xPos = e.getX();
		yPos = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
