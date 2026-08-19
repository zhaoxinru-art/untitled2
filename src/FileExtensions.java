import java.io.*;
import java.util.*;

public class FileExtensions implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, String> extensions = new HashMap<>();

    public void addEntry(String extension, String program) {
        extensions.put(extension, program);
    }

    public boolean removeEntry(String extension) {
        return extensions.remove(extension) != null;
    }

    public String getProgram(String extension) {
        return extensions.get(extension);
    }

    public Set<String> getExtensions() {
        return new HashSet<>(extensions.keySet());
    }

    public static FileExtensions loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (FileExtensions) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new FileExtensions();
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}