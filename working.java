//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.concurrent.LinkedBlockingQueue;
//
//
//public class MyCITS2200Project implements CITS2200Project {
//    private Graph fullGraph = new Graph();
//    private int[][] intArray;
//
//    /**
//     * Adds an edge to the Wikipedia page graph. If the pages do not
//     * already exist in the graph, they will be added to the graph.
//     *
//     * @param urlFrom the URL which has a link to urlTo.
//     * @param urlTo   the URL which urlFrom has a link to.
//     */
//    public void addEdge(String urlFrom, String urlTo) {
//        fullGraph.addVertex(urlFrom, urlTo);
//        intArray = fullGraph.getGraphAsInt();
//    }
//
//    /**
//     * Finds the shortest path in number of links between two pages.
//     * If there is no path, returns -1.
//     *
//     * @param urlFrom the URL where the path should start.
//     * @param urlTo   the URL where the path should end.
//     * @return the length of the shortest path in number of links followed.
//     */
//    public int getShortestPath(String urlFrom, String urlTo) {
//
//        int from = fullGraph.getVertexIndex(urlFrom);
//        int to = fullGraph.getVertexIndex(urlTo);
//
//        // Check if urlFrom and urlTo are legal
//        if (from == -1 || to == -1) {
//            System.out.println("No such source/destination URL!");
//            return -1;
//        }
//
//
//        // If urlFrom and urlTo are the same page, return 0
//        if (from == to) return 0;
//
//        int[] visited = new int[intArray.length];
//        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
//        queue.offer(from);
//        int[] dist = new int[intArray.length];
//
//        while (!queue.isEmpty()) {
//            int current = queue.remove();
//            for (int i = 0; i < intArray.length; i++) {
//                if (fullGraph.isConnected(current, i) && visited[i] == 0) {
//                    dist[i] = dist[current] + 1;
//                    visited[i] = -2;
//                    queue.offer(i);
//                }
//            }
//            visited[current] = -3;
//        }
//
//        if (dist[to] == 0) return -1;
//
//        return dist[to];
//
//    }
//
//    /**
//     * print array
//     */
//    public void print() {
//        for (int[] row : intArray) {
//            for (int i : row) System.out.print(i + "\t");
//            System.out.println();
//        }
//    }
//
//    /**
//     * Finds a Hamiltonian path in the page graph. There may be many
//     * possible Hamiltonian paths. Any of these paths is a correct output.
//     * This method should never be called on a graph with more than 20
//     * vertices. If there is no Hamiltonian path, this method will
//     * return an empty array. The output array should contain the URLs of pages
//     * in a Hamiltonian path. The order matters, as the elements of the
//     * array represent this path in sequence. So the element [0] is the start
//     * of the path, and [1] is the next page, and so on.
//     *
//     * @return a Hamiltonian path of the page graph.
//     */
//    public String[] getHamiltonianPath() {
//        HamiltonianCycle hc = new HamiltonianCycle();
//        return hc.findHamiltonianCycle(intArray);
//    }
//
//    public String toString() {
//        return fullGraph.toString();
//    }
//
//    private class Graph {
//        ArrayList<Vertex> vertices = new ArrayList<>();
//        private int[][] array;
//
//        /**
//         * If parent does not exist, create parent
//         * If child does not exist, create child and then create new adjacent node at parent
//         *
//         * @param parent OriginUrl
//         * @param child  DestinationUrl
//         */
//        void addVertex(String parent, String child) {
//            if (getVertexIndex(parent) == -1) vertices.add(new Vertex(parent));
//            if (getVertexIndex(child) == -1) vertices.add(new Vertex(child));
//            vertices.get(getVertexIndex(parent)).addNeighbour(getVertexIndex(child), child);
//
//        }
//
//        /**
//         * Get array size
//         *
//         * @return array size
//         */
//        int getSize() {
//            return vertices.size();
//        }
//
//        /**
//         * Returns name of the vertex
//         *
//         * @param index of vertex
//         * @return string name of the vertex
//         */
//        String getVertexName(int index) {
//            if (index < getSize()) return vertices.get(index).getVertexName();
//            return null;
//        }
//
//        /**
//         * Helper method to get index of vertex from name
//         *
//         * @param name Name of vertex to find
//         * @return index of vertex, -1 if not found
//         */
//        int getVertexIndex(String name) {
//            for (int i = 0; i < vertices.size(); i++) if (vertices.get(i).getVertexName().equals(name)) return i;
//            return -1;
//        }
//
//        /**
//         * Modified toString method to output all vertices with their respective toString
//         *
//         * @return
//         */
//        public String toString() {
//            String output = "";
//            for (Vertex record : vertices) output += record.toString() + "\n";
//
//            return output;
//        }
//
//        /**
//         * Find if two graphs are connected
//         *
//         * @param from formURL
//         * @param to   toURL
//         * @return true iff urls are connected
//         */
//        boolean isConnected(int from, int to) {
//            for (int i = 0; i < getSize(); i++) if (array[from][to] == 1) return true;
//            return false;
//        }
//
//        int[][] getGraphAsInt() {
//            array = new int[vertices.size()][vertices.size()];
//            for (int y = 0; y < vertices.size(); y++) {
//                ArrayList<Neighbour> temp = vertices.get(y).neighbourList;
//                for (Neighbour addToArray : temp) array[y][addToArray.getVertexNum()] = 1;
//            }
//            return array;
//        }
//
//        String[][] getGraphAsString() {
//            String[][] array = new String[vertices.size()][vertices.size()];
//            for (int y = 0; y < vertices.size(); y++) {
//                ArrayList<Neighbour> temp = vertices.get(y).neighbourList;
//                for (Neighbour addToArray : temp) array[y][addToArray.getVertexNum()] = addToArray.getUrl();
//            }
//            return array;
//        }
//
//        class Vertex {
//            String vertexName;
//            ArrayList<Neighbour> neighbourList = new ArrayList<>();
//
//            /**
//             * Constructor
//             *
//             * @param vertexName
//             */
//            public Vertex(String vertexName) {
//                this.vertexName = vertexName;
//            }
//
//            /**
//             * Modified toString, prints all adjacent nodes in the vertex
//             *
//             * @return
//             */
//            @Override
//            public String toString() {
//                String output = vertexName + "->> ";
//                for (Neighbour adjacent : neighbourList) output += adjacent.getUrl() + "-> ";
//                return output;
//            }
//
//            /**
//             * Add adjacent nodes at vertex
//             *
//             * @param vertexNum index of node at vertex
//             * @param name      name of vertex
//             */
//            public void addNeighbour(int vertexNum, String name) {
//                neighbourList.add(new Neighbour(vertexNum, name));
//            }
//
//            /**
//             * Get name of this vertex
//             *
//             * @return name of this vertex
//             */
//            public String getVertexName() {
//                return vertexName;
//            }
//
//            /**
//             * @return returns entire list of neighbours linkedlist(adjacent nodes)
//             */
//            public ArrayList<Neighbour> getNeighbourList() {
//                return neighbourList;
//            }
//
//
//        }
//
//        class Neighbour {
//            int vertexNum;
//            String url;
//
//            /**
//             * Constructor
//             *
//             * @param vertexNum
//             * @param url
//             */
//            public Neighbour(int vertexNum, String url) {
//                this.vertexNum = vertexNum;
//                this.url = url;
//            }
//
//            /**
//             * @return returns current vertex number
//             */
//            public int getVertexNum() {
//                return vertexNum;
//            }
//
//            /**
//             * @return returns current vertex name
//             */
//            public String getUrl() {
//                return url;
//            }
//        }
//    }
//
//    class HamiltonianCycle {
//        private int graphSize, pathCount;
//        private int[] path;
//        private int[][] graph;
//
//
//        /**
//         * Function to find cycle
//         **/
//        String[] findHamiltonianCycle(int[][] inputGraph) {
//            graphSize = inputGraph.length;
//            path = new int[graphSize];
//            Arrays.fill(path, -1);
//            graph = inputGraph;
//            for (int i = 0; i < path.length; i++) {
//                try {
//                    path[0] = i;
//                    pathCount = 1;
//                    findPath(i);
//                } catch (Exception e) {
//                    return convertPathToStringArr();
//                }
//            }
//            return new String[0];
//        }
//
//        /**
//         * function to find paths recursively
//         **/
//        void findPath(int vertex) throws Exception {
//            if (isPathComplete(path) && pathCount == graphSize)
//                throw new Exception("");
//            /** all vertices selected but last vertex not linked to 0 **/
//            if (pathCount == graphSize) {
//                System.out.println("Ran here");
//                return;
//            }
//            for (int v = 0; v < graphSize; v++) {
//                // if connected **/
//                if (graph[vertex][v] == 1) {
//                    // add to path **/
//                    path[pathCount++] = v;
//                    // remove connection **/
//                    graph[vertex][v] = 0;
//                    // if vertex not already selected solve recursively **/
//                    if (!isSelected(v)) findPath(v);
//
//                    // restore connection **/
//                    graph[vertex][v] = 1;
//                    // remove path **/
//                    path[--pathCount] = -1;
//                }
//            }
//        }
//
//        /**
//         * Function to check if path has been selected
//         **/
//        boolean isSelected(int v) {
//            for (int i = 0; i < pathCount - 1; i++) if (path[i] == v) return true;
//            return false;
//        }
//
//        boolean isPathComplete(int[] pathCheck) {
//            int[] temp = pathCheck.clone();
//            Arrays.sort(temp);
//            for (int i = 0; i < pathCheck.length - 1; i++) if (temp[i] != temp[i + 1] - 1) return false;
//            return true;
//        }
//
//        String[] convertPathToStringArr() {
//            String[] stringArray = new String[intArray.length];
//            for (int i = 0; i < graphSize; i++)
//                stringArray[i] = fullGraph.getVertexName(path[i]);
//            return stringArray;
//        }
//    }
//
//}
