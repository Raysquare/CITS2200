import CITS2200.Graph;
import CITS2200.Path;

/**
 * Simple Minimum spanning tree finder and single source shortest path finder
 *
 * @author Fang Kai Gan 21896665
 * @version 1.0
 * @since May 5 2017
 */
public class PathImp implements Path {

    /**
     * Finds and output the weight of a minimum spanning tree
     *
     * @param graph the graph to transverse
     * @return weight of the minimum spanning tree, -1 if there isn't
     */
    public int getMinSpanningTree(Graph graph) {

        int totalEdgeWeight = 0;
        int numberOfVertices = graph.getNumberOfVertices();

        java.util.PriorityQueue<nodes> priorityQueue = new java.util.PriorityQueue<>();
        boolean[] visited = new boolean[numberOfVertices];

        priorityQueue.add(new nodes(0, 0));
        while (!priorityQueue.isEmpty()) {
            nodes current = priorityQueue.remove();
            if (!visited[current.getVertex()]) {
                visited[current.getVertex()] = true;
                for (int x = 0; x < numberOfVertices; x++) {
                    if (graph.getWeight(x, current.getVertex()) > 0 && !visited[x])
                        priorityQueue.add(new nodes(x, graph.getWeight(current.getVertex(), x)));
                }
                totalEdgeWeight += current.getWeight();
            }
        }
        for (int i = 0; i < numberOfVertices; i++) if (!visited[i]) return -1;
        return totalEdgeWeight;
    }


    /**
     * Uses Dijkstra's algorithm to return an array of the distances from the specified start vertex to each of the vertices in the graph.
     *
     * @param graph Graph to be transversed
     * @param i     starting index
     * @return int array of distances from the starting index
     */
    public int[] getShortestPaths(Graph graph, int i) {
        int numberOfVertices = graph.getNumberOfVertices();
        java.util.PriorityQueue<nodes> priorityQueue = new java.util.PriorityQueue<>();
        boolean[] visited = new boolean[numberOfVertices];
        int[] tree = new int[numberOfVertices];


        priorityQueue.add(new nodes(i, 0));
        while (!priorityQueue.isEmpty()) {
            nodes current = priorityQueue.remove();
            if (!visited[current.getVertex()]) {
                visited[current.getVertex()] = true;
                for (int x = 0; x < numberOfVertices; x++) {
                    if (graph.getWeight(current.getVertex(), x) != 0 && !visited[x])
                        priorityQueue.add(new nodes(x, graph.getWeight(current.getVertex(), x) + current.getWeight()));
                }
                tree[current.getVertex()] = current.getWeight();
            }
        }
        return tree;
    }

    class nodes implements Comparable<nodes> {
        int vertex;
        int weight;

        nodes(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        int getVertex() {
            return vertex;
        }

        int getWeight() {
            return weight;
        }


        @Override
        public int compareTo(nodes x) {
            return Integer.compare(weight, x.getWeight());
        }
    }
}