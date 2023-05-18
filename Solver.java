public class Solver {
    public static void solve(String expression) {
        char[] tokens = expression.toCharArray();

        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];

        }
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
