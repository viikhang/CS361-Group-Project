package Algorithm;

public class Robot {

    private GraphNode[] targets;// list of items in graph
    private GraphNode[] robotPath;// shortest path from robot
    private TraversalAlgorithm RobotAlgorithm;// current algorithm
    private Graph localGraph;// copy of graph from main


    public Robot(Graph graph, TraversalAlgorithm algorithm){
        targets = graph.getItemNodeList();
        //account for return which could be very large
        robotPath = new GraphNode[graph.getTotalNodes() * graph.getTotalNodes()];
        localGraph = graph;
        RobotAlgorithm = algorithm;

    }

    /**
     * runRobot works like an all encompassing way to find every node in a graph
     * without repeated calls from main, also traversing back to the start.
     * @param targets- list of items
     * @param start - starting node
     * @return- shortest path from start to all items and back to start
     */
    public GraphNode[] runRobot(GraphNode[]targets, GraphNode start){
        int index = 0;// for adding subPaths
        int itemsFound =0;// keep track of where in targets we are
        GraphNode current = start;
        int pathLength =0;
        // for all targets
        for(GraphNode target: targets){
            // if we have reached the end of the list
            if(current == null|| target == null){
                break;
            }
            resetGraphNodes();// get fresh graph for traversal
            /* subPath is path from current to target
                Because all algorithms have this method, we send the algorithm
                a fresh graph, start,end to make sure it can find the subPath
             */
            GraphNode[] subPath =
                    RobotAlgorithm.findShortest(localGraph,current,target);

            itemsFound ++;// subPath found

            // fill robot path with shortest
            for(int i =0; i< subPath.length; i++){
                if(subPath[i] != null){
                    robotPath[index++] = subPath[i];
                    if(subPath[i] == target){
                        break;
                    }
                }
            }
            // current gets set to the found target for next target
            current = target;
        }
        resetGraphNodes();
        /* now that all targets have been found we return to start from final
        target using the same algorithm
         */
        GraphNode[] returnToStart =
                RobotAlgorithm.findShortest
                        (localGraph,targets[itemsFound -1],start);

        //Use this node to indicate robot going back to start
        GraphNode tempNode = new GraphNode(-99,-99);
        // robot at the last index is this termination node for printing
        robotPath[index++] = tempNode;
        for(GraphNode node : returnToStart){// fill in the last path
            if(node != null) {
                robotPath[index++] = node;
                if(node == start){
                    break;
                }
            }
        }
        return robotPath;
    }

    // need to reset the nodes visited for multiple items
    private void resetGraphNodes() {
        for (int i = 0; i < localGraph.getNodeList().length; i++) {
            localGraph.getNodeList()[i].setVisited(false);
            localGraph.getNodeList()[i].setParentNode(null);
        }
    }

    /**
     * Print Path will iterate over the current path ignoring null indices
     * If the current node is the temp node we print a message indicating a
     * return to start.
     */
    public int printPath() {
        int length = 0;
        for (int i = 0; i < robotPath.length; i++) {
            if (robotPath[i] != null) {
                // if the path is the termination node
                if(robotPath[i].getCol() == -99 && robotPath[i].getRow() == -99){
                    System.out.println();
                    // message indicating return to start
                    System.out.print(robotPath[i].goingBack());
                } else {
                    length++;
                    // valid node to print
                    System.out.print(robotPath[i]);
                }
                // check for in between nodes to print arrow
                if (i + 1 < robotPath.length &&
                        robotPath[i + 1] != null &&
                        robotPath[i + 1].getCol() != -99) {
                    System.out.print(" -> ");
                }
            } else {

                break;
            }
        }
        System.out.println();
        return length;
    }
}
