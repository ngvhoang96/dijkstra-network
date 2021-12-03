package dijkstrarouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Node {
	private final String thisNodeName;
	private final List<String> neighbors;
	private final HashMap<String, Integer> edgesList;

	public Node(String nodeName) {
		thisNodeName = nodeName;
		neighbors = new ArrayList<>();
		edgesList = new HashMap<>();
	}

	public void connectWith(Node otherNode, int cost) {
		neighbors.add(otherNode.getNodeName());
		edgesList.put(otherNode.getNodeName(), cost);

		otherNode.getNeighbors().add(thisNodeName);
		otherNode.edgesList.put(thisNodeName, cost);
	}

	public String getNodeName() {
		return thisNodeName;
	}

	public List<String> getNeighbors() {
		return neighbors;
	}

	public int getCostTo(String otherNodeName) {
		return Optional.ofNullable(edgesList.get(otherNodeName)).orElse(9999);
	}

	public void disconnectWith(Node otherNode) {
		neighbors.remove(otherNode.getNodeName());
		edgesList.remove(otherNode.getNodeName());

		otherNode.neighbors.remove(thisNodeName);
		otherNode.edgesList.remove(thisNodeName);
	}

	@Override
	public String toString() {
		return thisNodeName;
	}

	public String findClosetNeighbor() {
		AtomicInteger smallestCost = new AtomicInteger(-1);
		AtomicReference<String> closestNeighbor = new AtomicReference<>("");

		edgesList.forEach((k, v) -> {
			if (smallestCost.get() == -1 || v < smallestCost.get()) {
				smallestCost.set(v);
				closestNeighbor.set(k);
			}
		});

		return closestNeighbor.get();
	}
}
