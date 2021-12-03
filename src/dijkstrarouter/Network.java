package dijkstrarouter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Network {
	private final List<Node> nodeList;
	private List<String> selectedEdges;
	private List<Node> selectedNodes;
	private StringJoiner fullReport;

	public Network() {
		nodeList = new ArrayList<>();
		selectedEdges = new ArrayList<>();
		selectedNodes = new ArrayList<>();
	}

	public void add(Node node) {
		nodeList.add(node);
	}

	public int size() {
		return nodeList.size();
	}

	public String getNodeReport() {
		StringJoiner result = new StringJoiner(" ");
		nodeList.stream().filter(node -> node != nodeList.get(0))
			.forEach(neighbor -> {
					if (selectedNodes.contains(neighbor)) {
						result.add(" -   ");
					} else {
						int currentDistance = neighbor.getDistanceFrom(nodeList.get(0));
						result.add(String.format("%-5s", (currentDistance == 9999 ? "inf" : currentDistance) + "," + neighbor));
					}
				}
			);
		return result.toString();
	}

	public void runDijkstra() {
		Node origin = nodeList.get(0);
		List<Node> comparingNode = nodeList.stream().
			filter(node -> node != origin)
			.collect(Collectors.toList());

		selectedEdges = new ArrayList<>();
		selectedNodes = new ArrayList<>();
		selectedNodes.add(origin);
		fullReport = new StringJoiner("\n");

		while (comparingNode.size() > 0) {
			fullReport.add(String.format("%-" + nodeList.size() * 2 + "s: %s%n",
				selectedNodes.stream().map(Node::getNodeName).collect(Collectors.joining(",")),
				getNodeReport()
			));

			AtomicReference<Node> closestNode = getTheClosestNode(origin, comparingNode);
			comparingNode.remove(closestNode.get());

			if (closestNode.get().getPreviousNode() == null) {
				closestNode.get().setPreviousNode(origin);
			}
			selectedNodes.add(closestNode.get());
			selectedEdges.add(closestNode.get().getPreviousNode() + closestNode.get().getNodeName());

			int distanceToClosestNode = closestNode.get().getDistanceFrom(nodeList.get(0));
			closestNode.get().getNeighbors().forEach(neighbor -> {
				if (!selectedNodes.contains(neighbor)) {
					int newDistance = distanceToClosestNode + closestNode.get().getCostTo(neighbor);
					if (newDistance < neighbor.getDistanceFrom(origin)) {
						neighbor.setDistanceFrom(origin, newDistance);
						neighbor.setPreviousNode(closestNode.get());
					}
				}
			});
		}
		fullReport.add(String.format("%-" + nodeList.size() * 2 + "s: %s%n",
			selectedNodes.stream().map(Node::getNodeName).collect(Collectors.joining(",")),
			getNodeReport()
		));
	}

	private AtomicReference<Node> getTheClosestNode(Node origin, List<Node> comparingNode) {
		AtomicInteger smallestDistance = new AtomicInteger(0);
		AtomicReference<Node> closestNode = new AtomicReference<>();
		comparingNode.forEach(node -> {
			int currentDistance = node.getDistanceFrom(origin);

			if (smallestDistance.get() == 0 || currentDistance < smallestDistance.get()) {
				smallestDistance.set(currentDistance);
				closestNode.set(node);
			}
		});

		return closestNode;
	}

	public List<String> showSelectedEdges() {
		return selectedEdges;
	}

	public String getFullReport() {
		return fullReport.toString();
	}
}
