package Algorithm;

public enum CellType {

    EMPTY('.'),
    OBSTACLE('O'),
    ITEM('I');

    private char symbol;

    CellType(char symbol){
        this.symbol = symbol;
    }

    public char getSymbol(){
        return symbol;
    }

    @Override
    public String toString() {
        if(this == EMPTY){
            return "EMPTY";
        } else if (this == OBSTACLE){
            return "OBST";
        } else {
            return "ITEM";
        }
    }
}
