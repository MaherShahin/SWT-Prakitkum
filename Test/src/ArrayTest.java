import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class ArrayTest extends TestCase {
	public List<Integer> fixture;
	
	@Before
	public final void setUp() {
		fixture = new ArrayList<Integer>();
	}

	@Test
	public final void testNewArrayIsEmpty() {

		

		assertEquals("List not empty!", 0, fixture.size());
	}

	@Test
	public final void testArrayContainsAnElement() {

		fixture.add(42);

		assertEquals("List size is not equal to 1", 1, fixture.size());
	}
}
