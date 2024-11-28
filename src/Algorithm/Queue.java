package Algorithm;

public class Queue {
    GraphNode[] array;
//    int index = 0;

    public Queue() {
        array = new GraphNode[1000];
    }
    public boolean isEmpty(){
        for(GraphNode node : array){
            // might need to check a different way
            if(node != null){
                //System.out.println("Here");
                return false;
            }
        }
        return true;
    }

    public void add(GraphNode node){
        for(int i = 0; i < array.length; i++){
            if(array[i] == null){
                array[i] = node;
                break;
            }
        }
    }

    public GraphNode remove(){
        GraphNode node = array[0];
        createNewArray();
        return node;
    }

    private void createNewArray() {
        GraphNode[] newArray = new GraphNode[1000];
        for(int i = 1; i < array.length; i++) {
            newArray[i - 1] = array[i];
        }
        array = newArray;
    }


//        GraphNode[] newArray = new GraphNode[1000];
//        for(int i = 0; i < array.length; i++){
//            newArray[i] = array[i];
//            array[i] = null;
//        }
//
//        for(int i = 0; i < newArray.length; i++){
//            array[i] = newArray[i+1];
//        }
    }


