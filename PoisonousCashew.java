package hungrysquirrelgame;

import java.util.Random;

/* The PoinsonousCashew class extends from the Nut class. The constructor calls 
the overriden create method from the Entity superclass. In it, the location of 
a PoisonousCashew is randomly chosen and entered into the Maze. This class does
not override the move method from the Movable interface because it requires 
different parameters (none). This move method moves each PoisonousCashew one space 
up, right, left, or down, OR leaves it in the same space. Each of the 5 cases 
are randomly chosen for each PoisonousCashew and are equally probable. If a 
Poisonous Cashew moves to a space with the Squirrel, the Squirrel loses 15 
points, a message is displayed.*/

public class PoisonousCashew extends Nut{
    
    public PoisonousCashew(){
        create();
    }
    
    @Override
    public void create(){
        name = "Cashew";
        symbol = 'C'; 
        nutritionPoints = -15;
        
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
    
    public void move (){
        Maze.maze[row][column] = null;
        
        Entity e = null;
        int tempRow = row;
        int tempCol = column;
        
        while (true){
            Random random = new Random();
            int randNum = random.nextInt(5);
            
            switch(randNum){
                case 0:
                    e = put(row -1, column);
                    tempRow = row - 1;
                    tempCol = column;
                    break;
                case 1:
                    e = put(row +1, column);
                    tempRow = row + 1;
                    tempCol = column;
                    break;
                case 2:
                    e = put(row, column + 1);
                    tempRow = row;
                    tempCol = column + 1;
                    break;
                case 3:
                    e = put(row, column - 1);
                    tempRow = row;
                    tempCol = column - 1;
                    break;
                default:
                    break;
            }
            if (e instanceof Wall || e instanceof Nut){
                continue;
            }
            else if(e instanceof Squirrel){
                ((Squirrel) e).pointsCollected += nutritionPoints;
                if(((Squirrel) e ).pointsCollected <= 0){
                    System.err.println("The squirrel ate a poisonouse cashew! Lost 15 points and has died!");
                }
                else{

                    System.err.println("The squirrel ate a Poisonous Cashew! Lost 15 points. Total points: " + ((Squirrel) e).pointsCollected);
                }
                row = tempRow;
                column = tempCol;
                break;
            }
            else if (e == null){
                row = tempRow;
                column = tempCol;
                break;
            }
        }  
    }
}
