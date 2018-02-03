/*
Data Structures 2203
Assignment 2 Minion Mining
Chelsea McFarland
5/28/15
The minion class navigates the map searching for diamonds. It contains methods
for checking blocks, moving in all four directions, and returning home. The only
oustide input is the main map. The methods output either the minion's position 
or a boolean value
 */
package assign2;

import java.util.*;
/**
 *
 * @author Chelsea
 */
public class Minion {
    private Map mainMap;
    private Position currentBlock;
    private MyStack path = new MyStack<Position>();
    private int steps = 0;
    
    //creates a new minion with given map
    public Minion(Map map){
        this.mainMap = map;
        this.currentBlock = map.getHome();      
    }
    
    public Position getPosition(){
        return this.currentBlock;
    }
    
    public MyStack getPath(){
        return this.path;
    }
    
    public int getSteps(){
        return this.steps;
    }
    
    //move one space north
    //returns true if sucessful
    public boolean moveNorth(){
        Position tmp = new Position(currentBlock.getX()-1, currentBlock.getY());
        
        //make sure new position is on map & make sure no obstacle
        if(test(tmp) && !checkObstacle(tmp) && !checkTraveled(tmp)){
            //leave a torch
            mainMap.addTorch(currentBlock);
            
            //add block to stack
            path.push(currentBlock);
            
            //reset currentblock to new position
            currentBlock = tmp;
            
            //increment steps
            steps++;
        }  
        
        //if the minion has to go back to previous block
        else if(test(tmp) && !checkObstacle(tmp)){
            //leave a torch
            mainMap.addTorch(currentBlock);
            
            //add block to stack
            path.push(currentBlock);
            
            //reset currentblock to new position
            currentBlock = tmp;
            
            //increment steps
            steps++;
        }
        
        //if minion can't go in that direction but it's on the map, move back
        else if(test(tmp)){
            moveBack();
        }
        
        return test(tmp) && !checkObstacle(tmp);
    }
    
    //move south
    //same logic as north
    public boolean moveSouth(){
        Position tmp = new Position(currentBlock.getX()+1, currentBlock.getY());
        if(test(tmp) && !checkObstacle(tmp) && !checkTraveled(tmp)){
            //leave a torch
            mainMap.addTorch(currentBlock);
            
            //add block to stack
            path.push(currentBlock);
            
            //reset currentblock to new position
            currentBlock = tmp;
            
            //increment steps
            steps++;
        }
        
        else if(test(tmp) && !checkObstacle(tmp)){
            //leave a torch
            mainMap.addTorch(currentBlock);
            
            //add block to stack
            path.push(currentBlock);
            
            //reset currentblock to new position
            currentBlock = tmp;
            
            //increment steps
            steps++;
        }
        
        //if minion can't go in that direction but it's on the map, move back
        else if(test(tmp)){
            moveBack();
        }
        
        return test(tmp) && !checkObstacle(tmp);
    }
    
    //move east
    //same logic as north
    public boolean moveEast(){
        Position tmp = new Position(currentBlock.getX(), currentBlock.getY()+1);
        if(test(tmp) && !checkObstacle(tmp) && !checkTraveled(tmp)){
            //leave a torch
            mainMap.addTorch(currentBlock);
            
            //add block to stack
            path.push(currentBlock);
            
            //reset currentblock to new position
            currentBlock = tmp;
            
            //increment steps
            steps++;
        }
        
        else if(test(tmp) && !checkObstacle(tmp)){
            //leave a torch
            mainMap.addTorch(currentBlock);
            
            //add block to stack
            path.push(currentBlock);
            
            //reset currentblock to new position
            currentBlock = tmp;
            
            //increment steps
            steps++;
        }
        
        //if minion can't go in that direction but it's on the map, move back
        else if(test(tmp)){
            moveBack();
        }
        
        return test(tmp) && !checkObstacle(tmp);
    }
    
    //move west
    //same logic as north
    public boolean moveWest(){
        Position tmp = new Position(currentBlock.getX(), currentBlock.getY()-1);
        if(test(tmp) && !checkObstacle(tmp) && !checkTraveled(tmp)){
            //leave a torch
            mainMap.addTorch(currentBlock);
            
            //add block to stack
            path.push(currentBlock);
            
            //reset currentblock to new position
            currentBlock = tmp;
            
            //increment steps
            steps++;
        }
        
        else if(test(tmp) && !checkObstacle(tmp)){
            //leave a torch
            mainMap.addTorch(currentBlock);
            
            //add block to stack
            path.push(currentBlock);
            
            //reset currentblock to new position
            currentBlock = tmp;
            
            //increment steps
            steps++;
        }
        
        //if minion can't go in that direction but it's on the map, move back
        else if(test(tmp)){
            moveBack();
        }
        
        return test(tmp) && !checkObstacle(tmp);
    }
    
    //move back one block
    public void moveBack(){
        if(path.getSize()!=0)
            currentBlock = (Position)path.pop();
    }
    
    //return to the home position
    public void moveHome(){
        //pops next position off stack until curentBlock == homeLocation
        while(!(currentBlock == mainMap.getHome())){
            currentBlock = (Position)path.pop();
        }
    }
    
    
    //check if there's an obstacle
    public boolean checkObstacle(Position p){
        return mainMap.getElement(p) == 'o';
    }
    
    //check if block has been traveled
    public boolean checkTraveled(Position p){
        return mainMap.getElement(p) == 't';
    }
    
    public boolean checkDiamond(){
        return mainMap.getElement(currentBlock) == 'd';
    }
    
    //make sure position is on map
    public boolean test(Position p){
        return p.getX() >= 0 && p.getY()>=0 && p.getX() < mainMap.getRows()
                && p.getY() < mainMap.getColumns();
    }
}
