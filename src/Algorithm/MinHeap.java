package Algorithm;
// min heap for dijkstra's algorithm so I can achieve O(VlogV + E logV)
public class MinHeap {
    private GraphNode[] heap;
    private int[] distances;

    private int size;

    public MinHeap(int size){
        heap = new GraphNode[size];
        distances =new int[size];
        size = 0;

    }

    public void insert(GraphNode node, int distance){
        heap[size] = node;
        distances[size] = distance;
        heapifyUp(size);
        size ++;
    }

    public GraphNode extractMin(){
        if (size == 0){
            return null;
        }

        GraphNode minVertex = heap[0];
        distances[0] = distances[size -1];
        size --;
        heapifyDown(0);

        return minVertex;
    }

    public void setDistance(GraphNode node, int newDistance){
        for(int i = 0; i < size; i++){
            if(heap[i] == node){
                distances[i] = newDistance;
                heapifyUp(i);
                break;
            }
        }
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    private void heapifyUp(int index){
        while(index > 0){
            int parent = (index -1)/2;
            if (distances[index] < distances[parent]){
                swap(index, parent);
            }else{
                break;
            }
        }
    }

    private void heapifyDown(int index){
        while(index < size){
            int left =  2 * index +1;
            int right = 2 * index +2;
            int min = index;

            if(left < min && distances[left]< distances[min]){
                min = left;
            }
            if(right< min && distances[right]< distances[min]){
                min = right;
            }
            if(min != index){
                swap(index,min);
                index = min;
            }
            else{
                break;
            }
        }

    }

    private void swap( int i, int j){
        GraphNode temp = heap[i];
        int tempDist = distances[i];
        //swap j with i
        heap[i] = heap[j];
        distances[i] = distances[j];
        // update j with temp
        distances[j] = tempDist;
        heap[j] = temp;
    }
}
