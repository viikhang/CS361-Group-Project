package Algorithm;

public class Dijkstra implements TraversalAlgorithm {

    private int[][] distances;// hold weights for node location on graph
    private GraphNode[] shortestPath;// return value for function

    private int size;// total vertices on graph
    private Graph localGraph;// implemented local graph for robot

    /**
     * Constructor for Dijkstra initialize the structures we need for
     * traversal.
     *
     * @param graph- Graph we are traversing
     */
    public Dijkstra(Graph graph) {
        size = graph.getTotalNodes();

        shortestPath = new GraphNode[size];
        int rows = graph.getBoard().length;
        int cols = graph.getBoard()[0].length;
        //predecessors = new GraphNode[graph.getTotalNodes()];
        distances = new int[rows][cols];
        localGraph = graph;
        // from psuedocode have to initialize every weight to infinity(max value)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distances[i][j] = Integer.MAX_VALUE;

            }
        }

    }

    /*
    Overriden method for the shortestPath
     */
    @Override
    public GraphNode[] findShortest(Graph localGraph, GraphNode start, GraphNode target) {
        return dijkstraShortestPath(start, target);
    }


    /*
        Actual calling of algorithm to traverse graph given start and target
     */
    private GraphNode[] dijkstraShortestPath(GraphNode start, GraphNode target) {
        shortestPath = new GraphNode[size];
        shortestPath = DIJ(start, target);
        return shortestPath;
    }

    /**
     * DIJ is method Dijkstra algorithm which is a modified version of BFS,
     * this is a greedy approach and chooses traversal based on
     * the lowest weight edge of every visited node.
     *
     * @param start-  starting node
     * @param target- ending node
     * @return Shortest Path
     */
    private GraphNode[] DIJ(GraphNode start, GraphNode target) {
        resetDistances(distances);// need to reset distances when moving backward
        start.setParentNode(null);
        distances[start.getRow()][start.getCol()] = 0;//distance start =0
        MinHeap heap = new MinHeap(size);//create heap with max size
        heap.insert(start, 0);// insert start into heap
        int index = 0;
        // heap should only be empty when no path is available
        while (!(heap.isEmpty())) {
            // current = root of heap/ min weight value
            GraphNode current = heap.extractMin();

            if (current.isVisited()) {
                continue;// if we have visited node continue to new min
            }

            current.setVisited(true);// we are relaxing nodes so visited = true
            //predecessors[index++] = current;
            // if target found program is done
            if (current == target) {
                //function that retraces path taken from start to target
                createPath(current);
                return shortestPath;
            }
            //relaxing edges
            for (int i = 0; i < 4; i++) {// max 4 edges to any one node

                GraphNode neighbor = current.getVertices()[i];// local neighbor
                // if the neighbor is traversable and not visited
                if (neighbor != null && !(neighbor.isVisited())) {
                    /* distance to neighbor = edge weight from current+ distance
                     to current

                     */
                    neighbor.setParentNode(current);
                    int updatedDist =
                            distances[current.getRow()][current.getCol()] +
                                    current.getNodeToVertexCost()[i];
                    //if updated distance is new lowest path
                    if (updatedDist <
                            distances[neighbor.getRow()][neighbor.getCol()]) {
                        // new lowest distance to that node
                        distances[neighbor.getRow()][neighbor.getCol()] =
                                updatedDist;

                        // we will look at this edge next because its lowest
                        heap.insert(neighbor, updatedDist);


                    }
                }
            }
        }


        return null;// no path
    }

    /*
        For reseting distances when moving backward from final item to start
     */
    private void resetDistances(int[][] distances) {
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[0].length; j++) {
                distances[i][j] = Integer.MAX_VALUE;

            }
        }

    }

    /**
     * create path goes from target and retraces paths built from target
     * until we reach the start -> this path will need to be reversed
     *
     * @param target- ending location
     */
    public void createPath(GraphNode target) {
        GraphNode current = target;
        int index = 0;
        while (current != null) {
            if (index >= shortestPath.length) {// Graph is disconnected from start
                throw new ArrayIndexOutOfBoundsException("DISCONNECTED GRAPH");
            }
            shortestPath[index++] = current;
            current = current.getParentNode();// traverse up
        }
        // reverse the newly filled in path
        for (int i = 0; i < index / 2; i++) {
            GraphNode swap = shortestPath[i];
            shortestPath[i] = shortestPath[index - i - 1];
            shortestPath[index - i - 1] = swap;
        }

    }

    /**
     * Print path loops through shortest and prints node's x,y value on graph,
     * because the length is dependent on these specific values printPath()
     * so it returns the length of the path. This also helps with subPathing as
     * the subPaths are added to a total path length for multiple items.
     */
    public int printPath() {
        int length = 0;// current length
        for (int i = 0; i < shortestPath.length; i++) {// length of shortest
            if (shortestPath[i] != null) {// valid node
                System.out.print(shortestPath[i]);// print valid node
                length++;
                // check for end of path and add arrow in between nodes
                if (i + 1 < shortestPath.length && shortestPath[i + 1] != null) {
                    System.out.print(" -> ");
                }
            } else {
                break;// reached end of path so we are done
            }
        }
        System.out.println();
        return length;// current length of subpath
    }

}


