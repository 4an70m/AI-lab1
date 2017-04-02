package kpi.labs.ai.lab1.graph;

import java.util.*;

/**
 * Class represents a graph.
 * A structure of related nodes, connected by relations.
 *
 * @see Node
 * @see NodeRelation
 * @author Oleksii.Fisher
 * @date 02.19.17
 *
 */
public class Graph {

    private Map<String, Node> nodes;
    private GraphProcessor processor;

    /**
     * Creates an empty graph.
     */
    public Graph() {
        this(new ArrayList<>(), new BreadthFirstGraphProcessorV3());
    }

    /**
     * Creates a graph baseed on the list of nodes.
     *
     * @param nodes
     */
    public Graph(List<Node> nodes) {
        this(nodes, new BreadthFirstGraphProcessorV3());
    }

    public Graph(List<Node> nodes, GraphProcessor processor) {
        this.nodes = new HashMap<>();
        this.nodes.putAll(this.makeMap(nodes));
        this.processor = processor;
    }

    private Map<String, Node> makeMap(List<Node> nodes) {
        Map<String, Node> result = new HashMap<>();
        for (Node node : nodes) {
            result.put(node.getValue().toString(), node);
        }
        return result;
    }

    /**
     * Simple method to add nodes with relation between them.
     *
     * @param nodeAValue
     * @param nodeBValue
     */
    public void addNodesWithRelations (String nodeAValue, String nodeBValue ) {

        Node nodeA = nodes.computeIfAbsent(nodeAValue, k -> new Node(nodeAValue));
        Node nodeB = nodes.computeIfAbsent(nodeBValue, k -> new Node(nodeBValue));
        nodeA.addUnidirectionalRelation(nodeB);
    }

    public List<Node> getNodes() {
        return new ArrayList<Node>(nodes.values());
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = this.makeMap(nodes);
    }

    public Boolean isEmpty() {
        return nodes.isEmpty();
    }

    public Node getNodeByValue(Integer value) {
        return nodes.get(value.toString());
    }

    @Override
    public String toString() {
        return "Graph{\n" +
                nodes.values() +
                "\n}";
    }

    /**
     * Prints the Graph.
     */
    public void display() {
        System.out.println(this);
    }

    public int getMaximumLengthLink () {
        this.processor.breadthFirstSearchForLongestLink(this);
        return 0;
    }
}
