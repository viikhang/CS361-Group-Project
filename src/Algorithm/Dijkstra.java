package Algorithm;

public class Dijkstra {

    private int[][] distances;
    private GraphNode[][] predecessors;
    private boolean[][] visited;
    private GraphNode[] shortestPath;
    private int pathIndex;

    private int size;

    public Dijkstra(Graph graph) {
        size = graph.getTotalNodes();
        pathIndex = 0;
        shortestPath = new GraphNode[size];
        int rows = graph.getBoard().length;
        int cols = graph.getBoard()[0].length;
        predecessors = new GraphNode[rows][cols];
        distances = new int[rows][cols];
        visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                distances[i][j] = Integer.MAX_VALUE;
                visited[i][j] = false;
            }
        }

    }

    public void dijkstraShortestPath(GraphNode start, GraphNode target) {
        shortestPath = DIJ(start, target);
        int finalWeight = 0;
        for (GraphNode n : shortestPath) {
            finalWeight += distances[n.getRow()][n.getCol()];
        }
    }

    public GraphNode[] DIJ(GraphNode start, GraphNode target) {
        distances[start.getRow()][start.getCol()] = 0;
        visited[start.getRow()][start.getCol()] = true;
        predecessors[start.getRow()][start.getCol()] = start;

        MinHeap heap = new MinHeap(size);
        heap.insert(start, 0);


        while (!(heap.isEmpty())) {

            GraphNode current = heap.extractMin();

            if (current.isVisited()) {
                continue;
            }

            visited[current.getRow()][current.getCol()] = true;

            if (current == target) {
                //function that keeps track of path taken
                return pathBuilder(predecessors, start, target);
            }
            //relaxing
            for (int i = 0; i < 4; i++) {

                GraphNode neighbor = current.getVertices()[i];

                if (neighbor != null && !(visited[neighbor.getRow()][neighbor.getCol()])) {

                    int updatedDist = distances[current.getRow()][current.getCol()] + current.getNodeToVertexCost()[i];

                    if (updatedDist < distances[neighbor.getRow()][neighbor.getCol()]) {
                        distances[neighbor.getRow()][neighbor.getCol()] = updatedDist;
                        predecessors[neighbor.getRow()][neighbor.getCol()] = current;
                        heap.insert(neighbor, updatedDist);
                    }
                }
            }
        }

        return null;// no path
    }

    private GraphNode[] pathBuilder(GraphNode[][]predecessors, GraphNode start, GraphNode target){
        GraphNode[] path = new GraphNode[shortestPath.length];
        GraphNode curr = target;
        pathIndex = 0;
        //start from target and follow pred backwards to start.
        while(curr != null){
            path[pathIndex++]= curr;
            if(curr == start){
                break;
            }
            curr = predecessors[curr.getRow()][curr.getCol()];

        }
        //reverse path
        for(int i =0; i< pathIndex/2; i++){
            GraphNode temp = path[i];
            path[i] = path[pathIndex - i -1];
            path[pathIndex -i -1] = temp;
        }

        return path;
    }

    public void printPath() {
        for(int i = 0; i < shortestPath.length; i++){
            if(shortestPath[i] != null) {
                System.out.print(shortestPath[i]);
                if(i +1 < shortestPath.length && shortestPath[i + 1] != null) {
                    System.out.print(" -> ");
                }
            } else {
                break;
            }
        }
        System.out.println();
    }

}


