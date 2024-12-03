package Algorithm;

public class Robot {

    private GraphNode[] targets;
    private GraphNode[] robotPath;
    private TraversalAlgorithm RobotAlgorithm;


    public Robot(Graph graph, TraversalAlgorithm algorithm){
        targets = graph.getItemNodeList();
        robotPath = new GraphNode[graph.getTotalNodes() * 2];//account for return
        RobotAlgorithm = algorithm;

    }

    public GraphNode[] runRobot(GraphNode[]targets, GraphNode start){
        int index = 0;
        GraphNode current = start;
        // for all targets
        for(GraphNode target: targets){
            // subPath is path from current to target
            GraphNode[] subPath = RobotAlgorithm.findShortest(current,target);
            // fill robot path with shortest
            for(GraphNode node : subPath){
                robotPath[index++] = node;
            }
            // current gets set to the found target for next target
            current = target;
        }
        // now that all targets have been found we return to start from final target
        GraphNode[] returnToStart = RobotAlgorithm.findShortest(targets[targets.length -1],start);
        for(GraphNode node : returnToStart){// fill in the last path
            robotPath[index++] = node;
        }
        return robotPath;
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
