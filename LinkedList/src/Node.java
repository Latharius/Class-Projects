/* Leith Rabah
 * Dr. Steinberg
 * COP3330 Spring 2022
 * Programming Assignment 5
 */
public class Node <T> {
    private Node next;
    private T data;

    //defualt constructor
    public void Node (){
        this.next = null;
        this.data = null;

        System.out.println("Node() Constructor Invoked...");
    }

    //overloaded constructor
    public void Node (T var){
        this.data = var;
        this.next = null;

        System.out.println("Node(T data) Constructor Invoked...");
    }
}