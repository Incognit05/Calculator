import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Solver {

    public static String solve(String expression) {
        char[] tokens = expression.toCharArray();
        List<String> list = new ArrayList<>();

        String str = "";
        String numA = "";
        String numB = "";
        String operator = "";

        // split expression into list of parts
        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];

            // check if token is part of number (number or '.' or '-')
            if (Character.isDigit(token) || token == '.'
                    || (i == 0 && token == '-')
                    || (token == '-' && !Character.isDigit(tokens[i - 1]))) {
                str += token;
            } else {
                // otherwise token is an operator
                list.add(str);
                str = "";
                list.add(token + "");
            }
            // last token
            if (i == tokens.length - 1) {
                list.add(str);
                str = "";
            }
        }

        // solving
        try {
            for (int i = 0; i < list.size(); i++) {
                str = list.get(i);
                if (str.equals("*") || str.equals("/")) {
                    numA = list.get(i - 1);
                    operator = list.get(i);
                    numB = list.get(i + 1);
                    list.set(i - 1, eval(numA, operator, numB));
                    list.remove(i + 1);
                    list.remove(i);
                }
            }
            for (int i = 0; i < list.size(); i++) {
                str = list.get(i);
                if (str.equals("+") || str.equals("-")) {
                    numA = list.get(i - 1);
                    operator = list.get(i);
                    numB = list.get(i + 1);
                    list.set(i - 1, eval(numA, operator, numB));
                    list.remove(i + 1);
                    list.remove(i);
                }
            }
            return list.get(0);
        } catch (Exception e) {
            return "Error";
        }
    }

    // public static String old_solve(String expression) {
    // char[] tokens = expression.toCharArray();

    // ArrayList<String> list = new ArrayList<String>();

    // String s = "";
    // String numA = "";
    // String numB = "";
    // String operator = "";

    // boolean operationOnQueue = false;

    // try {
    // for (int i = 0; i < tokens.length; i++) {
    // char token = tokens[i];
    // // token is a number
    // if (Character.isDigit(token) || token == '.'
    // // - for negative numbers
    // || (i == 0 && token == '-')
    // || (token == '-' && !Character.isDigit(tokens[i - 1]))) {
    // s += token;
    // } else {
    // // token is an operator

    // // add current number to list
    // list.add(s);
    // // add operator
    // list.add(token + "");

    // // if operation is on queue, then pre compute it
    // if (operationOnQueue) {
    // operationOnQueue = false;

    // numB = s;
    // list.set(list.lastIndexOf(numA), eval(numA, operator, numB));
    // list.remove(list.lastIndexOf(operator));
    // list.remove(list.lastIndexOf(numB));
    // }

    // // if mul or div, then add to queue
    // if (token == '*' || token == '/') {
    // operationOnQueue = true;

    // operator = token + "";
    // numA = list.get(list.lastIndexOf(operator) - 1);
    // }
    // s = "";
    // }
    // // last token
    // if (i == tokens.length - 1 && s.length() > 0) {
    // list.add(s);
    // int listSize = list.size();

    // // if last calculation is mul or div, then pre compute it
    // String op = list.get(listSize - 2);
    // if (op.equals("*") || op.equals("/")) {
    // numA = list.get(listSize - 3);
    // operator = op;
    // numB = list.get(listSize - 1);

    // list.set(list.size() - 3, eval(numA, operator, numB));
    // list.remove(list.size() - 2);
    // list.remove(list.size() - 1);
    // }
    // }
    // }
    // // go through list and solve
    // // no mul or div should be there, only add and sub
    // while (list.size() > 2) {
    // String na = list.get(0);
    // String op = list.get(1);
    // String nb = list.get(2);
    // list.set(0, eval(na, op, nb));
    // list.remove(2);
    // list.remove(1);
    // }
    // return list.get(0);
    // } catch (Exception e) {
    // return "Error";
    // }
    // }

    private static String eval(String a, String operator, String b) {
        double r = 0;
        double da = Double.parseDouble(a);
        double db = Double.parseDouble(b);

        switch (operator) {
            case "/":
                r += da / db;
                break;
            case "*":
                r += da * db;
                break;
            case "-":
                r += da - db;
                break;
            case "+":
                r += da + db;
                break;
        }

        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.getDefault());
        dfs.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.########", dfs);
        return df.format(r);
    }
}
