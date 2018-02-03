/*
Data Structures 2203
Assignment 2 Minion Mining
Chelsea McFarland
5/28/15
This is the driver class. It handles inputs from and outputs to the console.
It initializes the map and controls the logic of the minion's search. It also
outputs the statistics once the program is over.
 */
package assign2;

import java.util.*;
/**
 *
 * @author Chelsea
 */
public class Driver {
    //loads a map with the values input through the console
    public Map load(Scanner sc){
        //sc = new Scanner(System.in);
        int rows, columns, objects;
        
        //get rows and columns to initialize map
        rows = sc.nextInt();
        columns = sc.nextInt();
        Map m = new Map(rows, columns);
        
        //get number of objects to load
        objects = sc.nextInt();
        
        //add the objects
        for(int i=0; i<objects; i++){
            switch(sc.next()){
                //if the next character is h, add home at those coords
                case "h": m.addHome(new Position(sc.nextInt(), sc.nextInt()));
                          break;
                    
                //if the next character is o, add obstacle at those coords
                case "o": m.addObstacle(new Position(sc.nextInt(),
                          sc.nextInt()));
                          break;
                
                //if the next character is d, add diamond at those coords
                case "d": m.addDiamond(new Position(sc.nextInt(),
                          sc.nextInt()));
                          break;
            }                                   
        }
        return m;        
    }
    
    public static void main(String[] args) {
        Map mainMap;
        Scanner input = new Scanner(System.in);
        
        //initializ mainMap using the load() method
        mainMap = new Driver().load(input);
        System.out.println(mainMap.toString());
               
        //create a minion to navigate the map
        Minion minion = new Minion(mainMap);
        
        int blanks = 1;
        
        //time the search
        long startTime = System.currentTimeMillis();
        
        //while the diamond isn't found and there are still blank spaces 
        while((!minion.checkDiamond()) && (blanks > 0)){
            blanks = 0;
            
            //random generator to choose direction
            Random rnd = new Random();
            switch(rnd.nextInt(4)+1){
                case 1: minion.moveNorth();
                        break;
                case 2: minion.moveSouth();
                        break;
                case 3: minion.moveEast();
                        break;
                case 4: minion.moveWest();
                        break;
            }
            
            //count blanks left
            for(int i = 0; i<mainMap.getRows(); i++){
                for(int j=0; j<mainMap.getColumns(); j++){
                    if(mainMap.getElement(new Position(i,j)) == ' ')
                        blanks++;
                }
            }
        }
        //time the search
        long endTime = System.currentTimeMillis();
        long searchTime = endTime - startTime;
        
        //check if the diamond was found
        boolean diamondFound = minion.checkDiamond();
        
        //add minion to map and display
        mainMap.addMinion(minion.getPosition());
        System.out.println(mainMap.toString());       
        
        //copy path for statistics    
        MyStack pathCopy = minion.getPath().clone();
        
        //get info for statistics       
        Position diamondLocation = minion.getPosition();
        Position[] pathCoord = new Position[pathCopy.getSize()];
        
        //convert path to position array
        for(int i = 0; i < pathCoord.length; i++){
            pathCoord[i] = (Position)pathCopy.pop();
        }
        int length = minion.getPath().getSize();
        
        //return home
        minion.moveHome();
        
        //Statistics
        System.out.println("Diamond found? " + diamondFound);
        System.out.println("Steps: " + minion.getSteps());
        System.out.println("Time to search: " + searchTime + " milliseconds");
       
        //only print these if the diamond is found
        if(diamondFound){
            System.out.println("(" + diamondLocation.getY() + ", " + 
                    diamondLocation.getX() + ")");
            System.out.println("Length of path: " + length);
            System.out.println("Coordinates home: ");
            
            //print out path coordinates
            for(int i=0; i < pathCoord.length; i++){
                System.out.print("(" + pathCoord[i].getY() + ", " + 
                        pathCoord[i].getX() + "), ");
            }
        }
        System.out.println(" ");
        System.out.println("Program terminated.");
    }
}
