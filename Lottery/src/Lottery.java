/* Leith Rabah
 * Dr. Andrew Steinberg
 * COP3503 Summer 2022
 * Programming Assignment 1
 */
import java.util.Random;

public class Lottery {
    private String ticket;

    public Lottery(){ //default constructor
        this.ticket = "";
    }

    public Lottery(Random val){ //Overloaded constructor
        int rand = 100000 + val.nextInt(999999);
        this.ticket = String.valueOf(rand);
    }

    public static String GenerateRandomWinner(Random val){ //Generate random ticket
        int rand = 100000 + val.nextInt(999999);

        return String.valueOf(rand);
    }

    public static int GenerateSelectWinner(int max, Random val){ //Generate winning ticket from within the array
        return val.nextInt(max);
    }

    public static boolean Solution1(Lottery[] tickets, String x, int y){ //Find the winner using Linear Search
        for (int i = 0; i <= y; i++) {
            if (tickets[i].GetTicket().equals(x))
                return true;
        }
        return false;
    }

    public static boolean Solution2(Lottery[] tickets, int left, int right, String x){ //Find the winner using Binary Search
        int mid = left + (right - left) / 2;

        if(left <= right) {
            if (tickets[mid].GetTicket().equals(x))
                return true;
            if (tickets[mid].GetTicket().compareTo(x) > 0)
                return Solution2(tickets, left, mid - 1, x);
            if (tickets[mid].GetTicket().compareTo(x) < 0)
                return Solution2(tickets, mid + 1, right, x);
        }
        return false;
    }

    public static void Sort(Lottery[] tickets){ //Sorting the array
        int left = 0;
        int right = tickets.length - 1;

        mergeSort(tickets, left, right);
    }

    public static void mergeSort(Lottery[] tickets, int left, int right){ //Sorting using mergesort
        if(left < right){
            int middle = left + (right - left) / 2;

            mergeSort(tickets, left, middle);
            mergeSort(tickets,  middle + 1, right);
            merge(tickets, left, middle, right);
        }
    }

    public static void merge(Lottery[] tickets, int left, int middle, int right){ //merging and sorting the temp arrays
        int n1 = middle - left + 1;
        int n2 = right - middle;

        Lottery[] leftArr = new Lottery[n1];
        Lottery[] rightArr = new Lottery[n2];

        for(int i = 0; i < n1; i++)
            leftArr[i] = tickets[left + i];
        for(int j = 0; j < n2; j++)
            rightArr[j] = tickets[middle + j + 1];

        int i = 0;
        int j = 0;
        int k = left;

        while(i < n1 && j < n2){
            if(leftArr[i].ticket.compareTo(rightArr[j].ticket) <= 0){
                tickets[k] = leftArr[i];
                i++;
            }
            else{
                tickets[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            tickets[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            tickets[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public String GetTicket(){ //Getter method for tickets
        return ticket;
    }
}