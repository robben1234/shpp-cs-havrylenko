package com.shpp.havrylenko.cs.a2silhouettes;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph data structure for {@code Point}
 *
 * @author Kyrylo Havrylenko
 * @see Point
 */
class GraphNode {

    boolean isVisited;
    List<GraphNode> neighbors;
    Point data;

    GraphNode(Point point) {
        isVisited = false;
        neighbors = new ArrayList<>();
        data = point;
    }
}
