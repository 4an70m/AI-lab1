package kpi.labs.ai.lab1.graph;


 /**
 * Class represents a relation between two nodes in a graph.
 *
 * @see Node
 * @see Graph
 * @author Oleksii.Fisher
 * @date 02.20.17
 *
 */
public class NodeRelation {

    private Node parentNode;
    private Node childNode;
    private Boolean isPassed;

    public NodeRelation(Node parentNode, Node childNode) {
        this.parentNode = parentNode;
        this.childNode = childNode;
        this.isPassed = false;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public Node getChildNode() {
        return childNode;
    }

     public Boolean getPassed() {
         return isPassed;
     }

     public void setPassed(Boolean passed) {
         isPassed = passed;
     }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeRelation that = (NodeRelation) o;

        if (parentNode != null ? !parentNode.equals(that.parentNode) : that.parentNode != null) return false;
        return childNode != null ? childNode.equals(that.childNode) : that.childNode == null;
    }

    @Override
    public int hashCode() {
        int result = parentNode != null ? parentNode.hashCode() : 0;
        result = 31 * result + (childNode != null ? childNode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "<" +
                parentNode.getValue() +
                ">==<" + childNode.getValue() +
                ">";
    }
}
