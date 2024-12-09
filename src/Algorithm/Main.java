package Algorithm;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static CellType[][] grid;
    public static int itemsFound = 0;
    public static Graph graph;

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


        long startTime;
        long endTime;
        long finalTime;


        /**
         * Our Depth First Search Algorithm approach to finding a shortest path for
         * both single and multiple item paths, timed in NANO seconds.
//         */
        System.out.println("Depth First Search Algorithm");
        DepthFirstSearch dfs = new DepthFirstSearch(graph.getTotalNodes(),graph);
        int pathLengthD =0;
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            dfs.findShortest(graph,startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            pathLengthD += dfs.printPath();
            System.out.println("Depth First Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: "+ (pathLengthD));
        } else { //handling multiple items
            finalTime = 0;
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while(index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                dfs.findShortest(graph,startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                pathLengthD += dfs.printPath();
            }

            System.out.println("Depth First Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: "+ (pathLengthD - graph.getItemCount()));
        }
        System.out.println();

        /**
         * Our Breadth First Search Algorithm approach to finding a shortest path for
         * both single and multiple item paths, timed in NANO seconds.
         */
        System.out.println("Breadth First Search Algorithm");
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph.getTotalNodes(),graph);
        resetGraphNodes();
        int pathLengthB =0;
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            bfs.findShortest(graph,startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            pathLengthB += bfs.printPath();
            System.out.println("Breadth First Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: "+ (pathLengthB));
        } else { //handle multiple items
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                bfs.findShortest(graph,startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                pathLengthB +=bfs.printPath();
            }
            System.out.println("Breadth First Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: "+ (pathLengthB- graph.getItemCount()));
        }

        /**
         * Our Dijkstra's Search Algorithm approach to finding a shortest path for
         * both single and multiple item paths, timed in NANO seconds.
         */
        System.out.println();
        System.out.println("Dijkstra Algorithm");
        Dijkstra dijk = new Dijkstra(graph);
        resetGraphNodes();
        int pathLengthDij =0;
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            dijk.findShortest(graph,startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            pathLengthDij += dijk.printPath();
            System.out.println("Dijkstra Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: "+ (pathLengthDij));
        } else {
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
//                System.out.println("start" + startingNode);
//                System.out.println("target" + targetNode);
                startTime = System.nanoTime();
                dijk.findShortest(graph,startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];

                pathLengthDij += dijk.printPath();
            }

            System.out.println("Dijkstra Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: "+ (pathLengthDij- graph.getItemCount()));

        }

        /**
         * Our A* Search Algorithm approach to finding a shortest path for
         * both single and multiple item paths, timed in NANO seconds.
         */
        System.out.println();
        System.out.println("A* Algorithm");
        AStarSearch Astar = new AStarSearch(graph);
        resetGraphNodes();
        int pathLengthA =0;
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            Astar.findShortest(graph,startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            pathLengthA += Astar.printPath();
            System.out.println("A* Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: "+ (pathLengthA));
        } else {
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                Astar.findShortest(graph,startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                pathLengthA +=Astar.printPath();
            }

            System.out.println("A* Search Time (nano seconds): " + finalTime);
            System.out.println("Path length: "+ (pathLengthA- graph.getItemCount()));
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
        if(graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            prims.findShortest(graph,startingNode,graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            pathLengthP +=prims.printPath();
            System.out.println(" Prims Search Time (nano seconds): "+ finalTime);
            System.out.println("Path length: "+ pathLengthP);
        }
        else{
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                prims.findShortest(graph,startingNode,targetNode);
                endTime = System.nanoTime();

                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                pathLengthP +=prims.printPath();

            }
            System.out.println("Prims search Time (nano seconds): " + finalTime);
            System.out.println("Path length: "+ (pathLengthP - graph.getItemCount()));
        }
        System.out.println();
        System.out.println();
        resetGraphNodes();

        // Robot Time for every Algorithm
        //DFS Robot
        DepthFirstSearch dfsRobot = new DepthFirstSearch(graph.getTotalNodes(), graph);
        Robot DFSrobot = new Robot(graph,dfsRobot);
        System.out.println(" DFS Robot");
        startTime = System.nanoTime();
        DFSrobot.runRobot(graph.getItemNodeList(),graph.getStartingNode());
        endTime = System.nanoTime();
        finalTime = endTime - startTime;
        DFSrobot.printPath();
        System.out.println("DFS Robot Time : " + finalTime);

        //BFS robot
        resetGraphNodes();
        BreadthFirstSearch bfsRobot = new BreadthFirstSearch(graph.getTotalNodes(), graph);
        Robot BFSrobot = new Robot(graph,bfsRobot);
        System.out.println("BFS Robot");
        startTime = System.nanoTime();
        BFSrobot.runRobot(graph.getItemNodeList(),graph.getStartingNode());
        endTime = System.nanoTime();
        finalTime = endTime - startTime;
        BFSrobot.printPath();
        System.out.println("BFS Robot Time : " + finalTime);


        //Dijkstra robot
        resetGraphNodes();
        Dijkstra dijkRobot = new Dijkstra(graph);
        Robot DIJKSTRArobot = new Robot(graph,dijkRobot);
        System.out.println(" Dijkstra Robot");
        startTime = System.nanoTime();
        DIJKSTRArobot.runRobot(graph.getItemNodeList(),graph.getStartingNode());
        endTime = System.nanoTime();
        finalTime = endTime - startTime;
        DIJKSTRArobot.printPath();
        System.out.println("Dijkstra Robot Time : " + finalTime);




        //A star robot
        resetGraphNodes();
        AStarSearch AstarRobot = new AStarSearch(graph);
        Robot ASTARrobot = new Robot(graph,AstarRobot);
        System.out.println("AStar Robot");
        startTime = System.nanoTime();
        ASTARrobot.runRobot(graph.getItemNodeList(),graph.getStartingNode());
        endTime = System.nanoTime();
        finalTime = endTime - startTime;
        ASTARrobot.printPath();
        System.out.println("AStar Robot Time : " + finalTime);



        // Prim Robot
        resetGraphNodes();
        Prims primsRobot = new Prims(graph);
        Robot PRIMrobot = new Robot(graph,prims);
        System.out.println(" Prim Robot");
        startTime = System.nanoTime();
        PRIMrobot.runRobot(graph.getItemNodeList(),graph.getStartingNode());
        endTime = System.nanoTime();
        finalTime = endTime - startTime;
        PRIMrobot.printPath();
        System.out.println(" Prim Robot Time : " + finalTime);


        // little calculation for averaging times
        System.out.println("Average of Algorithm over 100 iterations");
        int n = 100;
        long totalTime = 0;
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        while (n > 0){
            resetGraphNodes();
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            finalTime = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                // change to algorithm for testing purposes
                AstarRobot.findShortest(graph,startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
            }
            if(finalTime < min){
                min = finalTime;
            }
            if(finalTime > max){
                max = finalTime;
            }

            totalTime += finalTime;
            n --;

        }
        totalTime = totalTime / 100;
        System.out.println(" avg time: "+ totalTime);
        System.out.println(" min time: "+ min);
        System.out.println(" max time: "+ max);

    }



    /**
        For every node in the graph reset its visited nodes to false for next
        Algorithm's use on the graph
     */
    private static void resetGraphNodes() {
        for (int i = 0; i < graph.getNodeList().length; i++) {
            graph.getNodeList()[i].setVisited(false);
            graph.getNodeList()[i].setParentNode(null);
        }
    }

    /**
     * Convert char to cellType
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
