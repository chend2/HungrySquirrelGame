package hungrysquirrelgame;

import java.util.Scanner;

/*Squirrel extends from the Entity superclass and implements the Movable
interface. The constructor calls the create method, which first asks for user
input to set the initial location of the Squirrel in the 2D Maze. If the user
enters unavailable coordinates, the method will continue to ask for valid input. 
The move method accepts user input to move the Squirrel in a certain direction
in the maze (up, down, left, or right), with user input validationn. In any 
case, the put method is called to return the Entity at the location the user 
desires to move the Squirrel to. If the Squirrel collects a nut, the Squirrel's 
points accumulate and the nut is removed. If the Squirrel hits a wall, it does not 
move. Getters for points collected and total nuts are included.*/

public class Squirrel extends Entity implements Movable{
    public int pointsCollected = 0;
    public int totalNutsEaten = 0;
    
    public Squirrel(){
        create();
    }
    
    @Override
    public void create(){
        Scanner input = new Scanner(System.in);
        int[]coord = new int[2];
        boolean validInput = false;
        
        while (validInput == false){
            System.out.println("Choose 2 starting integer coordinates for the squirrel separated by a space: <row column>)");
                for(int i = 0 ; i < coord.length ; i++){
                    while(!input.hasNextInt()){
                        System.err.println("Incorrect format. Please enter only integers.");
                        input.next();
                    }
                    coord[i] = input.nextInt();
                }
            
            if (coord[0] < 0 || coord[0] > Maze.MAX_MAZE_ROW || coord[1] < 0 || coord[1] > Maze.MAX_MAZE_COLUMN){
                System.err.println("The coordinates entered are out of bounds.");
                validInput = false;
                continue;
            }
            
            else if (!Maze.available(coord[0], coord[1])){
                System.err.println("The selected coordinates are not available.");
                validInput = false;
                continue;
            }
            validInput = true;    
        }
        
        row = coord[0];
        column = coord[1];
        symbol = '@';
    }
    
    @Override
    public void move (char direction){
       Maze.maze[row][column] = null;
       Entity e;
       int tempRow = row;
       int tempCol = column;
        switch(direction){
            case 'u':
                e = put(row -1, column);
                tempRow = row - 1;
                tempCol = column;
                break;
            case 'd':
                e = put(row +1, column);
                tempRow = row + 1;
                tempCol = column;
                break;
            case 'r':
                e = put(row, column + 1);
                tempRow = row;
                tempCol = column + 1;
                break;
            default :
                e = put(row, column - 1);
                tempRow = row;
                tempCol = column - 1;
                break;
        }
        
        if(e instanceof Wall){
            System.err.println("There is a wall in your way!");
            Maze.maze[tempRow][tempCol] = new Wall(tempRow, tempCol);
        }
        
        else if (e instanceof Almond){
            pointsCollected += ((Almond) e).nutritionPoints;
            totalNutsEaten++;
            row = tempRow;
            column = tempCol;
            System.out.println("The squirrel ate an Almond! Gained 5 points. Total points: " + pointsCollected);
        }
        else if (e instanceof Peanut){
            pointsCollected += ((Peanut) e).nutritionPoints;
            totalNutsEaten++;
            row = tempRow;
            column = tempCol;
            System.out.println("The squirrel ate a Peanut! Gained 10 points. Total points: " + pointsCollected);
        }
        else if (e instanceof PoisonousCashew){
            pointsCollected += ((PoisonousCashew) e).nutritionPoints;
            if (pointsCollected <= 0){
                System.err.println("The squirrel ate a poisonouse cashew! Lost 15 points and has died!");
            }
            else{
                System.err.println("The squirrel ate a Poisonous Cashew! Lost 15 points. Total points: " + pointsCollected);
            }
            row = tempRow;
            column = tempCol;
        }
        else if (e == null){
            row = tempRow;
            column = tempCol;
        }
    }

    public int getPointsCollected() {
        return pointsCollected;
    }

    public int getTotalNutsEaten() {
        return totalNutsEaten;
    }    
}
