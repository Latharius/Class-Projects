/* Leith Rabah
 * Dr. Steinberg
 * COP3330 Spring 2022
 * Programming Assignment 3
 */

public class LibraryRunner {

    public static void main(String[] args)
    {
        System.out.println("*********************************************");
        System.out.println("Beginning test with default constructor.");
        Library mylibrary = new Library();

        System.out.println("Lets start adding books to the collection.");

        mylibrary.add("Julius Caesar", "William Shakespeare", 12.99);
        mylibrary.add("The Great Gatsby", "F. Scott Fitzgerald", 10.99);
        mylibrary.add("The Canterbury Tales", "Geoffrey Chaucer", 14.99);
        mylibrary.add("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 13.99);
        mylibrary.add("Moby Dick", "Herman Melville", 10.99);
        mylibrary.display();

        System.out.println("Let's try adding a sixth book.");
        mylibrary.add("The Hunger Games", "Suzanne Collins", 11.99);


        System.out.println("*********************************************");
        System.out.println("Now it is time to copy the library");

        Library mylibrary2 = new Library(mylibrary);

        mylibrary2.display();

        System.out.println("*********************************************");
        System.out.println("Now I will reverse the collection order.");
        mylibrary2.reverse();
        mylibrary2.display();
        System.out.println("*********************************************");

        System.out.println("Now removing book from mylibrary.");
        mylibrary.remove("The Great Gatsby");
        mylibrary.display();
        System.out.println("*********************************************");
        System.out.println("Now lets add that book to the collection.");
        mylibrary.add("The Hunger Games", "Suzanne Collins", 11.99);
        mylibrary.display();
        System.out.println("*********************************************");

        System.out.println("Now clearing a library.");
        mylibrary.clearLibrary();
        System.out.println("Checking to see if library is empty.");

        if(mylibrary.empty())
            System.out.println("Empty");
        else
            System.out.println("Not Empty");


        System.out.println("*********************************************");
        System.out.println("Looking up an existing book.");
        Book temp = mylibrary2.search("The Canterbury Tales");
        System.out.println(temp.getTitle());
        System.out.println("Looking up a book that doesn't exist.");
        Book temp2 = mylibrary2.search("What if...");
        System.out.println(temp2);
        System.out.println("*********************************************");
        System.out.println("End of program run.");
    }

}