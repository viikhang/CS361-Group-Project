package Algorithm;

public class Graph {

    private final CellType[][] board;
    private GraphNode[] nodeList;

    private GraphNode[] itemNodeList;
    //use this array to know what items we need to find while traversing
    // through the graph
    private int[][] weightGrid;
    private final int rowSize;
    private int colSize;
    private final int totalNodes;

    public Graph(CellType[][] board) {
        this.board = board;
        rowSize = board.length;
        colSize = board[0].length;
        totalNodes = rowSize * colSize;
        itemNodeList = new GraphNode[totalNodes];
        nodeList = new GraphNode[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            itemNodeList[i] = null; //set whole list to null
            nodeList[i] = null;
        }

        GraphNode[][] nodeBoard = new GraphNode[board.length][board[0].length];
        //initialize board of graphNodes
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                GraphNode node = new GraphNode(i,j);
                node.setCellType(board[i][j]);
                nodeBoard[i][j] = node;
            }
        }

        //set the vertices for every graphNode
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                setVertices(i, j, nodeBoard[i][j], nodeBoard);
            }
        }
        //create list of items that need to be found
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (nodeBoard[i][j].getCellType() == CellType.ITEM) {
                    addItemNode(nodeBoard[i][j]); //add item node to array
                }
                addNode(nodeBoard[i][j]); //add node to list containing all
                // nodes
            }
        }

    }

    private void addItemNode(GraphNode node) {
        for (int i = 0; i < totalNodes; i++) {
            if (itemNodeList[i] == null) {
                itemNodeList[i] = node;
                break; //break once we find the first empty spot in the array
            }
        }
    }
    private void addNode(GraphNode node){
        for(int i = 0; i < totalNodes; i++){
            if(nodeList[i] == null){
                nodeList[i] = node;
                break;
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
            node.getNodeToVertexCost()[0] = 1; //set the weight
        }

        // - * -
        // - x -
        // - - -

        // above vertices(1)
        if (inBoundL(row) && nodeBoard[row - 1][col].getCellType() != obs) {
            node.getVertices()[1] = nodeBoard[row - 1][col];
            node.getNodeToVertexCost()[1] = 1;
        }

        // - - *
        // - x -
        // - - -

        //top right vertices(2)
        if (inBoundL(row) && inBoundR(col, colSize) && nodeBoard[row - 1][col + 1].getCellType() != obs) {
            node.getVertices()[2] = nodeBoard[row - 1][col + 1];
            node.getNodeToVertexCost()[2] = 1;
        }
        // - - -
        // * x -
        // - - -

        //left vertices(3)
        if (inBoundL(col) && nodeBoard[row][col - 1].getCellType() != obs) {
            node.getVertices()[3] = nodeBoard[row][col - 1];
            node.getNodeToVertexCost()[3] = 1;
        }

        // - - -
        // - x *
        // - - -

        //right vertices(4)
        if (inBoundR(col, colSize) && nodeBoard[row][col + 1].getCellType() != obs) {
            node.getVertices()[4] = nodeBoard[row][col + 1];
            node.getNodeToVertexCost()[4] = 1;
        }

        // - - -
        // - x -
        // * - -

        //bottom left vertices(5)
        if (inBoundR(row, rowSize) && inBoundL(col) && nodeBoard[row + 1][col - 1].getCellType() != obs) {
            node.getVertices()[5] = nodeBoard[row + 1][col - 1];
            node.getNodeToVertexCost()[5] = 1;
        }

        // - - -
        // - x -
        // - * -

        //below vertices(6)
        if (inBoundR(row, rowSize) && nodeBoard[row + 1][col].getCellType() != obs) {
            node.getVertices()[6] = nodeBoard[row + 1][col];
            node.getNodeToVertexCost()[6] = 1;
        }

        // - - -
        // - x -
        // - - *

        //bottom right vertices(7)
        if (inBoundR(row, rowSize) && inBoundR(col, colSize) && nodeBoard[row + 1][col + 1].getCellType() != obs) {
            node.getVertices()[7] = nodeBoard[row + 1][col + 1];
            node.getNodeToVertexCost()[7] = 1;
        }
    }

    private boolean inBoundL(int val) {
        return val - 1 >= 0;
    }

    private boolean inBoundR(int val, int size) {
        return val + 1 < size;
    }

    public void setItemNodeList(GraphNode[] itemNodeList) {
        this.itemNodeList = itemNodeList;
    }

    public CellType[][] getBoard() {
        return board;
    }

    public GraphNode[] getItemNodeList() {
        return itemNodeList;
    }

    public GraphNode[] getNodeList() {
        return nodeList;
    }

    public int[][] getWeightGrid() {
        return weightGrid;
    }

    public int getTotalNodes() {
        return totalNodes;
    }
}
