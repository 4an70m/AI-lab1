package kpi.labs.ai.lab1.graph;


import java.util.*;

/**
 * Created by 4an70m on 21.02.2017.
 */
public class Path {

    private List<NodeRelation> path;
    private boolean isFinished;

    public Path() {
        this(new ArrayList<>());
    }

    public Path(List<NodeRelation> path) {
        this.path = path;
        this.isFinished = false;
    }

    public Path(Path path) {
        this.path = new ArrayList<>(path.path);
        this.isFinished = path.isFinished;
    }

    public void add(NodeRelation relation) {
        this.path.add(relation);
    }

    public int getSize () {
        return this.path.size();
    }

    public NodeRelation getLastPathLink() {
        if (path.isEmpty()) {
            return null;
        }
        return path.get(path.size() - 1);
    }

    public Boolean isPathValid() {
        PathCounter counter = new PathCounter();
        for (NodeRelation relation : path) {
            Node parentNode = relation.getParentNode();
            Node childNode = relation.getChildNode();
            counter.count(parentNode);
            counter.count(childNode);
            if (counter.twiceOrMore(parentNode) || counter.twiceOrMore(childNode)) {
                return false;
            }
        }
        return true;
    }

    public boolean isFinished() {
        if (this.isFinished) {
            return this.isFinished;
        }
        boolean isFinished = false;
        for (NodeRelation relation : this.path) {
            if (this.containsRelation(relation)) {
                isFinished = true;
                break;
            }
        }
        
        for (int i = 0; i < this.path.size(); i++) {
            NodeRelation orelation = this.path.get(i);
            for (int j = i; j < this.path.size(); j++) {
                NodeRelation irelation = this.path.get(j);
                if (!orelation.equals(irelation)) {
                    if(orelation.getParentNode().equals(irelation.getChildNode())){
                        isFinished = true;
                        break;
                    }
                }
            }
        }
        this.isFinished = isFinished;
        return this.isFinished;
    }
    
    public boolean containsRelation(NodeRelation relation) {
        this.isFinished = (
              Collections.frequency(path, relation)
            + Collections.frequency(path, relation.getReversedRelation())
        ) > 1;
        return this.isFinished;
    }

    private class PathCounter {
        private Map<Node, Integer> counter;
        public PathCounter() {
            this.counter = new HashMap<>();
        }
        public int count(Node node) {
            if (!counter.containsKey(node)) {
                counter.put(node, 0);
            }
            int curCounter = counter.get(node) + 1;
            counter.put(node, curCounter);
            return curCounter;
        }
        public Boolean twiceOrMore(Node node) {
            return counter.get(node) != null
                && counter.get(node) > 2;
        }
        public Boolean onceOrMore(Node node) {
            return counter.get(node) != null
                && counter.get(node) > 1;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.path);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Path other = (Path) obj;
        if (!Objects.equals(this.path, other.path)) {
            return false;
        }
        if (this.isFinished != other.isFinished) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "" + path + "\n";
    }
}
