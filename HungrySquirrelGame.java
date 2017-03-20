/*Alexandra Macedo
Java Programming I
Final Project
Hungry Squirrel Game
*/
package hungrysquirrelgame;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/*This program simulates a gaame where the user enters input to move a Squirrel 
throughout a Maze to collect nuts for points. The Maze is created by reading from 
the Maze.txt file and a Squirrel object is instantiated. The user is asked to 
provide the starting location of the Squirrel in the maze, so long as the space 
is available and valid. five total nuts, being a random combination of Peanuts and 
Almonds, are randomly placed in the Maze. Also, five PoisonousCashews are  
randomly placed in the maze. The program asks for user input to move the Squirrel 
to move to collect Peanuts and Almonds for points, while avoiding the Poisonous 
Cashews. Each time the user moves the Squirrel, the PoisonousCashews move around 
at random to increase the difficulty of the game. As collecteding them incurs 
a 15 point deduction, the user will lose the game if their score becomes negative. 
Once the user successfully collects all five nuts (Peanuts and Almonds), the 
user wins, the total earned points are displayed, and the program terminates.*/

public class HungrySquirrelGame {
    public static void main(String[] args) throws IOException{
        try{
            Maze.create("Maze.txt");
        }
        catch(FileNotFoundException exception){
            System.err.println("File not found. Exiting application.");
            return;
        }
        
        
        Squirrel squirrel = new Squirrel();
        Maze.maze[squirrel.row][squirrel.column] = squirrel;
        
        Nut[] nuts = new Nut[Nut.TOTAL_NUTS];
        for(int i = 0 ; i < nuts.length ; i++){

                Random random = new Random();
                int randNut = random.nextInt(2);

                switch(randNut){
                case 0:
                    Almond almond = new Almond();
                    nuts[i] = almond;
                    Maze.maze[almond.row][almond.column] = almond;
                    break;

                case 1:
                    Peanut peanut = new Peanut();
                    nuts[i] = peanut;
                    Maze.maze[peanut.row][peanut.column] = peanut;
                    break;
                }

            }
        
        PoisonousCashew[] cashews = new PoisonousCashew[5];
        for (int i = 0 ; i < cashews.length ; i++){
            PoisonousCashew cashew = new PoisonousCashew();
            cashews[i] = cashew;
            Maze.maze[cashew.row][cashew.column] = cashew;
        }
        
        Scanner input = new Scanner(System.in);
        String command = "";
        boolean validInput;
        
        System.out.println("You are a hungry squirrel! Your job is to collect all the nuts.");  
        System.out.println("Move the squirrel to collect nuts but watch out for poisonous cashews!\n"
                + "Almonds are 5 points, Peanuts are 10 points, and Cashews are -15. Don't let your points fall below 0!\n"
                + "Acceptable input: u (up), d (down), l (left), r (right)");
        
        while(squirrel.getTotalNutsEaten() < 5 && squirrel.pointsCollected >= 0){
            
            Maze.display();
            validInput = false;
            
            while (validInput == false){
                System.out.println("Enter a direction to move the squirrel or quit: ");

                command = input.nextLine();
                command = command.toLowerCase();

                if (command.contains("quit")){
                    System.out.println("\n\n==================================================\n\n");
                    System.out.println("Good game!");
                    return;
                }

                if(!command.equals("u") && !command.equals("d") && !command.equals("r") && !command.equals("l")){
                    System.err.println("Input format is incorrect. Please enter the first letter of the direction you wish to move.");
                    continue;
                }
                validInput = true;
            }

            char direction = command.charAt(0);
            squirrel.move(direction);
            Maze.maze[squirrel.row][squirrel.column] = squirrel;
                
            for (int i = 0 ; i < cashews.length ; i++){
                    
                if (cashews[i] == null){
                    continue;
                }
                    
                if(cashews[i].row == squirrel.row && cashews[i].column == squirrel.column){
                    cashews[i] = null;
                    continue;
                }
                    
                cashews[i].move();
                    
                if(cashews[i].row == squirrel.row && cashews[i].column == squirrel.column){
                    cashews[i] = null;
                    continue;
                }
                else{
                    Maze.maze[cashews[i].row][cashews[i].column] = cashews[i];
                }
            }
        }
        
        Maze.display();
        
        System.out.println("\n\n==================================================\n\n");
            
        if(squirrel.getPointsCollected()> 0){
            System.out.println("Congratulations! You collected all of the nuts! Total score: " + squirrel.getPointsCollected() + " points");
        }
        else{
            System.out.println("GAME OVER. The squirrel died a poisonous death. Try again next time!");
        }
    }
}
