/*
  Name: Leith Rabah
  Course: CNT 4714 Fall 2022
  Assignment title: Project 2 – Multi-threaded programming in Java
  Date:  October 2, 2022

  Class:  Enterprise Computing
*/
package EnterpriseComputing.MultiThreading;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Random;

public class RoutingStation implements Runnable {
    //define all the RoutingStation attributes
    //protected
    protected Random sleep = new Random();
    protected int work = 0;
    protected int numStation;
    protected int counter;
    protected Conveyor input;
    protected Conveyor output;
    protected boolean locks = false;


    //RoutingStation constructor method
    public RoutingStation(int numStation, int work, Conveyor input, Conveyor output){
        //build a RoutingStation object
        this.work = work;
        this.numStation = numStation;
        this.input = input;
        this.output = output;
        counter = work;
    }

    //method for threads to go to sleep
    //note: a sleeping thread in java maintains all resources allocated to it. Including locks.
    //Locks are not relinquished during a sleep cycle
    public void goToSleep(){
        try{
            Thread.sleep(sleep.nextInt(500)); //sleep a random time up to 500ms - adjust time as needed
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    //method for simulating Routing Station work - i.e. some time period during which the station is moving packages
    public void doWork(){
        //print simulation output messages
        System.out.println("\n* * Routing Station " + numStation + ": * * CURRENTLY HARD AT WORK MOVING PACKAGES.\n");
        //decrement workload counter
        work--;
        System.out.println("Routing Station " + numStation + ": has " + work + " package groups left to move.");

        //hold the conveyors for a random period of time to simulate work flow, i.e. sleep the thread
        goToSleep();
        //check if workload has reached 0 - if so, print out message indicating station is done
        //if(work == 0)
            //.out.println("\n#  #  Station  " + numStation + ":  Workload  successfully  completed.  *  *  Station  " + numStation + " preparing to go offline. # # \n");
    }

    //the run() method - this is what a station does
    @Override
    public void run() {
        //dump out the conveyor assignments and workload settings for the station - simulation output criteria
        System.out.println("\n% % % % % ROUTING STATION " + numStation + " Coming Online - Initializing Conveyors % % % % % \n");
        System.out.println("Routing Station " + numStation + ": Input conveyor is set to conveyor number C" + input.conveyor + ".");
        System.out.println("Routing Station " + numStation + ": Output conveyor is set to conveyor number C" + output.conveyor + ".");
        System.out.println("Routing Station " + numStation + " Has Total Workload of " + work + " Package Groups.");

        //run the simulation on the station for its entire workload
        while (counter != 0) {
            //loop for the workload of this station
            //loop until both locks acquired
            while (!locks) {
                //get input lock
                if (input.lockConveyor()) {
                    System.out.println("Routing Station " + numStation + ": holds lock on input conveyor C" + input.conveyor + ".");
                    //attempt to get output lock
                    //if both locks acquired - then go to work
                    if (output.lockConveyor()) {
                        System.out.println("Routing Station " + numStation + ": holds lock on output conveyor C" + output.conveyor + ".");
                        doWork();
                        //unlock input conveyor
                        input.unlockConveyor();
                        //unlock output conveyor
                        output.unlockConveyor();

                        locks = false;

                        System.out.println("Routing Station " + numStation + ": unlocks/releases input conveyor C" + input.conveyor + ".");
                        System.out.println("Routing Station " + numStation + ": unlocks/releases output conveyor C" + output.conveyor + ".");

                        if(work == 0){
                            System.out.println("#  #  Station  " + numStation + ":  Workload  successfully  completed.  *  *  Station  " + numStation + " preparing to go offline. # # ");
                            locks = true;
                        }
                    }
                    // if not - release input lock and sleep for a while
                    else {
                        System.out.println("Routing Station " + numStation + ": unable to lock output conveyor C" + output.conveyor + "." +
                                " SYNCHRONIZATION ISSUE: Station " + numStation + " currently holds the lock on output conveyor C" + output.conveyor + " - releasing lock on input conveyor C" + input.conveyor);
                        input.unlockConveyor();
                        goToSleep();
                    } //lock acquisition loop ends
                    goToSleep();
                }
            }
            counter--;
        }
        //loop ends - station is done - print out message
        System.out.println("\n\n @ @ @ @ @ @ @ ROUTING STATION " + numStation + ": OFFLINE @ @ @ @ @ @ @ \n\n");
    }
}
