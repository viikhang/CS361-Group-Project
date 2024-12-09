package Algorithm;

public class Prims implements TraversalAlgorithm {
    private GraphNode[] shortestPath;
    private int size;
    private Graph localGraph;

    public Prims(Graph graph) {
        size = graph.getTotalNodes();
        shortestPath = new GraphNode[size];
        localGraph = graph;
    }

    /*
    Overriden method for the shortestPath
     */
    @Override
    public GraphNode[] findShortest(Graph localGraph, GraphNode start, GraphNode target) {
        shortestPath = new GraphNode[size];
        return primMST(start, target);
    }

    /**
     * This function will build a MST from the start node, adding the edges to
     * the heap as long as they are not to unvisited nodes. The actual mst is
     * not held in a specific data structure, rather every "child" node to current
     * has its setParentNode( current ); this way when we finally reach the target
     * there is uninterrupted path to start. This will find the ABSOLUTE shortest
     * path however it searched through many adjacent nodes to get there making it
     * super slow.
     *
     * @param start-  starting node
     * @param target- ending node
     * @return shortest path
     */
    private GraphNode[] primMST(GraphNode start, GraphNode target) {
        GraphNode current = start;
        current.setParentNode(null);
        MinHeap heap = new MinHeap(size * 4);// over-estimate of edges
        heap.insert(current, 0);// start at current

        while (current != target) {// can cause loop if target is disconnected
            // find new lowest edge
            current = heap.extractMin();
            // already visited
            if (current.isVisited()) {
                continue;
            }

            current.setVisited(true);// now visiting

            for (int i = 0; i < 4; i++) {// all adjacent to current
                GraphNode node = current.getVertices()[i];// temporary node
                // if its not valid edge
                if (node == null || node.isVisited()) {
                    continue;
                }
                // if valid set new edge as child of current
                node.setParentNode(current);
                // insert edge
                heap.insert(node, current.getNodeToVertexCost()[i]);

            }
        }
        // we found a path!
        createPath(current);// build path
        return shortestPath;// return built path

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
        // reverse newly formed graph
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
