package Algorithm;
/** min heap for Greedy algorithm
    MinHeap functions include swap,insert, isEmpty, HeapifyUp, ExtractMin
 */
public class MinHeap {
    private GraphNode[] heap;// array of graphNodes
    private int[] distances;// array of weights coorisponding to nodes

    private int size;// max size of head based on vertices
    /*
        Constructor class initializing heap
     */
    public MinHeap(int size){
        heap = new GraphNode[size];
        distances = new int[size];
        this.size = 0;// indexing variable
    }

    /**
     * Insert adds node to heap and insures minheap property after
     *
     * @param node - node for insertion
     * @param distance- weight associated with accessing that node
     */
    public void insert(GraphNode node, int distance){
        heap[size] = node;
        distances[size] = distance;
        heapifyUp(size);
        size++;
    }

    /**
     * extractMin extracts the root from heap and creates new heap from
     * remaining elements.
     * @return- root of heap with min distance
     */
    public GraphNode extractMin(){
        // heap is empty
        if (size == 0){
            return null;
        }
        // the root is set to local variable
        GraphNode minVertex = heap[0];
        // remove root distance from the distances array
        distances[0] = distances[size -1];
        size--;// index is one less

        // new heap without root
        GraphNode[] newHeap = new GraphNode[heap.length];

        if(heap.length > 1) {
            for (int i = 1; i < heap.length; i++) {
                newHeap[i - 1] = heap[i];
            }
        }
        //previous heap to newly made heap
        heap = newHeap;
        // return previous heap
        return minVertex;
    }


    /*
        boolean value to ensure heap is not null
     */
    public boolean isEmpty(){
        return (size == 0);
    }

    /**
     * heapifyUp ensures that any changes to the heap retain heap property
     * min Heap property is just that every child of a node is greater than
     * the parent node. And that the root is the lowest node.
     * @param index- where we inserted a node
     */
    private void heapifyUp(int index){
        while(index > 0){// while its not the root
            int parent = (index -1)/2;// index of parent from bin tree structure
            if (distances[index] < distances[parent]){//child < parent
                swap(index, parent);
            }else{// heap still holds
                break;
            }
        }
    }


    /**
     * Swap-> swaps two elements in the min heap
     * @param i- first element
     * @param j- second
     */
    private void swap( int i, int j){
        GraphNode temp = heap[i];//temp node
        int tempDist = distances[i];//temp distance
        heap[i] = heap[j];//swap j with i
        distances[i] = distances[j];
        distances[j] = tempDist; // update j with temp
        heap[j] = temp;
    }


}
