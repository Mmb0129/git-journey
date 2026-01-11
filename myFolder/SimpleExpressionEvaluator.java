import java.util.Stack;

/**
 * SimpleExpressionEvaluator
 *
 * A minimal arithmetic expression evaluator used to demonstrate
 * how an AI agent can understand and summarize source code
 * accessed via a GitHub MCP Server.
 *
 * Supported operations:
 *  - Addition (+)
 *  - Subtraction (-)
 *  - Multiplication (*)
 *  - Division (/)
 *
 * Limitations:
 *  - No operator precedence
 *  - No parentheses support
 *  - Input must be space-separated
 *
 * Example:
 *   Input: "10 + 5 * 2"
 *   Output: 30
 */
public class SimpleExpressionEvaluator {

    /**
     * Evaluates a basic arithmetic expression.
     *
     * @param expression space-separated expression (e.g., "10 + 5")
     * @return evaluated result
     */
    public int evaluate(String expression) {
        String[] tokens = expression.split(" ");
        Stack<Integer> values = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                int b = values.pop();
                int a = values.pop();
                values.push(applyOperator(a, b, token));
            } else {
                values.push(Integer.parseInt(token));
            }
        }
        return values.pop();
    }

    private boolean isOperator(String token) {
        return "+-*/".contains(token);
    }

    private int applyOperator(int a, int b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                return a / b;
            default:
                throw new IllegalStateException("Unknown operator: " + operator);
        }
    }

    public static void main(String[] args) {
        SimpleExpressionEvaluator evaluator = new SimpleExpressionEvaluator();
        System.out.println(evaluator.evaluate("10 + 5 * 2"));
    }
}
