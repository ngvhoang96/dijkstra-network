package dijkstrarouter.UI;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class NetworkPanel extends JPanel {
	static JLabel editModeLabel;
	private final HashMap<String, Edge> edgeList;
	private final AppActionListener appActionListener;
	public Router hoveringRouter;
	private String editMode;

	NetworkPanel(AppActionListener actionListener) {
		appActionListener = actionListener;
		setLayout(null);
		setBounds(0, 0, 600, 600);

		editMode = "drag";
		editModeLabel = new JLabel("Edit mode: " + editMode);
		editModeLabel.setBounds(0, 450, 150, 50);
		add(editModeLabel);
		edgeList = new HashMap<>();

		JButton drawLinkButton = new JButton("Edit mode");
		drawLinkButton.setBounds(0, 500, 100, 50);
		drawLinkButton.addActionListener(appActionListener);
		drawLinkButton.setActionCommand("toggle edit mode");
		add(drawLinkButton);

		JButton addNodeButton = new JButton("Add a node");
		addNodeButton.setActionCommand("add a node");
		addNodeButton.addActionListener(appActionListener);
		addNodeButton.setBounds(120, 500, 100, 50);
		add(addNodeButton);

		JButton runAlgo = new JButton("Run Dijkstra");
		runAlgo.setActionCommand("run algo");
		runAlgo.addActionListener(appActionListener);
		runAlgo.setBounds(220, 500, 100, 50);
		add(runAlgo);

		JButton restartButton = new JButton("Reset");
		restartButton.setActionCommand("restart");
		restartButton.addActionListener(appActionListener);
		restartButton.setBounds(400, 500, 100, 50);
		add(restartButton);
	}

	public void toggleEditMode() {
		editMode = editMode.equals("draw") ? "drag" : "draw";
		editModeLabel.setText("Edit mode: " + editMode);
	}

	public String getEditMode() {
		return editMode;
	}

	public void connect(String path, Router origin, Router destination, String cost) {
		Edge edge = new Edge(origin, destination, cost);
		appActionListener.logToReport(String.format("%s -> %s: %s", origin, destination, cost));
		edgeList.put(path, edge);
	}

	public void drawAgain() {
		super.paint(getGraphics());
		edgeList.forEach((s, edge) -> edge.draw(getGraphics()));
	}

	public void drawSelectedEdges(List<String> selectedEdges) {
		super.paint(getGraphics());
		edgeList.forEach((s, edge) -> {
			String reserveS = new StringBuilder().append(s).reverse().toString();
			if (selectedEdges.contains(s) || selectedEdges.contains(reserveS)) {
				edge.drawSelectedEdge(getGraphics());
			} else {
				edge.draw(getGraphics());
			}
		});
	}


	private static class Edge {
		Router origin;
		Router destination;
		String cost;

		Edge(Router origin, Router destination, String cost) {
			this.origin = origin;
			this.destination = destination;
			this.cost = cost;
		}

		private Point randomizedPoint(Point point) {
			int xPos = (int) (point.x - 3 + Math.random() * (6));
			int yPos = (int) (point.y - 3 + Math.random() * (6));
			return new Point(xPos, yPos);
		}

		void drawSelectedEdge(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(3f));
			g2d.setColor(Color.GREEN);
			Point randomOrigin = randomizedPoint(origin.getPosPoint());
			Point randomDestination = randomizedPoint(destination.getPosPoint());
			g.drawLine(randomOrigin.x, randomOrigin.y, randomDestination.x, randomDestination.y);
			g2d.setColor(Color.black);
			g.drawString(cost, (randomDestination.x + randomOrigin.x) / 2, (randomDestination.y + randomOrigin.y) / 2);
		}

		void draw(Graphics g) {
			g.setColor(Color.BLACK);
			Point randomOrigin = randomizedPoint(origin.getPosPoint());
			Point randomDestination = randomizedPoint(destination.getPosPoint());
			g.drawLine(randomOrigin.x, randomOrigin.y, randomDestination.x, randomDestination.y);
			g.drawString(cost, (randomDestination.x + randomOrigin.x) / 2, (randomDestination.y + randomOrigin.y) / 2);
		}

	}
}
