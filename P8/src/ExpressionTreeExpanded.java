import java.util.*;

/**
 * Created by {@code garethhalladay} on 10/25/17
 */
public class ExpressionTreeExpanded extends ExpressionTree {

    /**
     * a reference to the SymbolTable. This is used to get variables form the table
     */
    private SymbolTable st;

    /**
     * Creates an instance of ExpressionTreeExpanded.
     * @param st a reference to the SymbolTable
     */
    public ExpressionTreeExpanded(SymbolTable st){
        this.st = st;
    }

    /**
     * Same as the convert method from the previous assignment - but not the convert method doesn't just
     * check if the leaf node is an integer. It checks to make sure the leaf node is node an operator.
     * @param infix the inorder expression
     * @return the postfix expression
     */
    @Override
    public List<String> convert(Queue<String> infix) {
        List<String> postfix = new ArrayList<>();
        Deque<String> operators = new ArrayDeque<>(); // used as a stack
        for(String token : infix){
            // checking if it's not an operator or a parenthesis
            if (!isOperator(token) && !token.equals("(") && !token.equals(")")){
                postfix.add(token);
            }else if(isOperator(token)){
                while(!operators.isEmpty() && !operators.peek().equals("(") &&
                              precedence(operators.peek()) <= precedence(token)){
                    postfix.add(operators.pop());
                }
                operators.addLast(token);
            }else if(token.equals("(")){
                operators.addLast(token);
            }else { // operator should be )
                while(!operators.isEmpty() && !operators.peekLast().equals("(")){
                    postfix.add(operators.removeLast());
                }operators.removeLast();
            }
        }
        while(!operators.isEmpty()){
            String token = operators.removeLast();
            postfix.add(token);
        }
        return postfix;
    }

    /**
     * Evaluate method from above. Utilizes two helper methods
     * @return the result of the expression
     */
    @Override
    public int evaluateRecursive(Node current) {
        if(current.left == null && current.right == null) return valueOfExpanded(current.token);
        return performOperation(current.token, evaluateRecursive(current.left), evaluateRecursive(current.right));
    }

    /**
     * A helper method for evaluate recursive. Takes an operator and two numbers and returns the
     * result of the corresponding operation.
     * @param operator a valid operator for this assignment
     * @param lhs the left hand operand
     * @param rhs the right hand operand
     * @return the result of {@code lhs operator rhs}
     * @throws UnsupportedOperationException if the operator is not valid
     */
    static int performOperation(String operator, int lhs, int rhs){    	
    	switch (operator) {
    	case "*":
    		return lhs * rhs;
    	case "/":
    		return lhs / rhs;
    	case "+":
    		return lhs + rhs;
    	case "-":
    		return lhs - rhs;
    	case "%":
    		return lhs % rhs;
    	default:
    		throw new UnsupportedOperationException();
    	}
    }

    /**
     * If the token is an integer, return the integer value.
     * If not, get the value from the instance of type SymbolTable.
     * @param token a string representing a number or variable
     * @return the decimal representation of the token
     */
    public int valueOfExpanded(String token) {
        if (ATree.isInteger(token)) {
        	return ATree.valueOf(token);
        }
        else {
        	return st.get(token);
        }
    }

    /**
     * Similar to the TestingProgram from the first part.
     * Creates an instance of ExpressionTreeExpanded, parses the expression, converts it to postfix, builds
     * the expression tree and then returns the result.
     * @param context a reference to the symbol table
     * @param exp the expression
     * @return the result of the expression
     */
    static int evaluateInContext(SymbolTable context, String exp){
        ExpressionTreeExpanded t = new ExpressionTreeExpanded(context);
        Queue<String> infix = t.parse(exp);
        List<String> postfix = t.convert(infix);
        t.build(postfix);
        return t.evaluate();
    }


}
