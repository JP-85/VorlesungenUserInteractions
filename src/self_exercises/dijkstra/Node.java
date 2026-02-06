package self_exercises.dijkstra;

// java
public class Node {
    public final String id;
    public double x, y;
    public String label;

    public Node(String id) {
        this(id, id, 0, 0);
    }

    public Node(String id, String label, double x, double y) {
        this.id = id;
        this.label = label;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node other = (Node) o;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return label;
    }
}
