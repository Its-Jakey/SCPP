package slvm;

import java.util.Stack;

public class VarStack extends Stack<String> {
    public String swapPop() {
        String top = pop();
        String next = pop();
        push(top);
        return next;
    }
}
