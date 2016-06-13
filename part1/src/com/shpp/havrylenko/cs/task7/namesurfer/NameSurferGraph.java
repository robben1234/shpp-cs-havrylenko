package com.shpp.havrylenko.cs.task7.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GLine;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    private List<NameSurferEntry> entryList = new ArrayList<>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);

    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entryList.clear();
        update();
    }

	
	/* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {
        entryList.add(entry);
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawGrid();
        drawGraph();
    }

    private void drawGrid() {

        for (int i = 0; i < NDECADES; i++) {

            int fromX = i * (getWidth() / NDECADES);
            int fromY = 0;
            int toX = i * (getWidth() / NDECADES);
            int toY = getHeight() - 2;

            // vlines
            GLine line = new GLine(fromX, fromY, toX, toY);
            add(line);

            // years
            GLabel year = new GLabel("" + (START_DECADE + (10 * i)));
            year.setFont(new Font("Arial", 0, GRAPH_MARGIN_SIZE - 1));
            add(year, fromX, toY);

        }

        // hlines
        GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
        add(topLine);
        GLine bottomLine = new GLine(0,
                                     getHeight() - GRAPH_MARGIN_SIZE,
                                     getWidth(),
                                     getHeight() - GRAPH_MARGIN_SIZE);
        add(bottomLine);
    }

    private void drawGraph() {
        Color[] colors = {
                Color.BLUE,
                Color.RED,
                Color.MAGENTA,
                Color.BLACK
        };

        final int[] i = {0};
        entryList.forEach((p) -> drawCurrentGraph(p, colors[i[0]++ % colors.length]));
    }

    private void drawCurrentGraph(NameSurferEntry entry, Color color) {

        int lineFromX = 0;
        int lineFromY = getCurY(entry.getRank(0));

        for (int i = 0; i < NDECADES; i++) {

            int lineToX = i * (getWidth() / NDECADES);
            int lineToY = getCurY(entry.getRank(i));

            GLabel name = new GLabel(entry.getName() + " " + getRankString(entry.getRank(i)));
            name.setColor(color);
            add(name, lineToX, lineToY);

            if(i > 0) {
                GLine line = new GLine(lineFromX, lineFromY, lineToX, lineToY);
                line.setColor(color);
                add(line);
            }

            lineFromX = lineToX;
            lineFromY = lineToY;
        }
    }

    /**
     * Gets Y-coord for graph out of rank
     * @param rank rank of name in db
     * @return int Y-coord
     */
    private int getCurY(int rank) {

        return (rank == 0)
                ? getHeight() - GRAPH_MARGIN_SIZE
                : GRAPH_MARGIN_SIZE + (rank * (getHeight() - GRAPH_MARGIN_SIZE * 2)) / MAX_RANK;
    }

    /**
     * Gets rank string (* if bad, rank if ok)
     * @param rank rank of name in db
     * @return String rank string
     */
    private String getRankString(int rank) {

        return (rank != 0)
                ? "" + rank
                : "*";
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }

    public void componentResized(ComponentEvent e) {
        update();
    }

    public void componentShown(ComponentEvent e) {
    }
}
