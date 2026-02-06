package self_exercises.dijkstra;// java
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class GraphPanel extends JPanel {
    private final Graph graph;
    private static final int NODE_RADIUS = 20;
    private static final int ARROW_LENGTH = 18;
    private static final int ARROW_WIDTH = 10;
    private final Map<Node, Point> layout = new HashMap<>();
    private final Set<Edge> highlightEdges = new HashSet<>();
    private final Set<Node> highlightNodes = new HashSet<>();

    public GraphPanel(Graph graph) {
        this(graph, null);
    }

    // new constructor: optionally provide edges that should be highlighted (e.g. shortest path)
    public GraphPanel(Graph graph, List<Edge> highlight) {
        this.graph = graph;
        setHighlightInternal(highlight);
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        initLayout();
    }

    // Update the highlighted edges (and implied nodes) at runtime
    public void setHighlight(List<Edge> highlight) {
        setHighlightInternal(highlight);
        repaint();
    }

    private void setHighlightInternal(List<Edge> highlight) {
        highlightEdges.clear();
        highlightNodes.clear();
        if (highlight != null) {
            for (Edge e : highlight) {
                if (e != null) {
                    highlightEdges.add(e);
                    highlightNodes.add(e.from);
                    highlightNodes.add(e.to);
                }
            }
        }
    }

    private void initLayout() {
        if (graph.nodes == null || graph.nodes.length == 0) return;

        // check if nodes already have coordinates (non-zero)
        boolean hasCoords = false;
        for (Node n : graph.nodes) {
            if (n.x != 0 || n.y != 0) {
                hasCoords = true;
                break;
            }
        }

        Dimension size = getPreferredSize();
        int cx = size.width / 2;
        int cy = size.height / 2;
        int radius = Math.min(size.width, size.height) / 2 - 60;
        int n = graph.nodes.length;
        double angleStep = 2 * Math.PI / n;

        for (int i = 0; i < n; i++) {
            Node node = graph.nodes[i];
            if (hasCoords) {
                layout.put(node, new Point((int) node.x, (int) node.y));
            } else {
                double angle = angleStep * i;
                int x = cx + (int) (radius * Math.cos(angle));
                int y = cy + (int) (radius * Math.sin(angle));
                node.x = x;
                node.y = y;
                layout.put(node, new Point(x, y));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (graph == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw edges
        if (graph.edges != null) {
            for (Edge e : graph.edges) {
                drawEdge(g2, e);
            }
        }

        // draw nodes
        if (graph.nodes != null) {
            for (Node node : graph.nodes) {
                drawNode(g2, node);
            }
        }
    }

    private void drawEdge(Graphics2D g2, Edge e) {
        Point a = layout.get(e.from);
        Point b = layout.get(e.to);
        if (a == null || b == null) return;

        // compute points that lie on the boundary of the node circles
        double dx = b.x - a.x;
        double dy = b.y - a.y;
        double dist = Math.hypot(dx, dy);
        if (dist < 1e-6) return;

        double ux = dx / dist;
        double uy = dy / dist;

        // start point: move out from source center by NODE_RADIUS
        int sx = (int) Math.round(a.x + ux * NODE_RADIUS);
        int sy = (int) Math.round(a.y + uy * NODE_RADIUS);
        // end point: move back from target center by NODE_RADIUS
        int ex = (int) Math.round(b.x - ux * NODE_RADIUS);
        int ey = (int) Math.round(b.y - uy * NODE_RADIUS);

        boolean isHighlighted = highlightEdges.contains(e);
        Color edgeColor = isHighlighted ? Color.BLUE : Color.LIGHT_GRAY;

        // Draw line
        g2.setColor(edgeColor);
        if (isHighlighted) {
            Stroke oldStroke = g2.getStroke();
            g2.setStroke(new BasicStroke(3.0f));
            g2.drawLine(sx, sy, ex, ey);
            g2.setStroke(oldStroke);
        } else {
            g2.drawLine(sx, sy, ex, ey);
        }

        // draw arrowhead at the end (pointing to 'to')
        drawArrowhead(g2, ex, ey, ux, uy, isHighlighted ? Color.BLUE : Color.DARK_GRAY);

        // draw weight label at midpoint
        drawWeightLabel(g2, e.weight, sx, sy, ex, ey);
    }

    private void drawArrowhead(Graphics2D g2, int tipX, int tipY, double ux, double uy, Color color) {
        // base point of arrowhead
        double bx = tipX - ux * ARROW_LENGTH;
        double by = tipY - uy * ARROW_LENGTH;

        // perpendicular for width
        double px = -uy;
        double hw = ARROW_WIDTH / 2.0; // half width

        int[] ax = {
            tipX,
            (int) Math.round(bx + px * hw),
            (int) Math.round(bx - px * hw)
        };
        int[] ay = {
            tipY,
            (int) Math.round(by + ux * hw),
            (int) Math.round(by - ux * hw)
        };

        g2.setColor(color);
        g2.fillPolygon(ax, ay, 3);
    }

    private void drawWeightLabel(Graphics2D g2, double weight, int sx, int sy, int ex, int ey) {
        String w = String.format("%.1f", weight);
        int mx = (sx + ex) / 2;
        int my = (sy + ey) / 2;
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(w, mx + 6, my - 6);
    }

    private void drawNode(Graphics2D g2, Node node) {
        Point p = layout.get(node);
        if (p == null) return;

        int x = p.x - NODE_RADIUS;
        int y = p.y - NODE_RADIUS;
        int diameter = NODE_RADIUS * 2;

        // Fill node
        Color fillColor = highlightNodes.contains(node)
            ? new Color(135, 206, 250)  // light sky blue
            : Color.ORANGE;
        g2.setColor(fillColor);
        g2.fillOval(x, y, diameter, diameter);

        // Draw border
        g2.setColor(Color.DARK_GRAY);
        g2.drawOval(x, y, diameter, diameter);

        // Draw label centered
        String label = node.label != null ? node.label : node.id;
        FontMetrics fm = g2.getFontMetrics();
        int lw = fm.stringWidth(label);
        int lh = fm.getAscent();
        g2.drawString(label, p.x - lw / 2, p.y + lh / 2 - 2);
    }
}
