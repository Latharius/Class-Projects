/* Leith Rabah
 * Dr. Steinberg
 * COP3330 Spring 2022
 * Programming Assignment 5
 */
public class LinkedList <T>{
    private Node head;
    private int length = 0;

    //default constructor
    public void LinkedList(){
        this.head = null;
        this.length = 0;

        System.out.println("LinkedList() Constructor Invoked...");
    }

    public void insert(T var){
        if(head == null){
            head = new Node(var);
            length++;
        }
        else{
            Node temp = head;

            while(temp.next != null)
                temp = temp.next;

            temp.next = new Node(var);
            length++;
        }
    }

    public void insert(int position, T var){
        if(position < length || position > length){
            System.out.println("Out of range!");
        }
        else{
            int n = 0;
            while(n != position){
                Node temp = head;
                temp = temp.next;
                n++;
            }

        }
    }

    public void remove(T var){
        if(empty())
            System.out.println("List is empty.");
        else if(Node.data == var){
            if(temp.data.equals(var)){
                temp = temp.next;
                length--;
            }
        }
    }

    public void clear(){
        this.head = null;
        this.length = 0;
    }

    public boolean empty(){
        return length == 0;
    }

    public int length(){
        return length;
    }

    public String toString(){
        if(empty())
            System.out.println("Empty List");
        else{

        }
    }
}
