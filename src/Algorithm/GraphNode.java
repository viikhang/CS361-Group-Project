package Algorithm;

public class GraphNode {
    private CellType cellType = CellType.EMPTY;
    private GraphNode[] vertices = new GraphNode[8];
    private int[] nodeToVertexCost = new int[8];
    private int color;
    private int xCord;
    private int yCord;

    public GraphNode(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
        for(int i = 0; i < 8; i++){
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
