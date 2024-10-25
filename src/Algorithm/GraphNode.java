package Algorithm;

public class GraphNode {
    CellType cellType = CellType.EMPTY;
    GraphNode[] vertices = new GraphNode[8];

    /**
     *
     */
    public GraphNode(){
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
}
