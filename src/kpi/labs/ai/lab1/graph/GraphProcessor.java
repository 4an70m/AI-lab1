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
public class GraphProcessor {

    public void breadthFirstSearchForLongestLink(Graph graph) {
        int maxLength = 0;
        for (Node node : graph.getNodes()) {
            int curentNodeLength = 0;
            curentNodeLength = breadthFirstSearchMaxLength(node);
            if (curentNodeLength > maxLength) {
                maxLength = curentNodeLength;
            }
            node.refreshTraverse();
        }
        System.out.println(maxLength);
    }

    private int breadthFirstSearchMaxLength(Node headNode) {
        int i = 0;
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
        System.out.println(getLongestPath(wholePath));
        return 0;
    }

    private static String getFullKey (Set<String> values, String substring) {
        for (String value : values) {
            if(value.substring(value.length()-1, value.length()).equals(substring)) {
                return value;
            }
        }
        return null;
    }

    private static List<Path> getLongestPath (Map<String, Path> pathMap) {
        List<Path> longestPathList = new ArrayList<>();
        Path longestPath = new Path();
        for (Path path : pathMap.values()) {
            if (longestPath.getSize() < path.getSize()) {
                longestPath = path;
            }
        }
        for (Path path : pathMap.values()) {
            if (longestPath.getSize() == path.getSize()) {
                longestPathList.add(path);
            }
        }
        return longestPathList;
    }
}
