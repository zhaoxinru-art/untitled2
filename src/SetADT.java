import java.util.Set;

public interface SetADT {
    void add(Object o);
    void remove(Object o);
    void intersection(SetADT s);
    void difference(SetADT s);
    int size();
    boolean isEmpty();
}