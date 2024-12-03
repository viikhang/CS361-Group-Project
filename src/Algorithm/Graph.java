package Algorithm;

public class Graph {

    private final CellType[][] board;
    private GraphNode[] nodeList;
    private GraphNode[][] adjList;

    private GraphNode[] itemNodeList;
    //use this array to know what items we need to find while traversing
    // through the graph
    private int itemCount = 0;
    private int[][] weightGrid;
    private final int rowSize;
    private int colSize;
    private final int totalNodes;

    private GraphNode startingNode;

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

        adjList = new GraphNode[board.length][board[0].length];
        //initialize board of graphNodes
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                GraphNode node = new GraphNode(i, j);
                node.setCellType(board[i][j]);
                adjList[i][j] = node;
            }
        }

        startingNode = adjList[0][0];


        //set the vertices for every graphNode
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                setVertices(i, j, adjList[i][j], adjList);
            }
        }
        //create list of items that need to be found
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (adjList[i][j].getCellType() == CellType.ITEM) {
                    addItemNode(adjList[i][j]); //add item node to array
                    itemCount++;
                }
                addNode(adjList[i][j]); //add node to list containing all
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

    private void addNode(GraphNode node) {
        for (int i = 0; i < totalNodes; i++) {
            if (nodeList[i] == null) {
                nodeList[i] = node;
                break;
            }
        }
    }

    /**
     * This function looks in all directions and determines if a path exists
     * between vertices that is updated for the current node.
     * <p>
     * | - 0 -
     * | 1 x 2
     * | - 3 -
     *
     * @param row       - current row
     * @param col       - current col
     * @param node      - current node
     * @param nodeBoard - 2D array carrying graphNodes instead of cells
     */
    public void setVertices(int row, int col, GraphNode node, GraphNode[][] nodeBoard) {
        CellType obs = CellType.OBSTACLE;

        // - * -
        // - x -
        // - - -

        // above vertices(1)
        if (inBoundL(row) && nodeBoard[row - 1][col].getCellType() != obs) {
            node.getVertices()[0] = nodeBoard[row - 1][col];
            node.getNodeToVertexCost()[0] = 1;
        }

        // - - -
        // * x -
        // - - -

        //left vertices(1)
        if (inBoundL(col) && nodeBoard[row][col - 1].getCellType() != obs) {
            node.getVertices()[1] = nodeBoard[row][col - 1];
            node.getNodeToVertexCost()[1] = 1;
        }

        // - - -
        // - x *
        // - - -

        //right vertices(2)
        if (inBoundR(col, colSize) && nodeBoard[row][col + 1].getCellType() != obs) {
            node.getVertices()[2] = nodeBoard[row][col + 1];
            node.getNodeToVertexCost()[2] = 1;
        }


        // - - -
        // - x -
        // - * -

        //below vertices(3)
        if (inBoundR(row, rowSize) && nodeBoard[row + 1][col].getCellType() != obs) {
            node.getVertices()[3] = nodeBoard[row + 1][col];
            node.getNodeToVertexCost()[3] = 1;
        }

    }

    public GraphNode[][] getAdjList() {
        return adjList;
    }

    public GraphNode getStartingNode() {
        return startingNode;
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

    public int getItemCount() {
        return itemCount;
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
