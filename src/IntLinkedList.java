import java.lang.IllegalStateException;

public abstract class IntLinkedList implements StackADT<Integer> {
    private Node head;

    private static class Node {
        Integer data;
        Node next;

        Node(Integer data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public void push(Integer value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
    }

    @Override
    public Integer pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        Integer value = head.data;
        head = head.next;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}