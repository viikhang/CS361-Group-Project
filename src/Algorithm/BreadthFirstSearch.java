package Algorithm;


import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch {
    private GraphNode[] shortestPath;
    private int pathIndex;
    Graph graph;

    public BreadthFirstSearch(int size) {
        shortestPath = new GraphNode[size];
        pathIndex = 0;
    }

    public void findShortestPath(GraphNode start, GraphNode target) {
        shortestPath = BFS(start, target);
    }

    public GraphNode[] BFS(GraphNode start, GraphNode target) {
        if(bfsHelper(start,target,shortestPath)){
            return shortestPath;
        }

        return null; //no path was found
    }
    public boolean bfsHelper(GraphNode current, GraphNode target, GraphNode[] shortestPath) {
        // checks if current node is null
        //might remove
        if(current ==  null){
            return false;
        }

        // might remove this
        current.setVisited(true);
        addNode(current);

        // create queue
        Queue<GraphNode> queue = new LinkedList<>();

        // checks if current node works
        if(current == target){
            return true;
        }
        // adds current to the queue
        queue.add(current);


        while(!queue.isEmpty()){
            GraphNode u = queue.remove();
            for(GraphNode v : u.getVertices()){
                if(v != null && !v.isVisited()){
                    v.setVisited(true);
                    queue.add(v);
                    addNode(current);

                }
                if(v == target){
                    return true;
                }
            }
//            removeNodeFromPath();
        }
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
            System.out.println(shortestPath[i]);
//            if(shortestPath[i] != null) {
//                System.out.print(shortestPath[i]);
//                if(i + 1 < shortestPath.length && shortestPath[i + 1] != null) {
//                    System.out.print(" -> ");
//                }
//            } else {
//                break;
//            }
        }
        System.out.println();
    }

}



/// BFS Initialization
//BreadthFirstSearch bfs = new BreadthFirstSearch(graph.getTotalNodes());
//        if(graph.getItemCount() == 1) {
//        bfs.findShortestPath(startingNode, graph.getItemNodeList()[0]);
////            bfs.printPath();
//        } else {
//        //need to do more than one call
//        }
///

