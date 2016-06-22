package com.shpp.havrylenko.cs.a2silhouettes;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.shpp.havrylenko.cs.a2silhouettes.Point.generatePointOutCoords;

/**
 * <what class do>
 *
 * @author Kyrylo Havrylenko
 * @see
 */
public class SilhouetteCounter {

    static final int BIG_SIZE = 85;
    static BufferedImage image;
    static int w;
    static int h;
    List<Silhouette> silhouettes = new LinkedList<>();
    GraphNode root;
    List<GraphNode> graph = new LinkedList<>();

    public static void main(String[] args) throws IOException {

//                                image = ImageIO.read(SilhouetteCounter.class.getResource("male-silhouette.jpg"));
//                        image = ImageIO.read(SilhouetteCounter.class.getResource("many.jpg"));
//        image = ImageIO.read(SilhouetteCounter.class.getResource("large.png"));
//        image = ImageIO.read(SilhouetteCounter.class.getResource("sil.png"));
//                        image = ImageIO.read(SilhouetteCounter.class.getResource("many.png"));
                image = ImageIO.read(SilhouetteCounter.class.getResource("manymany.png"));
        image = ImageIO.read(SilhouetteCounter.class.getResource("six.jpg"));
        SilhouetteCounter m = new SilhouetteCounter();
        boolean isBig = image.getWidth() > BIG_SIZE || image.getHeight() > BIG_SIZE;
        if (isBig)
            image = m.resizeBigImage(image);
        File output = new File("image.jpg");
        ImageIO.write(image, "jpg", output);

        w = image.getWidth();
        h = image.getHeight();

        m.generateGraphOutOfImage(image);
        m.lookForBlack(m.root, new Silhouette());
        System.out.println(m.silhouettes.size());

    }

    private BufferedImage resizeBigImage(BufferedImage image) {
        BufferedImage resized = new BufferedImage(BIG_SIZE, BIG_SIZE, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2 = resized.createGraphics();
        g2.drawImage(image, 0, 0, BIG_SIZE, BIG_SIZE, null);
        g2.dispose();
        return resized;
    }

    private GraphNode findNodeByCoords(Point xy) {

        for (GraphNode node : graph) {
            if (node.data.x == xy.x && node.data.y == xy.y)
                return node;
        }

        return null;
    }

    private void generateGraphOutOfImage(BufferedImage image) {

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Point xy = generatePointOutCoords(x, y, image);
                GraphNode node = new GraphNode(xy);
                graph.add(node);
            }
        }

        graph.forEach(this::findNeighbors);

        root = graph.get(0);
    }

    private void findNeighbors(GraphNode node) {

        Point curCoords = node.data;

        if (curCoords.x > 0)
            node.neighbors.add(findNodeByCoords(generatePointOutCoords(curCoords.x - 1, curCoords.y, image)));
        if (curCoords.x < w - 1)
            node.neighbors.add(findNodeByCoords(generatePointOutCoords(curCoords.x + 1, curCoords.y, image)));
        if (curCoords.y > 0)
            node.neighbors.add(findNodeByCoords(generatePointOutCoords(curCoords.x, curCoords.y - 1, image)));
        if (curCoords.y < h - 1)
            node.neighbors.add(findNodeByCoords(generatePointOutCoords(curCoords.x, curCoords.y + 1, image)));
    }

    private void lookForBlack(GraphNode root, Silhouette silhouette) {

        root.isVisited = true;
        if (isBlack(root.data.argb)) {
            silhouette.points.add(root.data);

            for (int i = 0; i < root.neighbors.size(); i++) {
                GraphNode node = root.neighbors.get(i);
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
            GraphNode node = root.neighbors.get(i);
            if (!node.isVisited)
                lookForBlack(node, new Silhouette());

        }
    }

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
