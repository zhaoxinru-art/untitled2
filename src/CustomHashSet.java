import java.util.Set;
import java.util.HashSet;

public class CustomHashSet implements SetADT {
    private final Set<Object> set = new HashSet<>();

    public CustomHashSet() {
    }

    @Override
    public void add(Object o) {
        set.add(o);
    }

    @Override
    public void remove(Object o) {
        set.remove(o);
    }

    @Override
    public void intersection(SetADT s) {
        Set<Object> intersection = new HashSet<>(set);
        intersection.retainAll(((CustomHashSet) s).set);
        set.clear();
        set.addAll(intersection);
    }

    @Override
    public void difference(SetADT s) {
        Set<Object> difference = new HashSet<>(set);
        difference.removeAll(((CustomHashSet) s).set);
        set.clear();
        set.addAll(difference);
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }
}