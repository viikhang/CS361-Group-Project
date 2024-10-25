package Algorithm;

import java.io.BufferedReader;
import java.io.FileReader;

public class HelloWorld {
    public static CellType[][] grid;
    public static void main(String[] args) {
        //read in a file here, then after creating the graph,
        if(args.length != 1){
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
                while((line = reader.readLine()) != null){
                    String[] cellTypes = line.split(" ");

                }
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
}
