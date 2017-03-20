package hungrysquirrelgame;

/* The abstract Nut superclass inherits from the Entity class and extends to 
the Almond, Peanut, and PoisonousCashew classes.*/

public abstract class Nut extends Entity{
    public static final int TOTAL_NUTS = 5;
    public int nutritionPoints;
    public String name;
}
