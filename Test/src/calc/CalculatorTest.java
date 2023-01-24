package calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import junit.framework.TestCase;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CalcFunctions.class)
public class CalculatorTest extends TestCase {

	@Test
	public void testCalc() {
		
		PowerMockito.mockStatic(CalcFunctions.class);
		PowerMockito.when(CalcFunctions.multiply(4, 2)).thenReturn(8);
		int x = 4;
		int y = 2;
		int type = 3;
		Calculator c = new Calculator();
		int result = c.calc(x, y, type);
		assertEquals(result, 8);
	}
}
