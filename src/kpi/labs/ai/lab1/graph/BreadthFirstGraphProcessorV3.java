package kpi.labs.ai.lab1.graph;

import java.util.*;

/**
 * Created by 4an70m on 02.04.2017.
 */
public class BreadthFirstGraphProcessorV3 implements GraphProcessor{

    public void breadthFirstSearchForLongestLink(Graph graph) {
        List<Path> longestPathes = new ArrayList<>();
        for (Node headNode : graph.getNodes()) {
            longestPathes.addAll(breadthFirstSearchMaxLength(headNode));
            headNode.refreshTraverse();
        }
        System.out.println(getLongestPath(longestPathes));

    }

    private Queue<Path> breadthFirstSearchMaxLength(Node headNode) {
        PathManager pathManager = new PathManager();
        Queue<Node> agenda = new LinkedList<>();
        agenda.add(headNode);

        while (!agenda.isEmpty()) {
            Node node = (Node) agenda.remove();
            for (NodeRelation childRelation : node.traverseRelation()) {

                List<Path> selectedPaths = pathManager.add(childRelation);
                for (Path path : selectedPaths) {
                    if (!path.isFinished()) {
                        agenda.add(childRelation.getChildNode());
                    }
                }

            }
        }
        return pathManager.getPathes();
    }

    public class PathManager {
        private Queue<Path> pathes;

        public PathManager(){
            pathes = new LinkedList<>();
        }

        public List<Path> add (NodeRelation relation) {
            List<Path> selectedPaths = new ArrayList<>();
            if (pathes.isEmpty()) {
                Path newPath = new Path();
                newPath.add(relation);
                pathes.add(newPath);
                selectedPaths.add(newPath);
                return selectedPaths;
            }
            for (Path path : new LinkedList<Path>(pathes)) {
                if (path.isFinished()) {
                    continue;
                }

                NodeRelation lastLink = path.getLastPathLink();
                if (lastLink.getChildNode().equals(relation.getParentNode())) {
                    Path newPath = new Path(path);
                    newPath.add(relation);
                    if (path.isFinished()) {
                        continue;
                    }
                    selectedPaths.add(newPath);
                    pathes.add(newPath);
                }
            }
            return selectedPaths;
        }

        public Queue<Path> getPathes() {
            Queue<Path> result = new LinkedList<Path>();
            for (Path path : this.pathes) {
                if(path.isPathValid()) {
                    result.add(path);
                }
            }
            return result;
        }
    }

    private static List<Path> getLongestPath (Collection<Path> listpaths) {
        Set<Path> paths = new HashSet<>(listpaths);
        List<Path> longestPathList = new ArrayList<>();
        Path longestPath = new Path();
        for (Path path : paths) {
            if (path.isFinished()) {
                continue;
            }
            if (longestPath.getSize() < path.getSize()) {
                longestPath = path;
            }
        }
        for (Path path : paths) {
            if (path.isFinished()) {
                continue;
            }
            if (longestPath.getSize() == path.getSize()) {
                longestPathList.add(path);
            }
        }
        return longestPathList;
    }

}
