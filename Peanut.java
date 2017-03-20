/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungrysquirrelgame;

import java.util.Random;

/* The Peanut class extends from the Nut class. The constructor calls the overriden
create method from the Entity superclass. In it, the location of a Peanut is 
randomly chosen and entered into the Maze.*/

public class Peanut extends Nut{
    
    public Peanut(){
        create();
    }
    
    @Override
    public void create(){   
        name = "Peanut";
        symbol = 'P'; 
        nutritionPoints = 10;
        
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
