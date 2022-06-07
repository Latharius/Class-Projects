/* Johnny Knights
 * Dr. Andrew Steinberg
 * COP3503 Summer 2022
 * Programming Assignment 1
 */
import java.util.*;


public class LotteryRunner {

    public static void main(String[] args) {

        Lottery [] ticketscollection = new Lottery[100000];

        Random rn = new Random();
        rn.setSeed(0); //set the pseudorandom number generator

        for(int x = 0; x < ticketscollection.length; ++x)
            ticketscollection[x] = new Lottery(rn);


        Lottery.Sort(ticketscollection);


        System.out.println("Beginning Test Cases");

        System.out.println("*********************************");

        String test = Lottery.GenerateRandomWinner(rn); //random ticket
        int index = Lottery.GenerateSelectWinner(1000, rn); //index of a winning ticket

        System.out.println("TEST 1");
        System.out.println("TESTING When Array holds 1000 Tickets");

        System.out.println("Testing Solution 1");

        if(Lottery.Solution1(ticketscollection, test, 999) == false && Lottery.Solution1(ticketscollection, ticketscollection[index].GetTicket(), 999) == true)
            System.out.println("Solution 1 Test 1 Passed!");
        else
            System.out.println("Solution 1 Test 1 Failed!");


        System.out.println("Testing Solution 2");

        if(Lottery.Solution2(ticketscollection, 0, 999, test) == false && Lottery.Solution2(ticketscollection, 0, 999, ticketscollection[index].GetTicket()) == true)
            System.out.println("Solution 2 Test 1 Passed!");
        else
            System.out.println("Solution 2 Test 1 Failed!");

        System.out.println("*********************************");

        test = Lottery.GenerateRandomWinner(rn); //random ticket
        index = Lottery.GenerateSelectWinner(2000, rn); //index of a winning ticket

        System.out.println("TEST 2");
        System.out.println("TESTING When Array holds 2000 Tickets");

        System.out.println("Testing Solution 1");

        if(Lottery.Solution1(ticketscollection, test, 1999) == false && Lottery.Solution1(ticketscollection, ticketscollection[index].GetTicket(), 1999) == true)
            System.out.println("Solution 1 Test 2 Passed!");
        else
            System.out.println("Solution 1 Test 2 Failed!");

        System.out.println("Testing Solution 2");

        if(Lottery.Solution2(ticketscollection, 0, 1999, test) == false && Lottery.Solution2(ticketscollection, 0, 1999, ticketscollection[index].GetTicket()) == true)
            System.out.println("Solution 2 Test 2 Passed!");
        else
            System.out.println("Solution 2 Test 2 Failed!");


        System.out.println("*********************************");
        test = Lottery.GenerateRandomWinner(rn); //random ticket
        index = Lottery.GenerateSelectWinner(5000, rn); //index of a winning ticket

        System.out.println("TEST 3");
        System.out.println("TESTING When Array holds 5000 Tickets");

        System.out.println("Testing Solution 1");

        if(Lottery.Solution1(ticketscollection, test, 4999) == false && Lottery.Solution1(ticketscollection, ticketscollection[index].GetTicket(), 4999) == true)
            System.out.println("Solution 1 Test 3 Passed!");
        else
            System.out.println("Solution 1 Test 3 Failed!");

        System.out.println("Testing Solution 2");

        if(Lottery.Solution2(ticketscollection, 0, 4999, test) == false && Lottery.Solution2(ticketscollection, 0, 4999, ticketscollection[index].GetTicket()) == true)
            System.out.println("Solution 2 Test 3 Passed!");
        else
            System.out.println("Solution 2 Test 3 Failed!");

        System.out.println("*********************************");
        test = Lottery.GenerateRandomWinner(rn); //random ticket
        index = Lottery.GenerateSelectWinner(9000, rn); //index of a winning ticket

        System.out.println("TEST 4");
        System.out.println("TESTING When Array holds 9000 Tickets");

        System.out.println("Testing Solution 1");

        if(Lottery.Solution1(ticketscollection, test, 8999) == false && Lottery.Solution1(ticketscollection, ticketscollection[index].GetTicket(), 8999) == true)
            System.out.println("Solution 1 Test 4 Passed!");
        else
            System.out.println("Solution 1 Test 4 Failed!");

        System.out.println("Testing Solution 2");

        if(Lottery.Solution2(ticketscollection, 0, 8999, test) == false && Lottery.Solution2(ticketscollection, 0, 8999, ticketscollection[index].GetTicket()) == true)
            System.out.println("Solution 2 Test 4 Passed!");
        else
            System.out.println("Solution 2 Test 4 Failed!");

        System.out.println("*********************************");
        test = Lottery.GenerateRandomWinner(rn); //random ticket
        index = Lottery.GenerateSelectWinner(ticketscollection.length, rn); //index of a winning ticket


        System.out.println("TEST 5");
        System.out.println("TESTING When Array holds 100000 Tickets");

        System.out.println("Testing Solution 1");


        if(Lottery.Solution1(ticketscollection, test, ticketscollection.length - 1) == false && Lottery.Solution1(ticketscollection, ticketscollection[index].GetTicket(), ticketscollection.length - 1) == true)
            System.out.println("Solution 1 Test 5 Passed!");
        else
            System.out.println("Solution 1 Test 5 Failed!");

        System.out.println("Testing Solution 2");

        if(Lottery.Solution2(ticketscollection, 0, ticketscollection.length - 1, test) == false && Lottery.Solution2(ticketscollection, 0, ticketscollection.length - 1, ticketscollection[index].GetTicket()) == true)
            System.out.println("Solution 2 Test 5 Passed!");
        else
            System.out.println("Solution 2 Test 5 Failed!");

    }
}
