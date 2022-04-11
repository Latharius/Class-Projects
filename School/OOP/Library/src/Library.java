/* Leith Rabah
 * Dr. Steinberg
 * COP3330 Spring 2022
 * Programming Assignment 3
 */
class Book{
    private String title;
    private String author;
    private double cost;
    Book nextptr;

    //Overloaded constructor for current Book
    public Book(String title, String author, double cost){
        this.title = title;
        this.author = author;
        this.cost = cost;
    }

    //Overloaded constructor for previous Book
    public Book(Book old){
        this.title = old.title;
        this.author = old.author;
        this.cost = old.cost;
        this.nextptr = old.nextptr;
    }

    //Gets title of the Book
    public String getTitle(){
        return title;
    }

    //Gets the author of the Book
    public String getAuthor() {
        return author;
    }

    //Gets the cost of the Book
    public double getCost() {
        return cost;
    }
}

public class Library{
    Book first;
    int total;

    //Default constructor
    public Library(){
        this.first = null;
        this.total = 0;
    }

    //Deep copy method
    public Library(Library library){
        System.out.println("Copying your Library collection.");

        this.total = library.total;

        Book temp = new Book(library.first);

        this.first = temp;

        Book link = library.first.nextptr;

        while(link != null){
            temp.nextptr = new Book(link);
            temp = temp.nextptr;
            link = link.nextptr;
        }
    }

    //Method to add a Book object to the list.
    public void add(String title, String author, double cost){
        if(full()){
            System.out.println("Your library is full.");
            return;
        }

        if(first == null){
            first = new Book(title, author, cost);
            total++;
        }
        else{
            Book temp = first;

            while(temp.nextptr != null)
                temp = temp.nextptr;

            temp.nextptr = new Book(title, author, cost);
            total++;
        }
    }

    //Method to search a certain title in the list.
    public Book search(String title){
        Book temp = first;

        while(temp != null){
            if(temp.getTitle() == title)
                return temp;

            temp = temp.nextptr;
        }
        return null;
    }

    //Method to reverse the order of the list.
    public void reverse(){
        Book prev = null;
        Book cur = first;
        Book next = null;

        while(cur != null){
            next = cur.nextptr;
            cur.nextptr = prev;
            prev = cur;
            cur = next;
        }
        first = prev;
    }

    //Method to remove a Book object from the list.
    public void remove(String title){
        if(empty())
            System.out.println("List is empty.");
        else if(search(title) != null){
            if(first.getTitle().equals(title)){
                first = first.nextptr;
                total--;
            }
            else if(total == 1){
                total--;
                first = null;
            }
            else{
                Book temp = first;

                while(!temp.nextptr.getTitle().equals(title))
                    temp = temp.nextptr;

                temp.nextptr = temp.nextptr.nextptr;
                total--;
            }
        }
        else
            System.out.println("Title does not exist in the list.");
    }

    //Empty out the entire library.
    public void clearLibrary(){
        this.first = null;
        this.total = 0;
    }

    //Checks if the list is empty.
    public boolean empty(){
        return total == 0;
    }

    //Checks if the list is full.
    public boolean full(){
        return total == 5;
    }

    //Gets the title of the Book
    public String getTitle(Book temp){
        return temp.getTitle();
    }

    //Displays the contents of the list.
    public void display(){
        Book temp = first;

        while(temp != null){
            System.out.println("Title: " +temp.getTitle());
            System.out.println("Author: " + temp.getAuthor());
            System.out.println("Cost: " + temp.getCost());

            temp = temp.nextptr;
        }
    }
}
