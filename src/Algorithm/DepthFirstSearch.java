package Algorithm;

public class DepthFirstSearch {
    private GraphNode[] shortestPath;
    public void Dfs(Graph graph) {
        shortestPath = new GraphNode[graph.getTotalNodes()]; //array
        // containing the shortest path

        for(GraphNode node : graph.getNodeList()) {
            node.setColor(-1);
            //node.previous = -1;
        }
        int time = 0;
        for(GraphNode node : graph.getNodeList()){
            if(node.getColor() == -1){
                DfsVisit(node, graph.getNodeList(), time);
            }
        }
        return        //what are we returning for?
    }
    public void DfsVisit(GraphNode node, GraphNode[] array, int time){
        node.setColor(0);
        time += 1;
        //discoveryTime:
        d[u] = time;

        //Something here
        GraphNode[] AdjNodes = new GraphNode[array.length];
        transferElements(AdjNodes,findAdjacentNodes(node));
        //

        for(GraphNode adjNode : AdjNodes){
            if(node.getColor() == -1){
                DfsVisit(adjNode, array, time);
            }
        }
        node.setColor(1);
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
