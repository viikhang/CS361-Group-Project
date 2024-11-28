package Algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class AStarSearch {
    // actual distance is gCost
    private int[][] gCost;

    // f cost is = g+h and is what the heap is minimized by for extraction
    private int[][] fCost;
    private GraphNode[][] predecessors;
    private GraphNode[] shortestPath;
    private int pathIndex;

    private int size;

    public AStarSearch(Graph graph) {
        size = graph.getTotalNodes();
        pathIndex = 0;
        shortestPath = new GraphNode[size];
        int rows = graph.getBoard().length;
        int cols = graph.getBoard()[0].length;
        predecessors = new GraphNode[rows][cols];
        gCost = new int[rows][cols];
        fCost = new int[rows][cols];


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gCost[i][j] = Integer.MAX_VALUE;
                fCost[i][j] = Integer.MAX_VALUE;

            }
        }

    }

    public void AStarShortestPath(GraphNode start, GraphNode target) {
        shortestPath = AStar(start, target);

    }

    public GraphNode[] AStar(GraphNode start, GraphNode target) {
        gCost[start.getRow()][start.getCol()] = 0;



        MinHeap heap = new MinHeap(size);
        heap.insert(start, 0);


        while (!(heap.isEmpty())) {

            GraphNode current = heap.extractMin();

            if (current.isVisited()) {
                continue;
            }

            current.setVisited(true);

            if (current == target) {
                //function that keeps track of path taken
                return pathBuilder(predecessors, start, target);
            }
            //relaxing
            for (int i = 0; i < 4; i++) {

                GraphNode neighbor = current.getVertices()[i];

                if (neighbor != null && !(neighbor.isVisited())) {
                    // manhattan distance formula for heuristic
                    int newHCost = abs(neighbor.getRow()- target.getRow()) +
                            abs(neighbor.getCol()- target.getCol());
                    // same as distances in dijkstras algorithm and is actual dist
                    int updatedGCost = gCost[current.getRow()][current.getCol()]
                            + current.getNodeToVertexCost()[i];

                    if (updatedGCost < gCost[neighbor.getRow()][neighbor.getCol()]) {
                        gCost[neighbor.getRow()][neighbor.getCol()] = updatedGCost;
                        predecessors[neighbor.getRow()][neighbor.getCol()] =
                                current;//we chose this node for dijkstras
                    }

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
    private int abs( int c ){
        if (c < 0){
            return -1 *c;
        }
        return c;
    }

    private GraphNode[] pathBuilder(GraphNode[][]predecessors, GraphNode start, GraphNode target){
        GraphNode[] path = new GraphNode[shortestPath.length];
        GraphNode curr = target;
        pathIndex = 0;
        //start from target and follow pred backwards to start.
        while(curr != null){
            path[pathIndex++]= curr;
            if(curr == start){
                break;
            }
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
