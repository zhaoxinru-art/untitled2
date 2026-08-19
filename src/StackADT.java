// StackADT.java
public interface StackADT<T> {
    void push(T value);
    T pop();
    boolean isEmpty();
    int size();
    void display();
}