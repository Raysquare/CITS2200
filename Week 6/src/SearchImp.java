/**
 * A basic Binary Tree Implementation
 *
 * @author Fang Kai Gan 21896665
 * @version 1.0
 * @since April 28 2017
 */


import CITS2200.Graph;
import CITS2200.Search;

import java.util.concurrent.LinkedBlockingDeque;

public class SearchImp implements Search {

    /**
     * Find the parent vertex of each node
     *
     * @param graph to be transversed
     * @param i     starting vertex
     * @return array of int stating the parent vertex
     */
    public int[] getConnectedTree(Graph graph, int i) {

        java.util.Queue<Integer> queue;
        queue = new LinkedBlockingDeque<>();

        int[] output = new int[graph.getNumberOfVertices()];
        int[] color = new int[graph.getNumberOfVertices()];

        for (int j = 0; j < output.length; j++) --output[j];
        for (int j = 0; j < output.length; j++) --color[j];

        // -1 = white, -2 = grey; -3 = black
        queue.offer(i);
        while (!queue.isEmpty()) {
            int w = queue.remove();
            for (int x = 0; x < output.length; x++) {
                if (graph.getWeight(w, x) >= 1) {
                    if (color[x] == -1) {
                        output[x] = w;
                        color[x] = -2;
                        queue.offer(x);
                    }
                }
            }
            color[w] = -3;
        }
        return output;
    }

    /**
     * Find the distance from the starting vertex
     *
     * @param graph to be transversed
     * @param i     starting vertex
     * @return array of int stating the distance of the vertex from the starting vertex, i
     */
    public int[] getDistances(Graph graph, int i) {


        java.util.Queue<Integer> queue;
        queue = new LinkedBlockingDeque<>();

        int[] dist = new int[graph.getNumberOfVertices()];
        int[] color = new int[graph.getNumberOfVertices()];
        for (int j = 0; j < dist.length; j++) --dist[j];
        for (int j = 0; j < color.length; j++) --color[j];

        // -1 = white, -2 = grey; -3 = black
        color[i] = -2;
        dist[i] = 0;
        queue.offer(i);
        while (!queue.isEmpty()) {
            int w = queue.remove();
            for (int x = 0; x < dist.length; x++) {
                if (graph.getWeight(w, x) > 0) {
                    if (color[x] == -1) {
                        dist[x] = dist[w] + 1;
                        color[x] = -2;
                        queue.offer(x);
                    }
                }
            }
            color[w] = -3;
        }
        return dist;
    }

    /**
     * A depth first search, output the start and finish times for each vertex
     *
     * @param graph graph to be transversed
     * @param w     starting vertex
     * @return int[x][y], where x is starting vertex; y = 0 is discovery time and y = 1 is ending time
     */
    public int[][] getTimes(Graph graph, int w) {

        final int[][] output = new int[graph.getNumberOfVertices()][2];
        final int[] color = new int[graph.getNumberOfVertices()];
        for (int j = 0; j < color.length; j++) color[j] = -1;

        class recurse {
            private int time;

            private int[][] runRecursion(Graph graph, int w) {
                color[w] = -2;
                output[w][0] = time;
                time++;
                for (int x = 0; x < output.length; x++) {
                    if (graph.getWeight(w, x) > 0) {
                        if (color[x] == -1) runRecursion(graph, x);
                    }
                }
                color[w] = -3;
                output[w][1] = time;
                time++;
                return output;
            }
        }
        return new recurse().runRecursion(graph, w);
    }

}

