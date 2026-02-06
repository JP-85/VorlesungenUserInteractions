package self_exercises.dijkstra;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create sample graph
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");

        Edge e1 = new Edge(a, b, 2.0);
        Edge e2 = new Edge(a, c, 5.0);
        Edge e3 = new Edge(b, c, 1.0);
        Edge e4 = new Edge(b, d, 2.5);
        Edge e5 = new Edge(c, e, 3.0);
        Edge e6 = new Edge(d, e, 1.0);
        Edge e7 = new Edge(e, f, 2.0);
        Edge e8 = new Edge(d, f, 4.0);
        Edge e9 = new Edge(b, f, 7.0);

        Graph g = new Graph(
            new Node[]{a, b, c, d, e, f},
            new Edge[]{e1, e2, e3, e4, e5, e6, e7, e8, e9}
        );

        // Compute shortest path A -> F
        List<Node> pathNodes = g.walk(a, f);

        // Show the graph with the shortest path highlighted
        GraphVisualizer.show(g, null);

        // Print path to console
        if (pathNodes != null && !pathNodes.isEmpty()) {
            System.out.println("Shortest path A -> F: " + pathNodes);
        } else {
            System.out.println("No path found from A to F");
        }
    }
}
