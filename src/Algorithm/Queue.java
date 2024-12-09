package Algorithm;

public class Queue {
    GraphNode[] array; // array of graph nodes held for the queue

    /**
     * constructor for the queue list
     */
    public Queue() {
        array = new GraphNode[1000];
    }

    /**
     * checks if queue is empty
     * @return boolean value determining array emptiness
     */
    public boolean isEmpty() {
        for (GraphNode node : array) {
            // might need to check a different way
            if (node != null) {
                //System.out.println("Here");
                return false;
            }
        }
        return true;
    }

    /**
     * adds given node
     * @param node: node to add to queue
     */
    public void add(GraphNode node) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = node;
                break;
            }
        }
    }

    /**
     * removes first node of array
     * @return removed graph node
     */
    public GraphNode remove() {
        GraphNode node = array[0];
        createNewArray();
        return node;
    }

    /**
     * pushes elements of arrays down to maintain stack properties.
     */
    private void createNewArray() {
        GraphNode[] newArray = new GraphNode[1000];
        for (int i = 1; i < array.length; i++) {
            newArray[i - 1] = array[i];
        }
        array = newArray;
    }
}


