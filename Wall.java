package hungrysquirrelgame;

/* The Wall class extends from the Entity class. The constructor calls the 
overridden create method from the Entity class.*/

public class Wall extends Entity{
    public Wall(int r, int c){
        row = r;
        column = c;
        create();
    }
    
    @Override
    public void create(){
        symbol = '*';
    }
}
