package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSample {
	@Test
	void canary() {
		assertTrue(true);
	}

	@Test
	void sampleMakesText() {
		Sample sample = new Sample();
		assertEquals("Sample", sample.makeText());
	}
}
