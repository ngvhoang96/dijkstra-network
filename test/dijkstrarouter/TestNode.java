package dijkstrarouter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestNode {
	Node nodeA;

	@BeforeEach
	void setup() {
		nodeA = new Node("A");
	}

	@Test
	void canary() {
		assertTrue(true);
	}

	@Test
	void neighborsIsAListOfOtherNodes() {
		nodeA.connectWith(new Node("B"), 2);

		assertEquals("B", nodeA.getNeighbors().get(0).getNodeName());
	}

	@Test
	void aConnectionHasACost() {
		Node nodeB = new Node("B");
		nodeA.connectWith(nodeB, 2);

		assertEquals(2, nodeA.getCostTo(nodeB));
	}

	@Test
	void aConnectionIsTwoWay() {
		Node nodeB = new Node("B");

		nodeA.connectWith(nodeB, 3);

		assertEquals("A", nodeB.getNeighbors().get(0).getNodeName());
		assertEquals(3, nodeB.getCostTo(nodeA));
	}

	@Test
	void aNodeCanConnectToMultipleNodes() {
		Node nodeB = new Node("B");
		Node nodeC = new Node("C");

		nodeA.connectWith(nodeB, 3);
		nodeA.connectWith(nodeC, 1);

		assertEquals(1, nodeC.getCostTo(nodeA));
		assertEquals(3, nodeB.getCostTo(nodeA));
	}

	@Test
	void cannotConnectToTheSameNodeTwice() {
		Node nodeB = new Node("B");

		nodeA.connectWith(nodeB, 1);
		assertThrows(RuntimeException.class, () -> nodeA.connectWith(nodeB, 3));
	}

	@Test
	void costShouldUpdateOnNewConnection() {
		Node nodeB = new Node("B");

		nodeA.connectWith(nodeB, 2);
		nodeA.disconnectWith(nodeB);
		nodeA.connectWith(nodeB, 4);

		assertEquals(1, nodeA.getNeighbors().size());
		assertEquals(1, nodeB.getNeighbors().size());

		assertEquals(4, nodeA.getCostTo(nodeB));
		assertEquals(4, nodeB.getCostTo(nodeA));
	}

	@Test
	void distanceIsDifferentFromCostAndCanBeSet() {
		Node nodeB = new Node("B");

		nodeB.setDistanceFrom(nodeA, 3);

		assertEquals(3, nodeB.getDistanceFrom(nodeA));
	}

	@Test
	void nodeCanFindClosestNeighbor() {
		nodeA.connectWith(new Node("B"), 1);
		nodeA.connectWith(new Node("D"), 3);

		assertEquals("B", nodeA.findClosetNeighbor());
	}

	@Test
	void findClosestNeighborOnEmptyNeighbor() {
		assertEquals("none", nodeA.findClosetNeighbor());
	}
}
