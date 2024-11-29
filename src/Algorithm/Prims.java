package Algorithm;

public class Prims {
    private Graph graph;
    private GraphNode[] shortestPath;
    private GraphNode[] parentNodes;
    private int[][] distances;// hold weights for node location on graph
    private GraphNode[][] predecessors;

    private int verticesCount;
    private int size;
    public Prims(Graph graph) {
        int rows = graph.getBoard().length;
        int cols = graph.getBoard()[0].length;
        predecessors = new GraphNode[rows][cols];
        distances = new int[rows][cols];
        size = graph.getTotalNodes();
        shortestPath = new GraphNode[size];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distances[i][j] = Integer.MAX_VALUE;

            }
        }

    }
    private void createMst(GraphNode start, GraphNode goal) {
        primMST(start);
    }
    public void primMST(GraphNode start) {
        //shortestPath[0] = start;
        start.setParentNode(null);

        MinHeap heap =  new MinHeap(size);
        heap.insert(start,0);
        while(!heap.isEmpty()){
            GraphNode current = heap.extractMin();

            if(current.isVisited()){
                continue;
            }
            current.setVisited(true);
            //add the current node to MST

            //process neighbors of current node
            for(GraphNode neighbor : current.getVertices()) {
                //get edge weight from current node to neighbor node
                int distance = 1; //change this to some actual weight value, if default wasn't 1
                //find the smallest
                if(neighbor != null && !neighbor.isVisited()) {
                    neighbor.setParentNode(current);
                    //since the weight for all nodes is only 1, we can hard code it
                    heap.insert(neighbor,distance);
                }
            }
        }
    }

    public void createPath(GraphNode target){

        int index = 0;
        GraphNode temp = target;
        while(temp != null) {
            shortestPath[index] = temp;
            temp = temp.getParentNode();
            index++;
        }


        int start = 0;
        int end = index -1;
        while(start < end) {
            temp = shortestPath[start];
            shortestPath[start] = shortestPath[end];
            shortestPath[end] = temp;
            start++;
            end--;
        }

        for (int i = 0; i < index; i++) {
            System.out.print(shortestPath[i]);
            if(i < index - 1){
                System.out.print(" -> ");
            }
        }

        //then need to reverse the path
    }
}
