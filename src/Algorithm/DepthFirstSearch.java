package Algorithm;

public class DepthFirstSearch implements TraversalAlgorithm {
    private GraphNode[] shortestPath;
    private int pathIndex;
    private Graph localGraph;

    /**
     * Constructor for Depth First Search
     *
     * @param size  (Size of graph)
     * @param graph (Graph algorithm is used on)
     */
    public DepthFirstSearch(int size, Graph graph) {
        shortestPath = new GraphNode[size];
        pathIndex = 0;
        localGraph = graph;
    }

    /**
     * Called to find the shortest path using DFS
     *
     * @param localGraph (Graph being traversed)
     * @param start      (Starting node)
     * @param target     (Target node)
     * @return (Return path)
     */
    @Override
    public GraphNode[] findShortest(Graph localGraph, GraphNode start, GraphNode target) {
        return findShortestPath(start, target);
    }

    /**
     * Called to find the shortest path Using DFS
     *
     * @param start  (Starting node)
     * @param target (Target node)
     * @return Shortest path
     */
    private GraphNode[] findShortestPath(GraphNode start, GraphNode target) {
        pathIndex = 0;
        shortestPath = DFS(start, target);
        return shortestPath;
    }

    /**
     * Called to start finding the shortest path
     *
     * @param start  (Starting node)
     * @param target (Target node)
     * @return Shortest path if found
     */
    public GraphNode[] DFS(GraphNode start, GraphNode target) {
        if (dfsHelper(start, target)) {
            return shortestPath;
        }

        return null; //no path was found
    }

    /**
     * Actual Depth First algorithm, algorithm is done recursively
     *
     * @param current (Current node)
     * @param target  (Target node)
     * @return True if found path, false if not
     */
    public boolean dfsHelper(GraphNode current, GraphNode target) {
        if (current == null) {
            return false;
        }
        current.setVisited(true);
        addNode(current);
        if (current == target) {
            return true;
        }

        for (GraphNode neighbor : current.getVertices()) {
            if (neighbor != null && !neighbor.isVisited()) {
                if (dfsHelper(neighbor, target)) {
                    return true;
                }
            }
        }
        removeNodeFromPath();
        return false;
    }

    /**
     * Add node to path
     *
     * @param node (Node being added to path)
     */
    private void addNode(GraphNode node) {
        if (pathIndex < shortestPath.length) {
            shortestPath[pathIndex] = node;
            pathIndex++;
        }
    }

    /**
     * Remove current node from path
     */
    private void removeNodeFromPath() {
        if (pathIndex > 0) {
            shortestPath[pathIndex] = null;
            pathIndex--;
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
