package Algorithm;

public class DepthFirstSearch {



    //-1 , 0 , 1
//    GraphNode[][] path =



    public void Dfs(Graph graph){



        for(GraphNode node : graph.getBoard()) {
            node.color = -1;
            //node.previous = -1;
        }
        int time = 0;
        for(GraphNode node : graph.getBoard()){
            if(node.color = -1){
                DfsVisit(node, graph.getBoard(), time);
            }
        }

        return

    }
    public void DfsVisit(GraphNode node, GraphNode[] array, int time){
        node.color = 0;
        time += 1;
        //discoveryTime:
        d[u] = time;

        //Something here
        GraphNode[] AdjNodes = new GraphNode[array.length];
        transferElements(AdjNodes,findAdjacentNodes(node));
        //

        for(GraphNode adjNode : AdjNodes){
            if(node.color == -1){
                DfsVisit(adjNode, array, time);
            }
        }
        node.color = 1;
        time = time + 1;
        //finalTime:
        f[u] = time;

        //Add node to path of visitedNodes

    }


    //find adjacent nodes
    public GraphNode[] findAdjacentNodes(GraphNode node){
        GraphNode[] AdjNodes = new GraphNode[ /** example number **/ ];


        return AdjNodes;
    }

    public void transferElements(GraphNode[] transTo, GraphNode[] transFrom){
        for(int i = 0; i < transTo.length; i++){
            transTo[i] = transFrom[i];
        }
    }
}
