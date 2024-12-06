package Algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class AStarSearch implements TraversalAlgorithm{
    private int[][] gCost;// actual distance is gCost
    private int[][] fCost;// f cost is = g+h, heap is based on this cost
    private GraphNode[][] predecessors;// previous nodes to current
    private GraphNode[] shortestPath;// return path
    private int pathIndex;// index for rebuilding

    private int size;// total nodes
    private Graph localGraph;

    /**
     * Constructor initializing structures for A star traversal
     *
     * @param graph
     */
    public AStarSearch(Graph graph) {
        size = graph.getTotalNodes();
        pathIndex = 0;
        shortestPath = new GraphNode[size];
        int rows = graph.getBoard().length;
        int cols = graph.getBoard()[0].length;
        predecessors = new GraphNode[rows][cols];
        gCost = new int[rows][cols];
        fCost = new int[rows][cols];
        localGraph = graph;

        // Astar uses two arrays for holding weights
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gCost[i][j] = Integer.MAX_VALUE;
                fCost[i][j] = Integer.MAX_VALUE;

            }
        }

    }
    @Override
    public GraphNode[] findShortest(Graph localGraph,GraphNode start, GraphNode target){
        return AStarShortestPath(start,target);
    }

    // Actual algorithm call
    private GraphNode[] AStarShortestPath(GraphNode start, GraphNode target) {
        shortestPath = AStar(start, target);
        return shortestPath;

    }

    /**
     * AStar is modified BFS, and is a greedy algorithm making decisions based
     * on actual gCost, and the heuristic cost totaled in Fcost
     * the Heuristic cost is done on the fly and utilizes a manhattan distance
     * <p>
     * This is used because our graph does not use diagonal edges so this is
     * a more accurate way of collecting distance to nodes
     *
     * @param start-  starting node
     * @param target- target node
     * @return- shortest path
     */
    public GraphNode[] AStar(GraphNode start, GraphNode target) {
        start.setParentNode(null);
        gCost[start.getRow()][start.getCol()] = 0;// distance to start is 0
        // heap structure to determine the next node to visit
        MinHeap heap = new MinHeap(size);
        heap.insert(start, 0);// hardcoded f cost

        // if heap is empty we explored all available nodes with no path found
        while (!(heap.isEmpty())) {
            // current = min fCost node
            GraphNode current = heap.extractMin();
            // if we visited this node continue to next lowest min cost
            if (current.isVisited()) {
                continue;
            }

            current.setVisited(true);// we are going to explore this node

            // if target is found the algorithm is done
            if (current == target) {
                //function that rebuilds path
                createPath(current);
                return shortestPath;
            }
            //relaxing edges
            for (int i = 0; i < 4; i++) {// max four adjacent nodes

                GraphNode neighbor = current.getVertices()[i];
                // if adjacent node is traversable and not visited
                if (neighbor != null && !(neighbor.isVisited())) {
                    neighbor.setParentNode(current);
                    /* manhattan distance formula for heuristic
                        target(x2,y2) current(x1,y1)
                        Hcost = |x1-x2| - |y1 - y2|
                     */
                    int newHCost = abs(neighbor.getRow() - target.getRow()) +
                            abs(neighbor.getCol() - target.getCol());
                    // same as distances in dijkstras algorithm and is actual dist
                    int updatedGCost = gCost[current.getRow()][current.getCol()]
                            + current.getNodeToVertexCost()[i];
                    // same as Dijkstra algorithm
                    if (updatedGCost <
                            gCost[neighbor.getRow()][neighbor.getCol()]) {

                        gCost[neighbor.getRow()][neighbor.getCol()] =
                                updatedGCost;

                        neighbor.setParentNode(current);
                        // despite exploring fCost min, g min is in pred
                        predecessors[neighbor.getRow()][neighbor.getCol()] =
                                current;//we chose this node for dijkstras
                    }
                    // fCost = H + G
                    fCost[neighbor.getRow()][neighbor.getCol()] = newHCost +
                            gCost[neighbor.getRow()][neighbor.getCol()];
                    // insert based on f cost of that place on the graph
                    heap.insert(neighbor,
                            fCost[neighbor.getRow()][neighbor.getCol()]);
                }
            }

        }

        return null;// no path
    }

    /*
    just absolute value function
     */
    private int abs(int c) {
        // if negative value
        if (c < 0) {
            return -1 * c;
        }
        return c;
    }

    /*private GraphNode[] pathBuilder(GraphNode[][] predecessors, GraphNode start, GraphNode target) {
        GraphNode[] path = new GraphNode[shortestPath.length];// return array
        GraphNode curr = target;// start from ending
        pathIndex = 0;
        //start from target and follow pred backwards to start.
        while (curr != null) {
            path[pathIndex++] = curr;// add current node
            if (curr == start) {// we reached start
                break;
            }
            // new current is node previous to the current node
            curr = predecessors[curr.getRow()][curr.getCol()];

        }
        //reverse path
        for (int i = 0; i < pathIndex / 2; i++) {
            GraphNode temp = path[i];
            path[i] = path[pathIndex - i - 1];
            path[pathIndex - i - 1] = temp;
        }

        return path;
    }

     */

    public void createPath(GraphNode target) {
        GraphNode current = target;
        int index = 0;
        while (current.getParentNode() != null) {
            if (index >= shortestPath.length) {// Graph is disconnected from start
                throw new ArrayIndexOutOfBoundsException("DISCONNECTED GRAPH");
            }
            shortestPath[index] = current;
            index++;
            current = current.getParentNode();
        }
        // reverse
        for(int i = 0; i < index /2; i++){
            GraphNode swap = shortestPath[i];
            shortestPath[i] = shortestPath[index - i - 1];
            shortestPath[index - i - 1] = swap;
        }

    }
    /**
     * Print path loops through shortest and prints node's x,y value on graph
     */
    public void printPath() {
        for (int i = 0; i < shortestPath.length; i++) {
            if (shortestPath[i] != null) {
                System.out.print(shortestPath[i]);
                if (i + 1 < shortestPath.length && shortestPath[i + 1] != null) {
                    System.out.print(" -> ");
                }
            } else {
                break;
            }
        }
        System.out.println();
    }

}

