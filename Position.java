/*
Data Structures 2203
Assignment 2 Minion Mining
Chelsea McFarland
5/28/15
The position class stores coordinates.
 */
package assign2;

/**
 *
 * @author Chelsea
 */
public class Position{
    private int x, y;
    
    public Position(int x, int y){
        this.x = x;
        this.y = y;     
    }
    
    public int getX(){
        return this.x;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public int getY(){
        return this.y;
    } 
    
    public void setY(int y){
        this.y = y;
    }
}
