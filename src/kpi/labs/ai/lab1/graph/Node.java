package kpi.labs.ai.lab1.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class represents a single Node in a graph.
 *
 * @see Graph
 * @author Oleksii.Fisher
 * @date 02.19.17
 *
 */
public class Node {

    private String value;
    private List<NodeRelation> relations;

    public Node(String value) {
        this.value = value;
        this.relations = new LinkedList<NodeRelation>();
    }

    public String getValue() {
        return value;
    }

    public List<NodeRelation> getRelations() {
        return relations;
    }

    public List<NodeRelation> traverseRelation() {
        List<NodeRelation> result = new ArrayList<>();
        for (NodeRelation relation : relations) {
            if (!relation.getPassed()) {
                NodeRelation reversedRelation
                        = relation.getChildNode().findReversedRelation(relation);
                reversedRelation.setPassed(true);
                relation.setPassed(true);
                result.add(relation);
            }
        }
        return result;
    }

    public NodeRelation findReversedRelation(NodeRelation relation) {
        NodeRelation reversedTempRelation
                = new NodeRelation(relation.getChildNode(), relation.getParentNode());
        int indexOfReversedRelation = relations.indexOf(reversedTempRelation);
        return this.relations.get(indexOfReversedRelation);
    }


    @Override
    public String toString() {
        String result = "";
        if (this.relations != null) {
            result  += " " + this.relations + "\n";
        }

        return result;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setRelations(List<NodeRelation> relations) {
        this.relations = relations;
    }

    /**
     * Adds a two way relation between two nodes.
     * NodeRelation's are added to both this Node
     * and the other node, but in other direction.
     * Example: Node1: A--B, Node2: B--A
     *
     * @param newRelation
     * @see NodeRelation
     * @see Node
     */
    public void addUnidirectionalRelation(Node newRelation) {
        NodeRelation parentChildRelation = new NodeRelation(this, newRelation);
        NodeRelation childParentRelation = new NodeRelation(newRelation, this);
        this.relations.add(parentChildRelation);
        newRelation.relations.add(childParentRelation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (value != null ? !value.equals(node.value) : node.value != null) return false;
        return relations != null ? relations.equals(node.relations) : node.relations == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result;
        return result;
    }

    /**
     * Prints the Node.
     */

    public void display() {
        System.out.println(this);
    }

    public void refreshTraverse() {
        for (NodeRelation relation : relations) {
            relation.refreshCounter();
            if (!relation.getPassed()) {
                continue;
            }
            relation.setPassed(false);
            relation.getChildNode().refreshTraverse();
        }
    }
}
