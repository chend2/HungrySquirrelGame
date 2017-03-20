package hungrysquirrelgame;

import java.util.Random;

/* The Almond class extends from the Nut class. The constructor calls the overriden
create method from the Entity superclass. In it, the location of an Almond is 
randomly chosen and entered into the Maze.*/

public class Almond extends Nut{
    
    public Almond(){
        create();
    }
    
    @Override
    public void create(){   
        name = "Almond";
        symbol = 'A'; 
        nutritionPoints = 5;
        
        boolean validInput = false;
        
        while(validInput == false){
            Random randRow = new Random();
            row = randRow.nextInt(Maze.MAX_MAZE_ROW);

            Random randCol = new Random();
            column = randCol.nextInt(Maze.MAX_MAZE_COLUMN);

            if(!Maze.available(row, column)){
                validInput = false;
                continue;
            }

            validInput = true;
        }
    }
}
