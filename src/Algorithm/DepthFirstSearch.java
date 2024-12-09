package Algorithm;

public class DepthFirstSearch implements TraversalAlgorithm{
    private GraphNode[] shortestPath;
    private int pathIndex;
    private Graph localGraph;

    public DepthFirstSearch(int size,Graph graph) {
        shortestPath = new GraphNode[size];
        pathIndex = 0;
        localGraph = graph;
    }
    @Override
    public GraphNode[] findShortest(Graph localGraph,GraphNode start, GraphNode target){
        return findShortestPath(start,target);
    }

    private GraphNode[] findShortestPath(GraphNode start, GraphNode target) {
        pathIndex = 0;
        shortestPath = DFS(start, target);
        return shortestPath;
    }

    public GraphNode[] DFS(GraphNode start, GraphNode target) {
        if (dfsHelper(start, target, shortestPath)) {
            return shortestPath;
        }

        return null; //no path was found
    }

    public boolean dfsHelper(GraphNode current, GraphNode target, GraphNode[] shortestPath) {
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
                if (dfsHelper(neighbor, target, shortestPath)) {
                    return true;
                }
            }
        }
        removeNodeFromPath();
        return false;
    }

    private void addNode(GraphNode node) {
        if (pathIndex < shortestPath.length) {
            shortestPath[pathIndex] = node;
            pathIndex++;
        }
    }

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
        int length =0;// current length
        for (int i = 0; i < shortestPath.length; i++) {// length of shortest
            if (shortestPath[i] != null) {// valid node
                System.out.print(shortestPath[i]);// print valid node
                length ++;
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
