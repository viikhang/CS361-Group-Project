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
        //after creating the 2d array, call searching methods, pass the graph
        // and the total amount of items that are created
        graph = new Graph(grid);
        //pass graph array to each algorithm and then
        printBoard();
        GraphNode startingNode = graph.getStartingNode();

        System.out.println("Depth First Search");

        DepthFirstSearch dfs = new DepthFirstSearch(graph.getTotalNodes());

        if(graph.getItemCount() == 1) {
            dfs.findShortestPath(startingNode, graph.getItemNodeList()[0]);
            dfs.printPath();
            System.out.println("here");
        } else {
            //need to do more than one call
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
    private static void printBoard(){
        for(int i = 0 ; i < grid.length;i++ ){
            for(int j = 0; j < grid[0].length;j++){
                System.out.print(grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
