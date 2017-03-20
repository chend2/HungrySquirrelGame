package hungrysquirrelgame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/*Instantiates a static Maze object with a 2D Entity array of 20 rows and 50 columns.
The create method reads from the Maze.txt file to populate the 2D array. The 
display method displays the contents of the Maze to the user's console. The 
available method  returns whether or not an element of the Maze 2D array is
null, meaning it references no Entity (Wall, Squirrel, Nut).*/

public class Maze {
    public static final int MAX_MAZE_ROW = 20;
    public static final int MAX_MAZE_COLUMN = 50;
    public static Entity [][] maze = new Entity[MAX_MAZE_ROW][MAX_MAZE_COLUMN];
    
    public static void create (String fileName) throws FileNotFoundException{
        FileReader in = new FileReader(fileName);
        Scanner file = new Scanner(in);
        
        String line = "";
        int row = 0;
        while(file.hasNextLine() && row < MAX_MAZE_ROW){
            line = file.nextLine();
            
            for(int column = 0; column<MAX_MAZE_COLUMN; column++){
                if(line.charAt(column) == '*'){    
                    maze[row][column] = new Wall (row, column);} 
                else {
                    maze[row][column] = null;}
            } 
            row++;
            
        }
        
        file.close();
    }
    
    public static void display(){
        for(int row = 0 ; row < MAX_MAZE_ROW ; row++){
            for(int column = 0 ; column < MAX_MAZE_COLUMN ; column++){
                if(maze[row][column]==null){
                    System.out.print(" ");
                }
                else{
                    System.out.print(maze[row][column]);
                }
            }
            System.out.println();
        }    
    }
    
    public static boolean available(int row, int col){
        if (maze[row][col]==null){
            return true;
        }
        else{
            return false;
        }
    }
}
