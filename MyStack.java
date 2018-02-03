/*
Data Structures 2203
Assignment 2 Minion Mining
Chelsea McFarland
5/28/15
MyStack is a generic class. It contains the stack methods peek(), pop(), and 
push(). It is implemeted using an ArrayList. It will be used for storing the
minion's coordinates in the form of Posistion objects.
 */
package assign2;

import java.util.*;

/**
 *
 * @author Chelsea
 * @param <E>
 */
public class MyStack<E> implements Cloneable{
    private ArrayList<E> myData;
    
    public MyStack(){
        myData = new ArrayList<E>();
    }
    
    public E push(E obj){
        myData.add(obj);
        return obj;
    }
    
    public E peek(){
        if(myData.isEmpty())
            throw new EmptyStackException();
        return myData.get(myData.size()-1);
    }
    
    public E pop(){
        if(myData.isEmpty())
            throw new EmptyStackException();
        return myData.remove(myData.size()-1);
    }
    
 
    public int getSize(){
        return myData.size();
    }
    
    @Override
    protected MyStack<E> clone(){
        try{
           MyStack<E> temp = (MyStack<E>)super.clone();
           temp.myData = (ArrayList<E>)(myData.clone());
           return temp;
        }
        catch(CloneNotSupportedException ex){
            return null;
        }
    }
}
