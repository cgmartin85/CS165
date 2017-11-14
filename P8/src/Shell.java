import java.util.Scanner;

/**
 * Created by garethhalladay on 10/26/17
 */
public class Shell {
    private SymbolTable st = new SymbolTable();

    /**
     *
     * @param statement
     * @return
     */
    int calculate(String statement){
        // Instantiate student code
        String[] args = statement.split("=");
        String expression = (args.length == 2) ? args[1] : args[0];
        int result = ExpressionTreeExpanded.evaluateInContext(st, expression);
        if (args.length == 2) {
            String symbol = args[0].trim();
            st.put(symbol, result);
        }
        return result;
    }

    static void repl(Scanner in){
        Shell sh = new Shell();
        System.out.print(">> ");
        while(in.hasNextLine()){
            String equation = in.nextLine();
            if(!equation.trim().isEmpty()) {
                try {
                    System.out.println(sh.calculate(equation));
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.print(">> ");
        }
    }

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        repl(in);
    }
}
