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
