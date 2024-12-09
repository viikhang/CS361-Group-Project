package Algorithm;

public class Graph {

    private final CellType[][] board; //Board containing warehouse cells
    private GraphNode[] nodeList; //List of nodes
    private GraphNode[][] adjList; //Nodes represented through adjacency  list

    private GraphNode[] itemNodeList; //List of nodes that contain items
    private int itemCount = 0; //Total item count
    private final int rowSize; //Row rize
    private final int colSize; //Column size
    private final int totalNodes; //Total nodes

    private GraphNode startingNode; //Starting node, left as top left corner

    /**
     * Constructor for Graph class, will create the nodes and assign the nodes
     * that connect to each other
     *
     * @param board (Board containing warehouse cells)
     */
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

    /**
     * Add item node to itemNode list
     *
     * @param node Item node being added
     */
    private void addItemNode(GraphNode node) {
        for (int i = 0; i < totalNodes; i++) {
            if (itemNodeList[i] == null) {
                itemNodeList[i] = node;
                break; //break once we find the first empty spot in the array
            }
        }
    }

    /**
     * Add node to nodeList
     *
     * @param node Node being added
     */
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

    /**
     * Return starting Node
     *
     * @return Starting node
     */
    public GraphNode getStartingNode() {
        return startingNode;
    }

    /**
     * Check if value is in bound
     *
     * @param val Value being checked
     * @return True if in bound, false if not
     */
    private boolean inBoundL(int val) {
        return val - 1 >= 0;
    }

    /**
     * Check if value is in bound based on given size
     *
     * @param val  Value being checked
     * @param size Value that cannot exceed this size
     * @return True if in bound, false if not
     */
    private boolean inBoundR(int val, int size) {
        return val + 1 < size;
    }

    /**
     * Get total items
     *
     * @return Number of items
     */
    public int getItemCount() {
        return itemCount;
    }

    /**
     * Get board representing warehouse
     *
     * @return Board
     */
    public CellType[][] getBoard() {
        return board;
    }

    /**
     * Get list containing nodes
     *
     * @return List containing items
     */
    public GraphNode[] getItemNodeList() {
        return itemNodeList;
    }

    /**
     * Get list of nodes
     *
     * @return List of nodes
     */
    public GraphNode[] getNodeList() {
        return nodeList;
    }

    /**
     * Get total number of nodes
     *
     * @return Number of nodes
     */
    public int getTotalNodes() {
        return totalNodes;
    }

}
