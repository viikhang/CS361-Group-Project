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


        System.out.println("Depth First Search Algorithm");
        DepthFirstSearch dfs = new DepthFirstSearch(graph.getTotalNodes());
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            dfs.findShortestPath(startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            dfs.printPath();
            finalTime = endTime - startTime;
            System.out.println("Depth First Search Time (nano seconds): " + finalTime);
        } else { //handling multiple items
            finalTime = 0;
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while(index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                dfs.findShortestPath(startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                dfs.printPath();
            }
            System.out.println("Depth First Search Time (nano seconds): " + finalTime);
        }
        System.out.println();


        System.out.println("Breadth First Search Algorithm");
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph.getTotalNodes());
        resetGraphNodes();
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            bfs.findShortestPath(startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            bfs.printPath();
            finalTime = endTime - startTime;
            System.out.println("Breadth First Search Time (nano seconds): " + finalTime);
        } else { //handle multiple items
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                bfs.findShortestPath(startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                bfs.printPath();
            }
            System.out.println("Breadth First Search Time (nano seconds): " + finalTime);
        }

        System.out.println();


        System.out.println("Dijkstra Algorithm");
        Dijkstra diji = new Dijkstra(graph);
        resetGraphNodes();
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            diji.dijkstraShortestPath(startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            diji.printPath();
            System.out.println("Dijkstra Search Time (nano seconds): " + finalTime);
        } else {
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                diji.dijkstraShortestPath(startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                diji.printPath();
            }
            System.out.println("Dijkstra Search Time (nano seconds): " + finalTime);

        }

        System.out.println();

        System.out.println("A* Algorithm");
        AStarSearch Astar = new AStarSearch(graph);
        resetGraphNodes();
        if (graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            Astar.AStarShortestPath(startingNode, graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            Astar.printPath();
            System.out.println("A* Search Time (nano seconds): " + finalTime);
        } else {
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                Astar.AStarShortestPath(startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                Astar.printPath();
            }
            System.out.println("A* Search Time (nano seconds): " + finalTime);

        }


        System.out.println(" ");
        System.out.println("Modified Prims Algorithm");
        //TODO, TEST THIS
        Prims prims = new Prims(graph);
        resetGraphNodes();
        if(graph.getItemCount() == 1) {
            startTime = System.nanoTime();
            prims.primMST(startingNode,graph.getItemNodeList()[0]);
            endTime = System.nanoTime();
            finalTime = endTime - startTime;
            prims.printPath();
            System.out.println(" Prims Search Time (nano seconds): "+ finalTime);

        }
        else{
            finalTime = 0;
            startingNode = graph.getStartingNode();
            GraphNode targetNode = graph.getItemNodeList()[0];
            int index = 0;
            while (index < graph.getItemCount()) {
                resetGraphNodes();
                startTime = System.nanoTime();
                prims.primMST(startingNode, targetNode);
                endTime = System.nanoTime();
                finalTime += endTime - startTime;
                index++;
                startingNode = targetNode;
                targetNode = graph.getItemNodeList()[index];
                prims.printPath();

            }
            System.out.println("Prims search Time (nano seconds): " + finalTime);
        }
    }




    private static void resetGraphNodes() {
        for (int i = 0; i < graph.getNodeList().length; i++) {
            graph.getNodeList()[i].setVisited(false);
        }
    }

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
