package hungrysquirrelgame;

/*The abstract Entity class si a superclass of Squirrel, Nut, Almond, Nut, 
Cashew, and Wall. Contains attributes symbol, row, and column, where row and
column refer to an Entity's location in the Maze, and symbol is what the Entity
is represented by in the maze when displayed on the user's console. Contains
abstract method create, which is defined by aforemention subclass types. The put
method returns the entity stored at a location in the maze.*/

public abstract class Entity {
    char symbol;
    int row;
    int column;
    
    public abstract void create();
    
    public Entity put(int row, int column){    
        return Maze.maze[row][column];
    }
    
    @Override
    public String toString(){
        String sym = "" + symbol;
        return sym;
    }
}
