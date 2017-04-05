package kpi.labs.ai.lab1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kpi.labs.ai.lab1.graph.Graph;
import kpi.labs.ai.lab1.graph.Node;
import kpi.labs.ai.lab1.graph.NodeRelation;
import kpi.labs.ai.lab1.graph.Path;

/**
 * Created by 4an70m on 20.02.2017.
 */
public class Main {
    public static void main (String[] args) {
        Graph graph = new Graph();

//        graph.addNodesWithRelations("1", "2");
//        graph.addNodesWithRelations("1", "3");
//        graph.addNodesWithRelations("1", "4");
//        graph.addNodesWithRelations("2", "5");
//        graph.addNodesWithRelations("3", "6");
//        graph.addNodesWithRelations("3", "7");
//        graph.addNodesWithRelations("4", "8");
//        graph.addNodesWithRelations("5", "9");
//
//        graph.addNodesWithRelations("4", "2");
//        graph.addNodesWithRelations("2", "1");
//        graph.addNodesWithRelations("2", "3");
//        graph.addNodesWithRelations("1", "5");
//        graph.addNodesWithRelations("3", "5");
        
//        graph.addNodesWithRelations("1", "2");
//        graph.addNodesWithRelations("2", "3");
//        graph.addNodesWithRelations("2", "4");
//        graph.addNodesWithRelations("3", "5");
//        graph.addNodesWithRelations("4", "5");
//        graph.addNodesWithRelations("4", "6");
//        graph.addNodesWithRelations("5", "6");
//        graph.addNodesWithRelations("6", "7");


        graph.display();
        graph.getMaximumLengthLink();
        
    }
}

