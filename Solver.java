public class Solver {

    private class Expression {
        public String numberA;
        public String numberB;
        public char operation;

        public Expression(String numA, char op, String numB) {
            numberA = numA;
            numberB = numB;
            operation = op;
        }
    }

    public static String solve(String expression) {
        char[] tokens = expression.toCharArray();

        char operation = ' ';
        String result = "";
        String[] numbers = new String[] { "", "" };
        int numberIndex = 0;

        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];
            if (Character.isDigit(token) || token == '.') {
                numbers[numberIndex] += token;
            } else {
                operation = token;
                numberIndex = numberIndex == 0 ? 1 : 0;
            }
        }
        result = eval(numbers[0], operation, numbers[1]);

        return result;
    }

    public static String eval(String a, char operation, String b) {
        double da = Double.parseDouble(a);
        double db = Double.parseDouble(b);
        String result = "";

        switch (operation) {
            case '+': {
                result = Double.toString(da + db);
                break;
            }
            case '-': {
                result = Double.toString(da - db);
                break;
            }
            case '*': {
                result = Double.toString(da * db);
                break;
            }
            case '/': {
                result = Double.toString(da / db);
                break;
            }
            default: {
                result = "Not an operation";
                break;
            }
        }
        return result;
    }
}
