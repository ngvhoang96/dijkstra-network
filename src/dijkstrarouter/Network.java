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
	private Node origin;

	public Network() {
		nodeList = new ArrayList<>();
		selectedEdges = new ArrayList<>();
		selectedNodes = new ArrayList<>();
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public void setOrigin(String nodeName) {
		origin = nodeList.stream().filter(node -> node.getNodeName().equals(nodeName)).findFirst().orElse(null);
	}

	public void add(Node node) {
		nodeList.forEach(n -> {
			if (n.getNodeName().equals(node.getNodeName())) {
				throw new RuntimeException("Node existed");
			}
		});
		nodeList.add(node);
	}

	public int size() {
		return nodeList.size();
	}

	public String getNodeReport() {
		StringJoiner result = new StringJoiner(" ");
		nodeList.stream().filter(node -> node != origin)
			.forEach(node -> {
					if (selectedNodes.contains(node)) {
						result.add(" -   ");
					} else {
						int currentDistance = node.getDistanceFromOrigin(origin);
						result.add(String.format("%-5s", (currentDistance == 9999 ? "inf" : currentDistance + "," + node.getPreviousNode())));
					}
				}
			);
		return result.toString();
	}

	public void runDijkstra() {
		if (origin == null) {
			throw new RuntimeException("Cant find the start node");
		} else {
			List<Node> comparingNode = nodeList.stream().
				filter(node -> node != origin)
				.collect(Collectors.toList());

			nodeList.forEach(Node::reset);
			comparingNode.forEach(node -> node.setPreviousNode(origin));

			selectedEdges = new ArrayList<>();
			selectedNodes = new ArrayList<>();
			selectedNodes.add(origin);

			resetFullReport(comparingNode);

			while (comparingNode.size() > 0) {
				fullReport.add(String.format("%-" + nodeList.size() * 2 + "s: %s%n",
					selectedNodes.stream().map(Node::getNodeName).collect(Collectors.joining(",")),
					getNodeReport()
				));

				AtomicReference<Node> nextNode = getNextNode(origin, comparingNode);

				selectedNodes.add(nextNode.get());
				selectedEdges.add(nextNode.get().getPreviousNode() + nextNode.get().getNodeName());

				updateDistanceToNeighborsOf(nextNode, origin);

				comparingNode.remove(nextNode.get());
			}

			fullReport.add(String.format("%-" + nodeList.size() * 2 + "s: %s%n",
				selectedNodes.stream().map(Node::getNodeName).collect(Collectors.joining(",")),
				getNodeReport()
			));
		}
	}

	private void updateDistanceToNeighborsOf(AtomicReference<Node> nextNode, Node origin) {
		int distanceToNextNode = nextNode.get().getDistanceFromOrigin(origin);
		nextNode.get().getNeighbors().forEach(neighbor -> {
			if (!selectedNodes.contains(neighbor)) {
				int newDistance = distanceToNextNode + nextNode.get().getCostTo(neighbor);
				if (newDistance < neighbor.getDistanceFromOrigin(origin)) {
					neighbor.setDistanceFromOrigin(origin, newDistance);
					neighbor.setPreviousNode(nextNode.get());
				}
			}
		});
	}

	private void resetFullReport(List<Node> comparingNode) {
		fullReport = new StringJoiner("\n");

		fullReport.add(String.format("%-" + nodeList.size() * 2 + "s  %s%n",
			"",
			comparingNode.stream().map(Node::getNodeName).map(nodeName -> "  " + nodeName + "  ").collect(Collectors.joining(" "))
		));
	}

	private AtomicReference<Node> getNextNode(Node origin, List<Node> comparingNode) {
		AtomicInteger smallestDistance = new AtomicInteger(0);
		AtomicReference<Node> nextNode = new AtomicReference<>();
		comparingNode.forEach(node -> {
			int currentDistance = node.getDistanceFromOrigin(origin);

			if (smallestDistance.get() == 0 || currentDistance < smallestDistance.get()) {
				smallestDistance.set(currentDistance);
				nextNode.set(node);
			}
		});

		return nextNode;
	}

	public List<String> showSelectedEdges() {
		return selectedEdges;
	}

	public String getFullReport() {
		return fullReport.toString() + "\n" + showSelectedEdges();
	}
}
