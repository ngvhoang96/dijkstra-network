package dijkstrarouter.UI;

import dijkstrarouter.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Router extends JPanel implements MouseListener, MouseMotionListener {
	private final NetworkPanel networkPanel;
	private final String routerName;
	private final Node node;
	private int xPos, yPos;
	private boolean makingLink;
	private int routerPosX;
	private int routerPosY;

	Router(Node node, NetworkPanel networkPanel) {
		routerName = node.getNodeName();
		this.node = node;

		setBounds(280, 280, 40, 40);
		routerPosX = 300;
		routerPosY = 300;
		setBackground(Color.ORANGE);
		addMouseListener(this);
		addMouseMotionListener(this);

		JLabel routerLabel = new JLabel(routerName);
		routerLabel.setBounds(25, 25, 10, 10);
		routerLabel.setBackground(Color.lightGray);
		add(routerLabel);

		this.networkPanel = networkPanel;
	}

	public Node getNode() {
		return node;
	}

	public Point getPosPoint() {
		return new Point(routerPosX, routerPosY);
	}

	@Override
	public String toString() {
		return routerName;
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (networkPanel.getEditMode().equals("drag")) {
			routerPosX = e.getComponent().getX() + e.getX();
			routerPosY = e.getComponent().getY() + e.getY();
			e.getComponent().setLocation(e.getX() + e.getComponent().getX() - xPos, e.getY() + e.getComponent().getY() - yPos);
			networkPanel.drawAgain();
		} else {
			networkPanel.getGraphics().fillOval(e.getComponent().getX() + e.getX(), e.getComponent().getY() + e.getY(), 5, 5);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		xPos = e.getX();
		yPos = e.getY();
		if (networkPanel.getEditMode().equals("draw")) {
			makingLink = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (makingLink) {
			if (networkPanel.hoveringRouter != null && !networkPanel.hoveringRouter.routerName.equals(routerName)) {
				String cost = JOptionPane.showInputDialog(String.format("What's the cost of %s ?", routerName + networkPanel.hoveringRouter));
				if (cost.length() > 0) {
					try {
						node.connectWith(networkPanel.hoveringRouter.getNode(), Integer.parseInt(cost));
						networkPanel.connect(routerName + networkPanel.hoveringRouter, this, networkPanel.hoveringRouter, cost);
						makingLink = false;
						networkPanel.drawAgain();
					} catch (RuntimeException ex) {
						JOptionPane.showMessageDialog(networkPanel, ex.getMessage());
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		networkPanel.hoveringRouter = this;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		networkPanel.hoveringRouter = null;
	}
}
