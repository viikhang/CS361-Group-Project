package Algorithm;

public class Graph {

    private GraphNode[] newArr;

    public Graph(CellType[][] board){

        newArr = new GraphNode[board.length * board[0].length];

        int startingIndex = 0;

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){

                GraphNode node = new GraphNode();
                //create a method that will check the surround 8 spots, then update the array in node if an empty node exists
                newArr[startingIndex] = node;
                startingIndex++;
            }
        }
    }





}
