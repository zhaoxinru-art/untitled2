// IntegerStack.java
import java.io.Serializable;
import java.util.Stack;

public class IntegerStack implements StackADT<Integer>, Serializable {
    private Stack<Integer> stack = new Stack<>();

    @Override
    public void push(Integer value) {
        stack.push(value);
    }

    @Override
    public Integer pop() {
        return stack.isEmpty() ? null : stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }

    @Override
    public void display() {
        stack.forEach(System.out::println);
    }
}