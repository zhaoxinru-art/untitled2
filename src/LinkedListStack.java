// LinkedListStack.java
public class LinkedListStack<T> implements StackADT<T> {
    private ListNode<T> top; // 指向栈顶元素

    public LinkedListStack() {
        top = null;
    }

    @Override
    public void push(T value) {
        ListNode<T> newNode = new ListNode<>(value);
        newNode.next = top;
        top = newNode;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T value = top.value;
        top = top.next;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int size() {
        int count = 0;
        ListNode<T> current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        ListNode<T> current = top;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}