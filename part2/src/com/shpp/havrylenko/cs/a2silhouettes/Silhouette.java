package com.shpp.havrylenko.cs.a2silhouettes;

import com.shpp.havrylenko.cs.a5collections.KArrayList;

/**
 * Util class to save info about silhouette coordinates and form
 *
 * @author Kyrylo Havrylenko
 * @see
 */
class Silhouette {

    KArrayList<Point> points;

    Silhouette() {
        points = new KArrayList<>();
    }
}
