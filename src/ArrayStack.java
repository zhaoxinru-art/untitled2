public class ArrayStack<T> implements StackADT<T> {
    private T[] array;
    private int top;

    public ArrayStack(int size) {
        array = (T[]) new Object[size];
        top = -1;
    }

    @Override
    public void push(T value) {
        if (top < array.length - 1) {
            array[++top] = value;
        } else {
            throw new IllegalStateException("Stack is full");
        }
    }

    @Override
    public T pop() {
        if (!isEmpty()) {
            return array[top--];
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    public void display() {
        System.out.print("Stack: ");
        for (int i = top; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(20);
        System.out.println("testing Stack");
        System.out.println("testing is empty " + stack.isEmpty());
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
        }
        System.out.println("num values in stack: " + stack.size());
        System.out.println("list has 5 items");
        stack.display();
        System.out.println("popping value " + stack.pop());
        System.out.println("value 5 should have been removed");
        stack.display();
    }
}