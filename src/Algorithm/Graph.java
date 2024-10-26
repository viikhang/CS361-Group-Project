package Algorithm;

public class Graph {

    private GraphNode[] newArr;

    private CellType[][] board;

    public Graph(CellType[][] board){
        this.board = board;
        //newArr = new GraphNode[board.length * board[0].length];

        //int startingIndex = 0;
        GraphNode[][] nodeBoard = new GraphNode[board.length][board[0].length];
        //initialize board of graphNodes
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){

                GraphNode node = new GraphNode();
                node.cellType = board[i][j];
                nodeBoard[i][j] = node;

                //newArr[startingIndex] = node;
                //startingIndex++;

            }
        }
        //set the verticies for every graphNode
        for(int i = 0; i < nodeBoard.length; i++){
            for(int j = 0; j < nodeBoard[i].length; j++){
                setVertices(i,j,nodeBoard[i][j],nodeBoard);

            }
        }

    }

    /**
     * This function looks in all directions and determines if a path exists
     * between vertices that is updated for the current node.
     *
     * @param row - current row
     * @param col - current col
     * @param node - current node
     * @param nodeBoard - 2D array carrying graphNodes instead of cells
     */
    public void setVertices(int row, int col, GraphNode node, GraphNode[][] nodeBoard){

        //top left vertices(0)
        if(inBounds(row-1,col-1,nodeBoard) &&
                nodeBoard[row -1][col -1].cellType != CellType.OBSTACLE){
            node.vertices[0] = nodeBoard[row -1][col -1];
        }else{ node.vertices[0] = null;}

        // above vertices(1)
        if(inBounds(row -1 ,col,nodeBoard) &&
                nodeBoard[row - 1][col].cellType != CellType.OBSTACLE){
            node.vertices[1] = nodeBoard[row -1][col];
        }else{ node.vertices[1] = null;}

        //top right vertices(2)
        if(inBounds(row - 1,col + 1,nodeBoard) &&
                nodeBoard[row - 1][col + 1].cellType != CellType.OBSTACLE){
            node.vertices[2] = nodeBoard[row -1][col +1];
        }else{ node.vertices[2] = null;}

        //left vertices(3)
        if(inBounds(row,col - 1,nodeBoard) &&
                nodeBoard[row][col - 1].cellType != CellType.OBSTACLE){
            node.vertices[3] = nodeBoard[row][col -1];
        }else{ node.vertices[3] = null;}

        //right vertices(4)
        if(inBounds(row,col + 1,nodeBoard) &&
                nodeBoard[row ][col + 1].cellType != CellType.OBSTACLE){
            node.vertices[4] = nodeBoard[row][col + 1];
        }else{ node.vertices[4] = null;}

        //bottom left vertices(5)
        if(inBounds(row + 1,col - 1,nodeBoard) &&
                nodeBoard[row + 1][col - 1].cellType != CellType.OBSTACLE){
            node.vertices[5] = nodeBoard[row + 1][col -1];
        }else{ node.vertices[5] = null;}

        //below vertices(6)
        if(inBounds(row + 1,col,nodeBoard) &&
                nodeBoard[row + 1][col].cellType != CellType.OBSTACLE){
            node.vertices[6] = nodeBoard[row +1][col];
        }else{ node.vertices[6] = null;}

        //bottom right vertices(7)
        if(inBounds(row + 1,col + 1,nodeBoard) &&
                nodeBoard[row + 1][col + 1].cellType != CellType.OBSTACLE){
            node.vertices[7] = nodeBoard[row + 1][col + 1];
        }else{ node.vertices[7] = null;}


    /*for(int i = -1; i < board.length; i++){
        for(int j = 0; j < board[0].length; j++){
            if(i < 0 || j < 0 || i >= board.length || j >= board[0].length){
                continue;
            }
            if(board[i][j])
        }
    }

     */

    }

    /**
     *  boolean for setVertices function
     * @param row- current row
     * @param col - current col
     * @param array- graph node array
     * @return- true if in bounds false if not
     */
    private boolean inBounds(int row, int col, GraphNode[][] array){
        return row >= 0 && row < array.length && col >= 0 && col < array[0].length;

    }






}
