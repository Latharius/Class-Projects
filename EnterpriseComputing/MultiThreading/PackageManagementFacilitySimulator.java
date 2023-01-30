/*
  Name: Leith Rabah
  Course: CNT 4714 Fall 2022
  Assignment title: Project 2 – Multi-threaded programming in Java
  Date:  October 2, 2022

  Class:  Enterprise Computing
*/
package EnterpriseComputing.MultiThreading;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PackageManagementFacilitySimulator {
    //max number of routing stations and conveyors that can be configured
    static int MAX = 10;
    static int[] workLoads;
    public static void main(String[] args) {
        try {
            File output = new File("outputSample.txt");
            PrintStream stream = new PrintStream(output);
            System.setOut(stream);

            System.out.println("\n* * * * * * * * * * PACKAGE MANAGEMENT FACILITY SIMULATION BEGINS * * * * * * * * * * \n");

            //read in config.txt file
            File file = new File("config.txt");
            Scanner configFile = new Scanner(file);

            //save the first integer in the config.txt file as number of routing stations in the simulation run
            int numStations = configFile.nextInt();

            //array list to store the integers from config.
            ArrayList<Integer> config = new ArrayList<Integer>();
            ArrayList<Integer> station = new ArrayList<Integer>();

            //create thread pool of MAX size
            ExecutorService pool = Executors.newFixedThreadPool(MAX);

            //read config.txt file into the config array
            while(configFile.hasNext()){
                int work = configFile.nextInt();
                config.add(work);
            }
            //assign the workloads to each station from the values in the config.txt file

            //file.close(); //close configuration file
            configFile.close();

            //create an array of conveyor objects
            Conveyor[] conveyors = new Conveyor[numStations];

            //fill the array with the conveyors for this simulation run
            for(int i = 0; i < numStations; i++)
                conveyors[i] = new Conveyor(i);

            //create the routing stations for this simulation run
            //loop through each routing station in the simulation
            for(int i = 0; i < numStations; i++){
                //start threads executing using the ExecutorService object
                pool.execute(new RoutingStation(i, config.get(i), conveyors[i], conveyors[(i + numStations - 1) % numStations]));
            }
            //application shutdown - different techniques for shutting down the ExecutorService are shown below
            pool.shutdown();

            while(true) {
                if (pool.isTerminated()) {
                    System.out.println("* * * * * * * * * * ALL WORKLOADS COMPLETE * * * PACKAGE MANAGEMENT FACILITY SIMULATION TERMINATES * * * * * * * * * *\n\n");
                    break;
                }
            }

        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
