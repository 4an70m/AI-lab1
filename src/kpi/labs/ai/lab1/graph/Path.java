package kpi.labs.ai.lab1.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public boolean isFinished() {
        for (NodeRelation relation : this.path) {
            if (this.containsRelation(relation)) {
                this.isFinished = true;
                break;
            }
        }
        return this.isFinished;
    }

    @Override
    public String toString() {
        return "" + path + "\n";
    }
}
