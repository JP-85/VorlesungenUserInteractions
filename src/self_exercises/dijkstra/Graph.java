package self_exercises.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.Comparator;

public class Graph {
    Node[] nodes;
    Edge[] edges;
    // Cache adjacency list to avoid rebuilding it every time
    private Map<Node, List<Edge>> adjacencyList = null;

    public Graph() {
        this.nodes = new Node[]{};
        this.edges = new Edge[]{};
    }

    public Graph(Node[] nodes, Edge[] edges) {
        this.edges = edges;
        this.nodes = nodes;
    }

    public void addNode(Node new_node, Edge[] new_edges) {
        List<Node> nodeList = new ArrayList<>(Arrays.asList(this.nodes));
        List<Edge> edgeList = new ArrayList<>(Arrays.asList(this.edges));

        // Actually add the new node and edges
        nodeList.add(new_node);
        edgeList.addAll(Arrays.asList(new_edges));

        this.nodes = nodeList.toArray(Node[]::new);
        this.edges = edgeList.toArray(Edge[]::new);

        // Invalidate adjacency list cache
        this.adjacencyList = null;
    }

    // Build adjacency list once and cache it
    private Map<Node, List<Edge>> getAdjacencyList() {
        if (adjacencyList == null) {
            adjacencyList = new HashMap<>();
            for (Node n : nodes) {
                adjacencyList.put(n, new ArrayList<>());
            }
            for (Edge e : edges) {
                adjacencyList.get(e.from).add(e);
                // if graph is undirected: also add reverse edge
                // adjacencyList.get(e.to).add(new Edge(e.to, e.from, e.weight));
            }
        }
        return adjacencyList;
    }

    // Helper class for priority queue (more efficient than AbstractMap.SimpleEntry)
    private static class NodeDistance {
        final Node node;
        final double distance;

        NodeDistance(Node node, double distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    // Dijkstra: returns shortest path from source to target (empty list if none)
    public List<Node> walk(Node source, Node target) {
        if (source == null || target == null) return Collections.emptyList();
        if (source.equals(target)) return Collections.singletonList(source);

        Map<Node, List<Edge>> adj = getAdjacencyList();
        Map<Node, Double> dist = new HashMap<>();
        Map<Node, Node> prev = new HashMap<>();

        // Initialize distances
        for (Node n : nodes) {
            dist.put(n, Double.POSITIVE_INFINITY);
        }

        PriorityQueue<NodeDistance> pq = new PriorityQueue<>(
                Comparator.comparingDouble(nd -> nd.distance)
        );

        dist.put(source, 0.0);
        pq.offer(new NodeDistance(source, 0.0));

        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            Node u = current.node;
            double d = current.distance;

            // Skip if we've already found a better path
            if (d > dist.get(u)) continue;

            // Early exit when target is reached
            if (u.equals(target)) break;

            List<Edge> neighbors = adj.get(u);
            for (Edge e : neighbors) {
                Node v = e.to;
                double alt = d + e.weight;
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    pq.offer(new NodeDistance(v, alt));
                }
            }
        }

        // Reconstruct path
        if (dist.get(target).isInfinite()) {
            return Collections.emptyList();
        }

        List<Node> path = new ArrayList<>();
        for (Node at = target; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
