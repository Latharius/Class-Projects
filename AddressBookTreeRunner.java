/* Name:
 * Dr. Andrew Steinberg
 * COP3503 Summer 2022
 * Programming Assignment 2
 */
import java.io.*;  


/**
 *
 * @author Andrew Steinberg
 */
public class AddressBookTreeRunner {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        AddressBookTree <String, String> test = new AddressBookTree <String, String>();

        File file=new File("faculty.txt"); 
        FileReader fr=new FileReader(file);   //reads the file  
        BufferedReader br=new BufferedReader(fr);
         
        String line;  
        
        while((line=br.readLine())!=null)  
        {  
            String [] content = line.toString().split("\t");
            
            if(content.length != 1)
                test.insert(content[0], content[1]);
        }  
        
        fr.close();    //closes the file!!!
        
        int pass = 0;
        
        System.out.println("*************************************************");
        System.out.println("Beginning Test Cases...");
        
        System.out.println("Test Case 1...");
        System.out.println("Testing the initial setup...");
        
        test.display(); //display content
        
        if(test.countBlack(test.getRoot()) == 30 && test.countRed(test.getRoot()) == 26)
        {
            System.out.println("TEST 1 Passed!");
            ++pass;
        }
        else
            System.out.println("TEST 1 Failed!");
        
        System.out.println("*************************************************");
        System.out.println("Test Case 2...");
        System.out.println("Testing the insert method...");
        System.out.println("Inserting some items...");
        
        //inserting some content
        test.insert("Albus Dumbledore", "HEC-108A");
        test.insert("Severous Snape", "HEC-108B");
        test.insert("Minerva McGonagall", "HEC-108C");
        test.insert("Rubeus Hagrid", "HEC-108E");
        
        if(test.countBlack(test.getRoot()) == 31 && test.countRed(test.getRoot()) == 29)
        {
            System.out.println("TEST 2 Passed!");
            ++pass;
        }
        else
            System.out.println("TEST 2 Failed!");
        
        System.out.println("*************************************************");
        
        System.out.println("*************************************************");
        System.out.println("Test Case 3...");
        System.out.println("Testing the deletion method...");
        System.out.println("Removing some items...");
        
        
        //removing some content
        test.deleteNode("Delores Umbridge");
        test.deleteNode("Jennifer Lawrence");
        test.deleteNode("Tom Holland");
        test.deleteNode("Scarlett Johansson");
        test.deleteNode("Ryan Reynolds");
        test.deleteNode("Melisa Mccarthy");
        test.deleteNode("Chris Pratt");
        test.deleteNode("Amy Poehler");
        test.deleteNode("Albus Dumbledore");
        test.deleteNode("Severous Snape");
        test.deleteNode("Minerva McGonagall");
        test.deleteNode("Rubeus Hagrid");
        test.deleteNode("Johnny Depp");
        
        if(test.countBlack(test.getRoot()) == 30 && test.countRed(test.getRoot()) == 17)
        {
            System.out.println("TEST 3 Passed!");
            ++pass;
        }
        else
            System.out.println("TEST 3 Failed!");
        
        System.out.println("Ending Test Cases...");
        System.out.println("*************************************************");
        
        test.display();
        
        System.out.println("*************************************************");
        
        if(pass == 3)
            System.out.println("Yay! All test cases passed!");
        else
            System.out.println("Oh No! Some test case didn't pass!");
    }    
}
