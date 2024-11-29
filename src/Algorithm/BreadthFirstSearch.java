package Algorithm;

import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirstSearch {
    private GraphNode[] shortestPath;
    private int pathIndex;

    private int size;

    private GraphNode startNode;
    private GraphNode finalNode;

    private GraphNode[] parentNodes = new GraphNode[15*15];
    private int parentIndex = 0;

    public BreadthFirstSearch(int size) {
        this.size = size;
        shortestPath = new GraphNode[size];
        pathIndex = 0;
    }

    public void findShortestPath(GraphNode start, GraphNode target) {
        pathIndex = 0;
        shortestPath = new GraphNode[size];
        shortestPath = BFS(start, target);
    }

    public GraphNode[] BFS(GraphNode start, GraphNode target) {
        if (bfsHelper(start, target, shortestPath)) {
            return shortestPath;
        }

        return null; //no path was found
    }

    public boolean bfsHelper(GraphNode current, GraphNode target, GraphNode[] shortestPath) {
        // checks if current node is null
        //might remove
        if (current == null) {
            return false;
        }

        // might remove this
        current.setVisited(true);
        addNode(current);
        startNode = current;

        // create queue
        Algorithm.Queue queue = new Algorithm.Queue();


        // checks if current node works
        if (current == target) {
            return true;
        }
        // adds current to the queue
        queue.add(current);


        while (!queue.isEmpty()) {
            GraphNode u = queue.remove();
            for (GraphNode v : u.getVertices()) {
                if (v != null && !v.isVisited()) {
                    v.setParentNode(u);
                    parentNodes[parentIndex++] = v;
                    v.setVisited(true);
                    addNode(v);
                    queue.add(v);
                    //System.out.println(v);
                }
                if (v == target) {
                    finalNode = v;
                    //System.out.println("in queue found");
                    /// checks elements of the list
//                    while(!queue.isEmpty()){
//                        System.out.println(queue.remove());
//                    }
                    ///
                    return true;
                }
            }
//            removeNodeFromPath();
        }
        return false;
    }


    private void addNode(GraphNode node) {
        if (pathIndex < shortestPath.length) {
            shortestPath[pathIndex] = node;
            pathIndex++;
        }
    }


    public void printPath() {

//        System.out.print(startNode);
//        for(int i = 0; i < parentNodes.length; i++) {
//            if(parentNodes[i] == null){
//                break;
//            }
//            System.out.print(" -> ");
//            System.out.print(parentNodes[i]);
//
//        }
        int depth = 0;
        GraphNode temp1 = finalNode;
        while (temp1 != null) {
            depth++;
            temp1 = temp1.getParentNode();
        }

        GraphNode[] reverse = new GraphNode[depth];



        int index = reverse.length - 1;
        GraphNode temp2 = finalNode;
        while(temp2 != null && index >= 0){
            reverse[index--] = temp2;
            temp2 = temp2.getParentNode();
        }

        for (int i = 0; i < reverse.length; i++) {
            System.out.print(reverse[i]);
            if(i < reverse.length - 1){
                System.out.print(" -> ");
            }


        }


        // for loop resets the parent node values of previously used nodes
        for(GraphNode node : parentNodes){
            if(node == null){
                break;
            }
            node.setParentNode(null);
        }

        System.out.println();

        ///Here is previously used print statement

//        for (int i = 0; i < shortestPath.length; i++) {
//            if (shortestPath[i] != null) {
//                System.out.print(shortestPath[i]);
//                if (i + 1 < shortestPath.length && shortestPath[i + 1] != null) {
//                    System.out.print(" -> ");
//                }
//            } else {
//                break;
//            }
//        }
//        System.out.println();
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

