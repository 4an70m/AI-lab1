package kpi.labs.ai.lab1.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 4an70m on 21.02.2017.
 */
public class Path {

    private List<NodeRelation> path;

    public Path() {
        this(new ArrayList<>());
    }

    public Path(List<NodeRelation> path) {
        this.path = path;
    }

    public Path(Path path) {
        this.path = new ArrayList<>(path.path);
    }

    public void add(NodeRelation relation) {
        this.path.add(relation);
    }

    public int getSize () {
        return this.path.size();
    }

    @Override
    public String toString() {
        return "" + path + "\n";
    }
}
