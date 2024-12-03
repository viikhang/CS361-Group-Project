package Algorithm;

public class Robot {

    private GraphNode[] targets;
    private GraphNode[] robotPath;
    private TraversalAlgorithm RobotAlgorithm;
    private Graph localGraph;


    public Robot(Graph graph, TraversalAlgorithm algorithm){
        targets = graph.getItemNodeList();
        robotPath = new GraphNode[graph.getTotalNodes() * 2];//account for return
        localGraph = graph;
        RobotAlgorithm = algorithm;

    }

    public GraphNode[] runRobot(GraphNode[]targets, GraphNode start){
        int index = 0;
        int itemsFound = 0;
        GraphNode current = start;
        // for all targets
        for(GraphNode target: targets){
            if(current == null|| target == null){
                break;
            }
            resetGraphNodes();
            // subPath is path from current to target
            GraphNode[] subPath = RobotAlgorithm.findShortest(localGraph,current,target);
            itemsFound ++;
            // fill robot path with shortest
            for(int i =0; i< subPath.length; i++){
                if(subPath[i] != null){
                    robotPath[index++] = subPath[i];
                }
            }
            // current gets set to the found target for next target
            current = target;
        }
        resetGraphNodes();
        // now that all targets have been found we return to start from final target
        GraphNode[] returnToStart = RobotAlgorithm.findShortest(localGraph,targets[itemsFound -1],start);
        for(GraphNode node : returnToStart){// fill in the last path
            if(node != null) {
                robotPath[index++] = node;
            }
        }
        return robotPath;
    }

    private void resetGraphNodes() {
        for (int i = 0; i < localGraph.getNodeList().length; i++) {
            localGraph.getNodeList()[i].setVisited(false);
        }
    }
    public void printPath() {
        for (int i = 0; i < robotPath.length; i++) {
            if (robotPath[i] != null) {
                System.out.print(robotPath[i]);
                if (i + 1 < robotPath.length && robotPath[i + 1] != null) {
                    System.out.print(" -> ");
                }
            } else {
                break;
            }
        }
        System.out.println();
    }











}
