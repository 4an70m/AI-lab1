package kpi.labs.ai.lab1.graph;

import java.util.*;

/**
 * Class designed to perform operations over graph.
 *
 * @see Graph
 * @author Oleksii.Fisher
 * @date 02.21.17
 *
 */
public class BreadthFirstGraphProcessor implements GraphProcessor {

    public void breadthFirstSearchForLongestLink(Graph graph) {
        List<Path> longestPathes = new ArrayList<>();
        for (Node node : graph.getNodes()) {
            longestPathes.addAll(breadthFirstSearchMaxLength(node));
            node.refreshTraverse();
        }
        System.out.println(getLongestPath(longestPathes));
    }

    private List<Path> breadthFirstSearchMaxLength(Node headNode) {
        Queue<Node> queue = new LinkedList<>();
        Map<String, Path> wholePath = new HashMap<>();

        queue.add(headNode);
        while (!queue.isEmpty()) {
            Node node = (Node) queue.remove();
            for ( NodeRelation childRelation : node.traverseRelation() ) {

                String partialKey = childRelation.getParentNode().getValue();
                String fullKey = getFullKey(wholePath.keySet(), partialKey);


                partialKey += childRelation.getChildNode().getValue();
                Path path = wholePath.get(fullKey);
                if (path == null) {
                    path = new Path();
                    path.add(childRelation);
                    wholePath.put(partialKey, path);
                } else {
                    Path newPath = new Path(path);
                    newPath.add(childRelation);
                    wholePath.put(fullKey + partialKey, newPath);
                }

                queue.add(((NodeRelation) childRelation).getChildNode());
            }
        }
        return getLongestPath(wholePath.values());
    }

    private static String getFullKey (Set<String> values, String substring) {
        for (String value : values) {
            if(value.substring(value.length()-1, value.length()).equals(substring)) {
                return value;
            }
        }
        return null;
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
