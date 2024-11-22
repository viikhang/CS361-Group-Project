package Algorithm;


import java.util.Queue;


public class BreadthFirstSearch {
    private GraphNode[] shortestPath;
    private int pathIndex;
    Graph graph;
    public BreadthFirstSearch(Graph graph){
        this.graph = graph;
        shortestPath = new GraphNode[graph.getSize()];
        pathIndex = 0;
    }
    public void findShortestPath(GraphNode start, GraphNode target) {
        shortestPath = BFS(start,target);
    }


    public GraphNode[] BFS(GraphNode start, GraphNode target) {
//        if(bfsHelper(start,target,shortestPath)){
//            return shortestPath;
//        }
//
//        return null; //no path was found
        for (GraphNode neighbor : node.getVertices()) {
            color[]u = white;
            d[u] = infin;
            time[u] = null;
        }


        color[s] = gray;
        d[s] = 0;
        time[s] = null;
        Queue queue = null;
        enqueue(Q,s);
        while(!queue.isEmpty()){
            u = dequeue(queue);
            for(GraphNode neighbor : adjacentNodes.getVertices()) {
                if(color[v] == white){
                    color[v] = gray;
                    d[v] = d[u] + 1;
                    time[v] = u;
                    enqueue(Q,v);


                }
            }
            color[u] = black;
        }


    }
    public boolean bfsHelper(GraphNode current, GraphNode target, GraphNode[] shortestPath) {
        if(current ==  null){
            return false;
        }
        current.setVisited(true);
        addNode(current);
        if(current == target){
            return true;
        }


        for(GraphNode neighbor : current.getVertices()) {
            if(neighbor != null && !neighbor.isVisited()){
                if(bfsHelper(neighbor,target,shortestPath)){
                    return true;
                }
            }
        }
        removeNodeFromPath();
        return false;
    }
    private void addNode(GraphNode node){
        if(pathIndex < shortestPath.length){
            shortestPath[pathIndex] = node;
            pathIndex++;
        }
    }


    private void removeNodeFromPath(){
        if(pathIndex > 0){
            shortestPath[pathIndex] = null;
            pathIndex--;
        }
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

