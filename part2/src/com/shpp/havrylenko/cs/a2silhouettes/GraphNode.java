package com.shpp.havrylenko.cs.a2silhouettes;

import java.util.ArrayList;
import java.util.List;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
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
