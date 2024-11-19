package Algorithm;

public class GraphNode {
    private CellType cellType = CellType.EMPTY;
    private final GraphNode[] vertices = new GraphNode[4];
    private final int[] nodeToVertexCost = new int[4];
    private int color;
    private final int xCord;
    private final int yCord;
    private boolean visited;

    public GraphNode(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
        for(int i = 0; i < 4; i++){
            vertices[i] = null;
        }
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    public CellType getCellType() {
        return cellType;
    }

    public GraphNode[] getVertices() {
        return vertices;
    }

    public int[] getNodeToVertexCost() {
        return nodeToVertexCost;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "(" + xCord + " , " + yCord + ")";
    }

}
