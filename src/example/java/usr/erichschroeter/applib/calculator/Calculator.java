package usr.erichschroeter.applib.calculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import usr.erichschroeter.applib.calculator.operations.AddOperation;

public class Calculator {

	public static Number[] parseNumbers(String[] args) {
		List<Number> numbers = new Vector<Number>(args.length);
		for (String arg : args) {
			try {
				BigDecimal parsed = new BigDecimal(arg);
				numbers.add(parsed);
			} catch (NumberFormatException e) {
				// simply catch it and don't add to the result
			}
		}
		return numbers.toArray(new Number[numbers.size()]);
	}

	public static MathOperation parseMathOperation(String operation,
			String[] args) {
		MathOperation mathOperation = null;
		if (operation.equalsIgnoreCase("add")) {
			mathOperation = new AddOperation(parseNumbers(args));
		}
		return mathOperation;
	}

	public static void main(String[] args) throws OperationException {
		if (args == null || args.length < 1) {
			printHelp();
			System.exit(1);
		}
		String operation = args[0];
		// check for help command
		if (operation.equalsIgnoreCase("help")
				|| operation.equalsIgnoreCase("-h")) {
			printHelp();
			System.exit(0);
		}
		// check for version command
		if (operation.equalsIgnoreCase("version")
				|| operation.equalsIgnoreCase("-v")) {
			// print help
			System.out.println(new CalculatorApplication(null).getVersion());
			System.exit(0);
		}

		// parse the math operation
		MathOperation mathOperation = parseMathOperation(args[0],
				Arrays.copyOfRange(args, 1, args.length));
		CalculatorApplication calculator = new CalculatorApplication(
				mathOperation);
		calculator.run();
	}

	public static void printHelp() {
		System.out
				.format("calc <help | -h> <version | -v> operation numbers...%n%noperations:%n%s",
						"add");
	}

}
