package kpi.labs.ai.lab1.graph;

import java.util.*;

/**
 * Created by 4an70m on 04.03.2017.
 */
public class BreadthFirstGraphProcessorV2 implements GraphProcessor {

    public void breadthFirstSearchForLongestLink(Graph graph) {
        List<Path> longestPathes = new ArrayList<>();
        for (Node headNode : graph.getNodes()) {
            for (Node nodeTo : graph.getNodes()) {
                longestPathes.addAll(breadthFirstSearchMaxLength(headNode, nodeTo));
                headNode.refreshTraverse();
            }
        }
        System.out.println(getLongestPath(longestPathes));

    }

    private Queue<Path> breadthFirstSearchMaxLength(Node headNode, Node nodeToSearch) {
        PathManager pathes = new PathManager();
        Queue<Node> queue = new LinkedList<>();
        queue.add(headNode);

        while (!queue.isEmpty()) {
            Node node = (Node) queue.remove();
            if (node.equals(nodeToSearch)) {
                return pathes.pathes;
            }
            for ( NodeRelation childRelation : node.traverseRelation() ) {
                pathes.add(childRelation);
                queue.add(childRelation.getChildNode());
            }
        }
        return new LinkedList<Path>();
    }

    public class PathManager {
        public Queue<Path> pathes;

        public PathManager(){
            pathes = new LinkedList<>();
            pathes.add(new Path());
        }

        public void add (NodeRelation relation) {
            boolean isNewPath = false;
            for (Path path : new LinkedList<Path>(pathes)) {
                NodeRelation lastLink = path.getLastPathLink();
                if (lastLink == null) {
                    path.add(relation);
                } else if (lastLink.getChildNode().equals(relation.getParentNode())) {
                    Path newRout = new Path(path);
                    newRout.add(relation);
                    pathes.add(newRout);
                } else {
                    isNewPath = true;
                }
            }
            if (isNewPath) {
                Path newPath = new Path();
                newPath.add(relation);
                pathes.add(newPath);
            }
        }
    }

    private static List<Path> getLongestPath (Collection<Path> paths) {
        List<Path> longestPathList = new ArrayList<>();
        Path longestPath = new Path();
        for (Path path : paths) {
            if (longestPath.getSize() < path.getSize()) {
                longestPath = path;
            }
        }
        for (Path path : paths) {
            if (longestPath.getSize() == path.getSize()) {
                longestPathList.add(path);
            }
        }
        return longestPathList;
    }

}
