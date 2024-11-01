package Algorithm;

public class Graph {

    private GraphNode[] newArr;

    private CellType[][] board;
    private int boardSize;

    public Graph(CellType[][] board) {
        this.board = board;
        boardSize = board.length;

        GraphNode[][] nodeBoard = new GraphNode[board.length][board[0].length];
        //initialize board of graphNodes
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                GraphNode node = new GraphNode();
                node.setCellType(board[i][j]);
                nodeBoard[i][j] = node;
            }
        }

        //set the vertices for every graphNode
        for (int i = 0; i < nodeBoard.length; i++) {
            for (int j = 0; j < nodeBoard[i].length; j++) {
                setVertices(i, j, nodeBoard[i][j], nodeBoard);
            }
        }
    }

    /**
     * This function looks in all directions and determines if a path exists
     * between vertices that is updated for the current node.
     * <p>
     * | 0 1 2
     * | 3 x 4
     * | 5 6 7
     *
     * @param row       - current row
     * @param col       - current col
     * @param node      - current node
     * @param nodeBoard - 2D array carrying graphNodes instead of cells
     */
    public void setVertices(int row, int col, GraphNode node, GraphNode[][] nodeBoard) {
        CellType obs = CellType.OBSTACLE;

        // * - -
        // - x -
        // - - -

        //top left vertices(0)
        if (inBoundL(row) && inBoundL(col) && nodeBoard[row - 1][col - 1].getCellType() != obs) {
            node.getVertices()[0] = nodeBoard[row - 1][col - 1];
        }

        // - * -
        // - x -
        // - - -

        // above vertices(1)
        if (inBoundL(row) && nodeBoard[row - 1][col].getCellType() != obs) {
            node.getVertices()[1] = nodeBoard[row - 1][col];
        }

        // - - *
        // - x -
        // - - -

        //top right vertices(2)
        if (inBoundL(row) && inBoundR(col) && nodeBoard[row - 1][col + 1].getCellType() != obs) {
            node.getVertices()[2] = nodeBoard[row - 1][col + 1];
        }
        // - - -
        // * x -
        // - - -

        //left vertices(3)
        if (inBoundL(col) && nodeBoard[row][col - 1].getCellType() != obs) {
            node.getVertices()[3] = nodeBoard[row][col - 1];
        }

        // - - -
        // - x *
        // - - -

        //right vertices(4)
        if (inBoundR(col) && nodeBoard[row][col + 1].getCellType() != obs) {
            node.getVertices()[4] = nodeBoard[row][col + 1];
        }

        // - - -
        // - x -
        // * - -

        //bottom left vertices(5)
        if (inBoundR(row) && inBoundL(col) && nodeBoard[row + 1][col - 1].getCellType() != obs) {
            node.getVertices()[5] = nodeBoard[row + 1][col - 1];
        }

        // - - -
        // - x -
        // - * -

        //below vertices(6)
        if (inBoundR(row) && nodeBoard[row + 1][col].getCellType() != obs) {
            node.getVertices()[6] = nodeBoard[row + 1][col];
        }

        // - - -
        // - x -
        // - - *

        //bottom right vertices(7)
        if (inBoundR(row) && inBoundR(col) && nodeBoard[row + 1][col + 1].getCellType() != obs) {
            node.getVertices()[7] = nodeBoard[row + 1][col + 1];
        }
    }

    private boolean inBoundL(int val) {
        return val - 1 >= 0;
    }

    private boolean inBoundR(int val) {
        return val + 1 < boardSize;
    }
}
