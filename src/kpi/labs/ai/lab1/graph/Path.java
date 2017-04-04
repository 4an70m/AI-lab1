package kpi.labs.ai.lab1.graph;

import com.sun.org.apache.xpath.internal.operations.Bool;

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

    public boolean containsRelation(NodeRelation relation) {
        this.isFinished = (
              Collections.frequency(path, relation)
            + Collections.frequency(path, relation.getReversedRelation())
        ) > 1;
        return this.isFinished;
    }

    public Boolean isPathValid() {
        PathCounter counter = new PathCounter();
        for (NodeRelation relation : path) {
            Node parentNode = relation.getParentNode();
            Node childNode = relation.getChildNode();
            counter.count(parentNode);
            counter.count(childNode);
            if (counter.twiceMore(parentNode) || counter.twiceMore(childNode)) {
                return false;
            }
        }
        return true;
    }

    public boolean isFinished() {
        boolean isFinished = false;
        for (NodeRelation relation : this.path) {
            if (this.containsRelation(relation)) {
                isFinished = true;
                break;
            }
        }
        return this.isFinished || isFinished;
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
        public Boolean twiceMore(Node node) {
            return counter.get(node) != null
                && counter.get(node) > 2;
        }
    }

    @Override
    public String toString() {
        return "" + path + "\n";
    }
}
