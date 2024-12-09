package Algorithm;

public class BreadthFirstSearch implements TraversalAlgorithm {
    private GraphNode[] shortestPath; // holds the shortest path
    private int pathIndex; // holds the path index

    private int size; // maximum size for the array

    private GraphNode finalNode; // final node for a given path

    private GraphNode[] parentNodes; // array of all the parent nodes for any found
    // nodes in the algorithm
    private int parentIndex = 0; // index of the parent node

    /**
     * Constructor initializing structure for BFS traversal
     *
     * @param size:  max size for the array that holds path
     * @param graph: given graph that will be searched over
     */
    public BreadthFirstSearch(int size, Graph graph) {
        this.size = size;
        shortestPath = new GraphNode[size];
        pathIndex = 0;
        parentNodes = new GraphNode[graph.getTotalNodes() * graph.getTotalNodes()];
    }


    @Override
    public GraphNode[] findShortest(Graph localGraph, GraphNode start, GraphNode target) {
        return findShortestPath(start, target);
    }

    /**
     * method called to find and return the shortest path found
     *
     * @param start:  starting node
     * @param target: target node
     * @return shortest path found
     */
    private GraphNode[] findShortestPath(GraphNode start, GraphNode target) {
        pathIndex = 0;
        shortestPath = new GraphNode[size];
        shortestPath = BFS(start, target);
        return shortestPath;
    }

    /**
     * main bfs method that returns path if found or returns null
     * if there is no path
     *
     * @param start:  starting node
     * @param target: target node
     * @return shortest path
     */
    public GraphNode[] BFS(GraphNode start, GraphNode target) {
        if (bfsHelper(start, target, shortestPath)) {
            return shortestPath;
        }

        return null; //no path was found
    }

    /**
     * bfs helper function that does all the path finding
     * and searches for the item while traversing the graph
     *
     * @param current
     * @param target
     * @param shortestPath
     * @return
     */
    public boolean bfsHelper(GraphNode current, GraphNode target, GraphNode[] shortestPath) {
        // checks if current node is null
        //might remove
        if (current == null) {
            return false;
        }

        current.setVisited(true);
        addNode(current);

        // create queue
        Algorithm.Queue queue = new Algorithm.Queue();


        // checks if current node works
        if (current == target) {
            return true;
        }
        // adds current to the queue
        queue.add(current);


        while (!queue.isEmpty()) {
            GraphNode u = queue.remove();
            for (GraphNode v : u.getVertices()) {
                if (v != null && !v.isVisited()) {
                    v.setParentNode(u);
                    parentNodes[parentIndex++] = v;
                    v.setVisited(true);
                    addNode(v);
                    queue.add(v);
                    //System.out.println(v);
                }
                if (v == target) {
                    finalNode = v;
                    createShortPath();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * creates shortest path, saves into a short path array
     */
    private void createShortPath() {
        int depth = 0;
        GraphNode temp1 = finalNode;
        while (temp1 != null) {
            depth++;
            temp1 = temp1.getParentNode();
        }

        GraphNode[] reverse = new GraphNode[depth];


        int index = reverse.length - 1;
        GraphNode temp2 = finalNode;
        while (temp2 != null && index >= 0) {
            reverse[index--] = temp2;
            temp2 = temp2.getParentNode();
        }
        shortestPath = new GraphNode[size];
        for (int i = 0; i < reverse.length; i++) {
            shortestPath[i] = reverse[i];
        }
    }
    /**
     * adds given node
     *
     * @param node: node to insert to shortestPath array
     */
    private void addNode(GraphNode node) {
        if (pathIndex < shortestPath.length) {
            shortestPath[pathIndex] = node;
            pathIndex++;
        }
    }

    /**
     * prints the given shortest path found
     *
     * @return path length
     */
    public int printPath() {
        int length = 0;
        int depth = 0;
        GraphNode temp1 = finalNode;
        while (temp1 != null) {
            depth++;
            temp1 = temp1.getParentNode();
        }

        GraphNode[] reverse = new GraphNode[depth];


        int index = reverse.length - 1;
        GraphNode temp2 = finalNode;
        while (temp2 != null && index >= 0) {
            reverse[index--] = temp2;
            temp2 = temp2.getParentNode();
        }

        for (int i = 0; i < reverse.length; i++) {
            System.out.print(reverse[i]);
            length++;
            if (i < reverse.length - 1) {
                System.out.print(" -> ");
            }
        }


        // for loop resets the parent node values of previously used nodes
        for (GraphNode node : parentNodes) {
            if (node == null) {
                break;
            }
            node.setParentNode(null);
        }

        System.out.println();
        return length;
    }
}
