package Algorithm;

public class GraphNode {

    private GraphNode parentNode;

    private CellType cellType = CellType.EMPTY; //Default set as empty node
    private final GraphNode[] vertices = new GraphNode[4]; //Vertices the node connects to
    private final int[] nodeToVertexCost = new int[4]; //Cost to neighbor
    private final int xCord; //X coordinate
    private final int yCord; //Y coordinate
    private boolean visited; //If node has been visited already

    /**
     * Graph Node constructor
     *
     * @param xCord (X coordinate)
     * @param yCord (Y coordinate)
     */
    public GraphNode(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
        for (int i = 0; i < 4; i++) {
            vertices[i] = null;
        }
    }

    /**
     * Get parent node
     *
     * @return (Parent node)
     */
    public GraphNode getParentNode() {
        return parentNode;
    }

    /**
     * Set Parent node
     *
     * @param parentNode Node parent
     */
    public void setParentNode(GraphNode parentNode) {
        this.parentNode = parentNode;
    }

    /**
     * Set cell type
     *
     * @param cellType Three types of possible cells
     */
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    /**
     * Get cell type
     *
     * @return Cell Type
     */
    public CellType getCellType() {
        return cellType;
    }

    /**
     * Return list of vertices
     *
     * @return List of vertices
     */
    public GraphNode[] getVertices() {
        return vertices;
    }

    /**
     * List containing weight cost to neighbor
     *
     * @return List of weight costs
     */
    public int[] getNodeToVertexCost() {
        return nodeToVertexCost;
    }

    /**
     * Check if node has been visited
     *
     * @return True if visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Set node to visited or not visited
     *
     * @param visited True if visited
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * Get row value of node
     *
     * @return X coordinate
     */
    public int getRow() {
        return xCord;
    }

    /**
     * Get column value of node
     *
     * @return Y coordinate
     */
    public int getCol() {
        return yCord;
    }

    /**
     * Print x and y coordinate
     *
     * @return String representation of Node
     */
    @Override
    public String toString() {
        return "(" + yCord + " , " + xCord + ")";
    }

    /**
     * Used for temp node in order to indicate robot is going backwards
     *
     * @return String
     */
    public String goingBack() {
        return "(Traversing back to start)";
    }
}
