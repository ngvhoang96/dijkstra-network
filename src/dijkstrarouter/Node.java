package dijkstrarouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Node {
	private final String thisNodeName;
	private final List<Node> neighbors;
	private final HashMap<Node, Integer> edgesList;
	private final int INFINITY = 9999;
	private int distanceFromOrigin;
	private Node previousNode;

	public Node(String nodeName) {
		thisNodeName = nodeName;
		neighbors = new ArrayList<>();
		edgesList = new HashMap<>();
	}

	public Node getPreviousNode() {
		return Optional.ofNullable(previousNode).orElse(null);
	}

	public void setPreviousNode(Node thePreviousNode) {
		previousNode = thePreviousNode;
	}

	public int getDistanceFromOrigin(Node origin) {
		if (distanceFromOrigin != 0) {
			return distanceFromOrigin;
		} else if (getCostTo(origin) != INFINITY) {
			return getCostTo(origin);
		} else {
			return INFINITY;
		}
	}

	public void setDistanceFromOrigin(Node origin, int distance) {
		distanceFromOrigin = distance;
	}

	public void connectWith(Node otherNode, int cost) {
		if (neighbors.contains(otherNode)) {
			throw new RuntimeException(thisNodeName + " -> " + otherNode.getNodeName() + " already exists");
		} else {
			neighbors.add(otherNode);
			edgesList.put(otherNode, cost);

			otherNode.getNeighbors().add(this);
			otherNode.edgesList.put(this, cost);
		}
	}

	public String getNodeName() {
		return thisNodeName;
	}

	public List<Node> getNeighbors() {
		return neighbors;
	}

	public int getCostTo(Node otherNode) {
		return Optional.ofNullable(edgesList.get(otherNode)).orElse(INFINITY);
	}

	public void disconnectWith(Node otherNode) {
		neighbors.remove(otherNode);
		edgesList.remove(otherNode);

		otherNode.neighbors.remove(this);
		otherNode.edgesList.remove(this);
	}

	@Override
	public String toString() {
		return thisNodeName;
	}

	public String findClosetNeighbor() {
		AtomicInteger smallestCost = new AtomicInteger(-1);
		AtomicReference<Node> closestNeighbor = new AtomicReference<>(new Node("none"));

		edgesList.forEach((k, v) -> {
			if (smallestCost.get() == -1 || v < smallestCost.get()) {
				smallestCost.set(v);
				closestNeighbor.set(k);
			}
		});

		return closestNeighbor.toString();
	}

	public void reset() {
		previousNode = null;
		distanceFromOrigin = 0;
	}
}
