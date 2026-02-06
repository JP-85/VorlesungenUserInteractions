package self_exercises.dijkstra;// java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GraphVisualizer {

    // Helper method to convert node path to edge path
    private static List<Edge> getEdgesFromPath(Graph g, List<Node> pathNodes) {
        List<Edge> pathEdges = new ArrayList<>();
        if (pathNodes == null || pathNodes.size() < 2) {
            return pathEdges;
        }

        for (int i = 0; i < pathNodes.size() - 1; i++) {
            Node from = pathNodes.get(i);
            Node to = pathNodes.get(i + 1);
            for (Edge edge : g.edges) {
                if (edge.from.equals(from) && edge.to.equals(to)) {
                    pathEdges.add(edge);
                    break;
                }
            }
        }
        return pathEdges;
    }

    // Helper method to calculate total path distance
    private static double calculatePathDistance(List<Edge> pathEdges) {
        double sum = 0.0;
        for (Edge edge : pathEdges) {
            sum += edge.weight;
        }
        return sum;
    }

    // Public API: show a graph in a Swing window, optionally highlighting a list of edges
    public static void show(Graph g, List<Edge> highlight) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Graph Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Main layout: left control panel, center graph
            JPanel container = new JPanel(new BorderLayout());
            GraphPanel graphPanel = new GraphPanel(g, highlight);
            container.add(graphPanel, BorderLayout.CENTER);

            // Build control bar
            JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
            controls.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

            // node choices - cache node names
            String[] nodeNames = new String[g.nodes.length];
            for (int i = 0; i < g.nodes.length; i++) nodeNames[i] = g.nodes[i].id;
            JComboBox<String> startBox = new JComboBox<>(nodeNames);
            JComboBox<String> endBox = new JComboBox<>(nodeNames);
            controls.add(new JLabel("Start:"));
            controls.add(startBox);
            controls.add(new JLabel("End:"));
            controls.add(endBox);

            JButton findButton = new JButton("Find path");
            controls.add(findButton);

            JLabel status = new JLabel(" ");
            controls.add(status);

            container.add(controls, BorderLayout.NORTH);

            frame.setContentPane(container);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // action: compute path when button pressed
            findButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int si = startBox.getSelectedIndex();
                    int ti = endBox.getSelectedIndex();
                    if (si < 0 || ti < 0) return;

                    Node s = g.nodes[si];
                    Node t = g.nodes[ti];
                    List<Node> pathNodes = g.walk(s, t);
                    List<Edge> pathEdges = getEdgesFromPath(g, pathNodes);

                    graphPanel.setHighlight(pathEdges);

                    if (pathNodes == null || pathNodes.isEmpty()) {
                        status.setText("No path");
                    } else {
                        double distance = calculatePathDistance(pathEdges);
                        status.setText(String.format("Path length: %.2f", distance));
                    }
                }
            });
        });
    }
}
