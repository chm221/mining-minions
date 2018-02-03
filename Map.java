/*
Data Structures 2203
Assignment 2 Minion Mining
Chelsea McFarland
5/28/15
This class is responsible for creating the map using values passed from Driver.
It adds the different elements of the map using their positions. It also stores
the home position. The methods return either integers, a Position, or a char.
 */
package assign2;

/**
 *
 * @author Chelsea
 */
public class Map {
    private char[][] map;
    private int rows, columns;
    private int nbrObstacles = 0;
    private Position homeLocation;
    private boolean diamond = false;
    
    //creates a new map of given size made of blank spaces
    public Map(int rows, int columns){
        this.map = new char[rows][columns];
        this.rows = rows;
        this.columns = columns;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                this.map[i][j] = ' ';
            }
        }   
    }
    
    //returns specified character
    public char getElement(Position coord){
        try{
            return this.map[coord.getX()][coord.getY()];
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Please enter coordinates within the map");
            return 'e';
        }
    }
    
    public int getRows(){
        return this.rows;
    }           
    
    public int getColumns(){
        return this.columns;
    }
    
    //returns the home position
    public Position getHome(){
        return this.homeLocation;
    }
    
    //returns true if there is a diamond
    public boolean getDiamond(){
        return diamond;
    }
    
    //returns the number of obstacles
    public int getObstacles(){
        return this.nbrObstacles;
    }
    
    //add home to map and set home location
    public void addHome(Position coord){
        try{
            this.map[coord.getX()][coord.getY()] = 'h';
            this.homeLocation = coord;
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Could not add home. "
                    + "Please enter coordinates within the map");
        }
    }
    
    //add diamond to map
    public void addDiamond(Position coord){
        try{
            this.map[coord.getX()][coord.getY()] = 'd';
            this.diamond = true;
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Could not add diamond. "
                    + "Please enter coordinates within the map");
        }
    }
    
    //add obstacle to map
    public void addObstacle(Position coord){
        try{
            this.map[coord.getX()][coord.getY()] = 'o';
            nbrObstacles++;
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("Could not add obstacle. "
                    + "Please enter coordinates within the map");
        }
    }
    
    //add minion to map
    public void addMinion(Position coord){
        map[coord.getX()][coord.getY()] = 'm';
    }
    
    //add torch
    public void addTorch(Position coord){
        //only add if block is blank
        if(map[coord.getX()][coord.getY()] == ' ')
            map[coord.getX()][coord.getY()] = 't';
    }
    
    @Override
    public String toString(){
         //convert string array to single string
        String delimiter = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        for(int m=0; m<rows; m++){
            for(int n=0; n<columns; n++)
                sb.append(map[m][n]).append(" ");
            sb.append(delimiter);
        }
        return sb.toString();
    }
}
