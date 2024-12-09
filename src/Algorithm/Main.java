package Algorithm;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static CellType[][] grid;// visualization of GraphNodes and graph
    public static int itemsFound = 0;// used to iterate items found
    public static Graph graph;// Actual 2D array of GraphNodes

    public static void main(String[] args) {
        //read in a file here, then after creating the graph,
        if (args.length != 1) {
            System.out.println("Invalid arguments given");
            System.exit(1);
        } else {
            String fileName = args[0];
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String[] dimensions = reader.readLine().split(" ");
                int width = Integer.parseInt(dimensions[0]);
                int height = Integer.parseInt(dimensions[1]);
                grid = new CellType[width][height];

                String line;
                for (int i = 0; i < height; i++) {
                    line = reader.readLine();
                    String[] values = line.split(" ");
                    for (int j = 0; j < width; j++) {
                        CellType type = convert(values[j].charAt(0));
                        grid[i][j] = type;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        graph = new Graph(grid);
        printBoard();
        System.out.println();
        GraphNode startingNode = graph.getStartingNode();

        // used for calculating time later final = end - start
        long startTime;
        long endTime;
        long finalTime;


        /**
         * Our Depth First Search Algorithm approach to finding a shortest path for
         * both single and multiple item paths, timed in NANO seconds.
         //         */
        System.out.println("Depth First Search Algorithm");
        //Instantiate DFS with Graph
        DepthFirstSearch dfs = new DepthFirstSearch(graph.getTotalNodes(), graph);
        int pathLengthD = 0;// variable to keep track of current length DFS
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            dfs.findShortest(graph, startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            pathLengthD += dfs.printPath();// printPath returns current length
            System.out.println("Depth First Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: " + (pathLengthD));
        } else { //handling multiple items
            finalTime = 0;
            GraphNode targetNode = graph.getItemNodeList()[0];// list of items
            int index = 0;// where we are in nodeList
            while (index < graph.getItemCount()) {
                // reset is boolean just in case we traverse same node
                resetGraphNodes();
                startTime = System.nanoTime();
                dfs.findShortest(graph, startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;// found item
                startingNode = targetNode;// new start is where we end
                targetNode = graph.getItemNodeList()[index];
                // print the subPath and add its length
                pathLengthD += dfs.printPath();
            }

            System.out.println("Depth First Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: " + (pathLengthD - graph.getItemCount() - 1));
        }
        System.out.println();

        /**
         * Our Breadth First Search Algorithm approach to finding a shortest path for
         * both single and multiple item paths, timed in NANO seconds.
         */
        System.out.println("Breadth First Search Algorithm");
        //Instantiate BFS with Graph
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph.getTotalNodes(), graph);
        resetGraphNodes();// reset GraphNode relationships
        int pathLengthB = 0;// pathLength for BFS
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            bfs.findShortest(graph, startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            pathLengthB += bfs.printPath();
            System.out.println("Breadth First Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: " + (pathLengthB));
        } else { //handle multiple items
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                bfs.findShortest(graph, startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                pathLengthB += bfs.printPath();
            }
            System.out.println("Breadth First Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: " + (pathLengthB - graph.getItemCount() - 1));
        }

        /**
         * Our Dijkstra's Search Algorithm approach to finding a shortest path for
         * both single and multiple item paths, timed in NANO seconds.
         */
        System.out.println();
        System.out.println("Dijkstra Algorithm");
        //Instantiate Dijkstra with Graph
        Dijkstra dijk = new Dijkstra(graph);
        resetGraphNodes();
        int pathLengthDij = 0;
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            dijk.findShortest(graph, startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            pathLengthDij += dijk.printPath();
            System.out.println("Dijkstra Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: " + (pathLengthDij));
        } else {
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                dijk.findShortest(graph, startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];

                pathLengthDij += dijk.printPath();
            }

            System.out.println("Dijkstra Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: " + (pathLengthDij - graph.getItemCount() - 1));

        }

        /**
         * Our A* Search Algorithm approach to finding a shortest path for
         * both single and multiple item paths, timed in NANO seconds.
         */
        System.out.println();
        System.out.println("A* Algorithm");
        //Instantiate A* with Graph
        AStarSearch Astar = new AStarSearch(graph);
        resetGraphNodes();
        int pathLengthA = 0;
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            Astar.findShortest(graph, startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            pathLengthA += Astar.printPath();
            System.out.println("A* Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: " + (pathLengthA));
        } else {
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                Astar.findShortest(graph, startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                pathLengthA += Astar.printPath();
            }

            System.out.println("A* Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: " + (pathLengthA - graph.getItemCount() - 1));
        }

        /**
         * The Modified Prim Search Algorithm approach to finding a shortest path for
         * both single and multiple item paths, timed in NANO seconds.
         */
        System.out.println();
        System.out.println("Modified Prims Algorithm");
        Prims prims = new Prims(graph);
        resetGraphNodes();
        int pathLengthP = 0;
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            prims.findShortest(graph, startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            pathLengthP += prims.printPath();
            System.out.println(" Prims Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: " + pathLengthP);
        } else {
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                prims.findShortest(graph, startingNode, targetNode);
                endTime = System.nanoTime();

                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                pathLengthP += prims.printPath();

            }
            System.out.println("Prims search Time (nano seconds): " + finalTime);
            System.out.println("Path length: " + (pathLengthP - graph.getItemCount() - 1));
        }
        System.out.println();
        System.out.println();
        resetGraphNodes();

        // Robot Time for every Algorithm
        //DFS Robot
        DepthFirstSearch dfsRobot = new DepthFirstSearch(graph.getTotalNodes(), graph);
        // fresh DFS for robot
        Robot DFSrobot = new Robot(graph, dfsRobot);
        System.out.println(" DFS Robot");
        startTime = System.nanoTime();
        // method exclusive to Robot prints path using specific algorithm
        DFSrobot.runRobot(graph.getItemNodeList(), graph.getStartingNode());
        endTime = System.nanoTime();
        finalTime = endTime - startTime;
        // path length + printing procedure
        int Dlength = DFSrobot.printPath();
        System.out.println("DFS Robot Time : " + finalTime);
        System.out.println("DFS Robot Path Length : " + Dlength);

        //BFS robot
        resetGraphNodes();
        BreadthFirstSearch bfsRobot = new BreadthFirstSearch(graph.getTotalNodes(), graph);
        Robot BFSrobot = new Robot(graph, bfsRobot);
        System.out.println("BFS Robot");
        startTime = System.nanoTime();
        BFSrobot.runRobot(graph.getItemNodeList(), graph.getStartingNode());
        endTime = System.nanoTime();
        finalTime = endTime - startTime;
        int Blength = BFSrobot.printPath();
        System.out.println("BFS Robot Time : " + finalTime);
        System.out.println("BFS Robot Path Length : " + Blength);

        //Dijkstra robot
        resetGraphNodes();
        Dijkstra dijkRobot = new Dijkstra(graph);
        Robot DIJKSTRArobot = new Robot(graph, dijkRobot);
        System.out.println(" Dijkstra Robot");
        startTime = System.nanoTime();
        DIJKSTRArobot.runRobot(graph.getItemNodeList(), graph.getStartingNode());
        endTime = System.nanoTime();
        finalTime = endTime - startTime;
        int DijLength = DIJKSTRArobot.printPath();
        System.out.println("Dijkstra Robot Time : " + finalTime);
        System.out.println("Dijkstra Robot Path Length : " + DijLength);

        //A star robot
        resetGraphNodes();
        AStarSearch AstarRobot = new AStarSearch(graph);
        Robot ASTARrobot = new Robot(graph, AstarRobot);
        System.out.println("AStar Robot");
        startTime = System.nanoTime();
        ASTARrobot.runRobot(graph.getItemNodeList(), graph.getStartingNode());
        endTime = System.nanoTime();
        finalTime = endTime - startTime;
        int ALength = ASTARrobot.printPath();
        System.out.println("AStar Robot Time : " + finalTime);
        System.out.println("AStar Robot Path Length : " + ALength);


        // Prim Robot
        resetGraphNodes();
        Prims primsRobot = new Prims(graph);
        Robot PRIMrobot = new Robot(graph, prims);
        System.out.println(" Prim Robot");
        startTime = System.nanoTime();
        PRIMrobot.runRobot(graph.getItemNodeList(), graph.getStartingNode());
        endTime = System.nanoTime();
        finalTime = endTime - startTime;
        int PLength = PRIMrobot.printPath();
        System.out.println("Prim Robot Time : " + finalTime);
        System.out.println("Prim Robot Path Length : " + PLength);


//        /* This is a hard coded way of finding the average of a single
//            algorithm for the current file over 100 iterations calculating
//            a sample mean, min and max value for performance diagnostic
//         */
//        System.out.println("Mean time of Algorithm over 100 iterations");
//        int n = 100;// iterations
//        long totalTime = 0;
//        long max = Long.MIN_VALUE;
//        long min = Long.MAX_VALUE;
//        while (n > 0){
//            resetGraphNodes();// reset graph
//            startingNode = graph.getStartingNode();// new start
//            GraphNode targetNode = graph.getItemNodeList()[0];// new item
//            int index = 0;// items found
//            finalTime = 0;// local final time
//            // find all the items in the graph
//            while (index < graph.getItemCount()) {
//                resetGraphNodes();
//                startTime = System.nanoTime();
//                // change to algorithm for testing purposes
//                dfs.findShortest(graph,startingNode, targetNode);
//                endTime = System.nanoTime();
//                finalTime += endTime - startTime;// calculate sub time
//                index++;
//                startingNode = targetNode;// next starting node
//                targetNode = graph.getItemNodeList()[index];// next target
//            }
//            // after finding all items see if this is a final min
//            if(finalTime < min){
//                min = finalTime;
//            }
//            // see if this iteration is final max
//            if(finalTime > max){
//                max = finalTime;
//            }
//            // add up the local time to total time
//            totalTime += finalTime;
//            // one full iteration done
//            n --;
//
//        }
//        // calculate a min value
//        totalTime = totalTime / 100;
//        // print results
//        System.out.println(" avg time: "+ totalTime);
//        System.out.println(" min time: "+ min);
//        System.out.println(" max time: "+ max);

    }

    /**
     * For every node in the graph reset its visited nodes to false for next
     * Algorithm's use on the graph
     */
    private static void resetGraphNodes() {
        for (int i = 0; i < graph.getNodeList().length; i++) {
            graph.getNodeList()[i].setVisited(false);
            graph.getNodeList()[i].setParentNode(null);
        }
    }

    /**
     * Convert char to cellType
     *
     * @param input- input char from file
     * @return- cellType
     */
    private static CellType convert(char input) {
        if (input == 'I') {
            itemsFound++;
            return CellType.ITEM;
        } else if (input == 'O') {
            return CellType.OBSTACLE;
        } else {
            return CellType.EMPTY;
        }
    }

    /**
     * Print every cell type of board to get the visual representation
     */
    private static void printBoard() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}