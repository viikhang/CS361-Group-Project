package Algorithm;

public class Graph {

    private GraphNode[] newArr;

    private CellType[][] board;

    public Graph(CellType[][] board){
        this.board = board;
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
    public void setVertices(int row, int col, GraphNode node){
        //create 8 if statments here
//        if (row - 1 >= 0 && col - 1 >=0 && board[row - 1][col-1]== CellType.EMPTY ){
//            node.vertices(0) =
//        }else if

    for(int i = -1; i < board.length; i++){
        for(int j = 0; j < board[0].length; j++){
            if(i < 0 || j < 0 || i >= board.length || j >= board[0].length){
                continue;
            }
            if(board[i][j])
        }
    }

    }

    private boolean inbounds (int value){
        return value - 1 >=0;
    }
    private boolean inBoundsR(int value){
        return value + 1 <
    }





}
