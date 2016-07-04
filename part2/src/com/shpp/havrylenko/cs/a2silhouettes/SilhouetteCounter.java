package com.shpp.havrylenko.cs.a2silhouettes;

import com.shpp.havrylenko.cs.a5collections.KLinkedList;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.shpp.havrylenko.cs.a2silhouettes.Point.generatePointOutOfCoords;

/**
 * Counts silhouettes on user provided pictures
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class SilhouetteCounter {

    private static final int BIG_SIZE = 85;
    private static BufferedImage image;
    private static int w;
    private static int h;
    private KLinkedList<Silhouette> silhouettes = new KLinkedList<>();
    private KGraphNode root;
    private KLinkedList<KGraphNode> graph = new KLinkedList<>();

    /**
     * Entry point of program
     * @param args console arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        if (args.length != 3) {
            System.out.println("USAGE: java SilhouetteCounter <filename>");
            System.out.println("<filename>: URL of picture to count silhouettes in");
            return;
        }

        SilhouetteCounter silhouetteCounter = new SilhouetteCounter();
        image = ImageIO.read(silhouetteCounter.getClass().getResource(args[2]));

        if (image.getWidth() > BIG_SIZE || image.getHeight() > BIG_SIZE)
            image = silhouetteCounter.resizeBigImage(image);

        w = image.getWidth();
        h = image.getHeight();

        silhouetteCounter.generateGraphOutOfImage(image);
        silhouetteCounter.lookForBlack(silhouetteCounter.root, new Silhouette());

        System.out.println("Results: silhouette counter = " + silhouetteCounter.silhouettes.size());

    }

    /**
     * Resizes user provided big picture to smaller size
     * @param image BufferedImage picture to resize
     * @return BufferedImage resized picture
     */
    private BufferedImage resizeBigImage(BufferedImage image) {

        // new BufferedImage
        BufferedImage resized = new BufferedImage(BIG_SIZE, BIG_SIZE, BufferedImage.TYPE_3BYTE_BGR);
        // create Graphics2D to draw into resized
        Graphics2D g2 = resized.createGraphics();
        // render resized image
        g2.drawImage(image, 0, 0, BIG_SIZE, BIG_SIZE, null);
        // destroy context and free resources
        g2.dispose();

        return resized;

    }

    /**
     * Finds vertex node in graph created from pixel array
     * @param xy Point with needed coordinates
     * @return KGraphNode with needed coordinates
     */
    private KGraphNode findNodeByCoords(Point xy) {

        for (KGraphNode node : graph) {
            if (node.data.x == xy.x && node.data.y == xy.y)
                return node;
        }

        return null;

    }

    /**
     * Generates graph out of BufferedImage (pixel array)
     * @param image BufferedImage image to graph
     */
    private void generateGraphOutOfImage(BufferedImage image) {

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Point xy = generatePointOutOfCoords(x, y, image);
                KGraphNode node = new KGraphNode(xy);
                graph.add(node);
            }
        }

        graph.forEach(this::findNeighbors);
        root = graph.get(0);

    }

    /**
     * Finds neighbors of vertex
     * @param node KGraphNode vertex
     */
    private void findNeighbors(KGraphNode node) {

        Point curCoords = node.data;

        if (curCoords.x > 0)
            node.neighbors.add(findNodeByCoords(generatePointOutOfCoords(curCoords.x - 1, curCoords.y, image)));
        if (curCoords.x < w - 1)
            node.neighbors.add(findNodeByCoords(generatePointOutOfCoords(curCoords.x + 1, curCoords.y, image)));
        if (curCoords.y > 0)
            node.neighbors.add(findNodeByCoords(generatePointOutOfCoords(curCoords.x, curCoords.y - 1, image)));
        if (curCoords.y < h - 1)
            node.neighbors.add(findNodeByCoords(generatePointOutOfCoords(curCoords.x, curCoords.y + 1, image)));

    }

    /**
     * Searches graph for silhouettes (via black pixels). Uses depth-first search.
     * @param root
     * @param silhouette
     */
    private void lookForBlack(KGraphNode root, Silhouette silhouette) {

        root.isVisited = true;

        if (isBlack(root.data.argb)) {
            silhouette.points.add(root.data);

            for (int i = 0; i < root.neighbors.size(); i++) {
                KGraphNode node = root.neighbors.get(i);
                if (node.isVisited)
                    continue;
                node.isVisited = true;
                if (isBlack(node.data.argb)) {

                    silhouette.points.add(node.data);
                    lookForBlack(node, silhouette);
                }
            }
        }

        if (silhouette.points != null && silhouette.points.size() > 0) {
            if (!silhouettes.contains(silhouette))
                silhouettes.add(silhouette);
        }

        for (int i = 0; i < root.neighbors.size(); i++) {
            KGraphNode node = root.neighbors.get(i);
            if (!node.isVisited)
                lookForBlack(node, new Silhouette());

        }

    }

    /**
     * Checks if pixel's black
     * @param argb argb of pixel
     * @return Boolean true if it's black
     */
    private boolean isBlack(Argb argb) {

        final int notBlack = 160;
        final int diff = 10;

        boolean separately = argb.red < notBlack && argb.green < notBlack && argb.blue < notBlack;

        if (separately) {
            boolean red = argb.red - argb.blue < diff && argb.red - argb.green < diff;
            boolean green = argb.green - argb.blue < diff && argb.green - argb.red < diff;
            boolean blue = argb.blue - argb.red < diff && argb.blue - argb.green < diff;
            if (red && green && blue)
                return true;
        }

        return false;

    }

}
