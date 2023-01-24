package calc;

public class Calculator {

	static final int ADD = 1, SUB = 2, MULT = 3, DIV = 4;

	public Calculator() {
		super();
	}

	public int calc(int x, int y, int type) {
		int result = 0;
		switch (type) {
		case MULT: {
			result = CalcFunctions.multiply(x, y);
			break;
			// ...
		}
		}
		return result;
	}
}
