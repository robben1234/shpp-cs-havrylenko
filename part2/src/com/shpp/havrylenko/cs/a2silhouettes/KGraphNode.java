package com.shpp.havrylenko.cs.a2silhouettes;

import java.util.ArrayList;
import java.util.List;

/**
 * Graph data structure for {@code Point}
 *
 * @author Kyrylo Havrylenko
 * @see Point
 */
class KGraphNode {

    boolean isVisited;
    List<KGraphNode> neighbors;
    Point data;

    KGraphNode(Point point) {
        isVisited = false;
        neighbors = new ArrayList<>();
        data = point;
    }
}
