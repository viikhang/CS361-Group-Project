package Algorithm;

public class GraphNode {
    CellType cellType = CellType.EMPTY;
    GraphNode[] vertices = new GraphNode[8];

    /**
     *
     */
    public GraphNode(){
        GraphNode[] node = new GraphNode[8];
        for(int i = 0; i < 8; i++){
            node[i] = null;
        }
    }
}
