package Algorithm;

public class Dijkstra {

    private int[][] distances;// hold weights for node location on graph
    private GraphNode[][] predecessors;// hold visited node path on graph
    private GraphNode[] shortestPath;// return value for function
    private int pathIndex;// index of where in path we are

    private int size;// total vertices on graph

    /**
     * Constructor for Dijkstra initialize the structures we need for
     * traversal.
     * @param graph- Graph we are traversing
     */
    public Dijkstra(Graph graph) {
        size = graph.getTotalNodes();
        pathIndex = 0;
        shortestPath = new GraphNode[size];
        int rows = graph.getBoard().length;
        int cols = graph.getBoard()[0].length;
        predecessors = new GraphNode[rows][cols];
        distances = new int[rows][cols];

        // from psuedocode have to initialize every weight to infinity(max value)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distances[i][j] = Integer.MAX_VALUE;

            }
        }

    }
    /*
        Actual calling of algorithm to traverse graph given start and target
     */
    public void dijkstraShortestPath(GraphNode start, GraphNode target) {
        shortestPath = DIJ(start, target);
    }

    /**
     * DIJ is method Dijkstra algorithm which is a modified version of BFS,
     * this is a greedy approach and chooses traversal based on
     * the lowest weight edge of every visited node.
     *
     * @param start- starting node
     * @param target- ending node
     * @return Shortest Path
     */
    public GraphNode[] DIJ(GraphNode start, GraphNode target) {

        distances[start.getRow()][start.getCol()] = 0;//distance start =0
        predecessors[start.getRow()][start.getCol()] = start;// first node

        MinHeap heap = new MinHeap(size);//create heap with max size
        heap.insert(start, 0);// insert start into heap

        // heap should only be empty when no path is available
        while (!(heap.isEmpty())) {
            // current = root of heap/ min weight value
            GraphNode current = heap.extractMin();

            if (current.isVisited()) {
                continue;// if we have visited node continue to new min
            }

            current.setVisited(true);// we are relaxing nodes so visited = true
            // if target found program is done
            if (current == target) {
                //function that retraces path taken from start to target
                return pathBuilder(predecessors, start, target);
            }
            //relaxing edges
            for (int i = 0; i < 4; i++) {// max 4 edges to any one node

                GraphNode neighbor = current.getVertices()[i];// local neighbor
                // if the neighbor is traversable and not visited
                if (neighbor != null && !(neighbor.isVisited())) {
                    /* distance to neighbor = edge weight from current+ distance
                     to current

                     */
                    int updatedDist =
                            distances[current.getRow()][current.getCol()] +
                                    current.getNodeToVertexCost()[i];
                    //if updated distance is new lowest path
                    if (updatedDist <
                            distances[neighbor.getRow()][neighbor.getCol()]) {
                        // new lowest distance to that node
                        distances[neighbor.getRow()][neighbor.getCol()] =
                                updatedDist;
                        // we will traverse this neighbor so add it to path
                        predecessors[neighbor.getRow()][neighbor.getCol()] =
                                current;
                        // we will look at this edge next because its lowest
                        heap.insert(neighbor, updatedDist);

                    }
                }
            }
        }

        return null;// no path
    }

    /**
     *
     * @param predecessors- list of nodes taken based on x,y of the node
     * @param start- starting node
     * @param target- ending node
     * @return - Shortest path
     */
    private GraphNode[] pathBuilder(GraphNode[][]predecessors, GraphNode start, GraphNode target){
        GraphNode[] path = new GraphNode[shortestPath.length];// return array
        GraphNode curr = target;// start from ending
        pathIndex = 0;
        //start from target and follow pred backwards to start.
        while(curr != null){
            path[pathIndex++]= curr;// add current node
            if(curr == start){// we reached start
                break;
            }
            // new current is node previous to the current node
            curr = predecessors[curr.getRow()][curr.getCol()];

        }
        //reverse path
        for(int i =0; i< pathIndex/2; i++){
            GraphNode temp = path[i];
            path[i] = path[pathIndex - i -1];
            path[pathIndex -i -1] = temp;
        }

        return path;
    }

    /**
     * Print path loops through shortest and prints node's x,y value on graph
     */
    public void printPath() {
        for(int i = 0; i < shortestPath.length; i++){
            if(shortestPath[i] != null) {
                System.out.print(shortestPath[i]);
                if(i +1 < shortestPath.length && shortestPath[i + 1] != null) {
                    System.out.print(" -> ");
                }
            } else {
                break;
            }
        }
        System.out.println();
    }

}


