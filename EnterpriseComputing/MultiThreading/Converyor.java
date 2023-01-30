/*
  Name: Leith Rabah
  Course: CNT 4714 Fall 2022
  Assignment title: Project 2 – Multi-threaded programming in Java
  Date:  October 2, 2022

  Class:  Enterprise Computing
*/
package EnterpriseComputing.MultiThreading;

import java.util.concurrent.locks.ReentrantLock;

public class Conveyor {
    //define the attributes of a conveyor
    int conveyor;
    //define a lock on the conveyor object - a ReentrantLock() with no fairness policy - starvation not an issue in this application
    public ReentrantLock lock = new ReentrantLock();

    //constructor method - simply assign the conveyor its number
    public Conveyor(int conveyor){
        this.conveyor = conveyor;
    }

    //method for routing stations to acquire a conveyor lock
    public boolean lockConveyor(){
        //use tryLock()
        //tryLock() returns true if the lock request is granted by the Lock Manager
        if(lock.tryLock())
            return true;
        //i.e. the lock was free and was granted to the requesting thread - otherwise return is false
        return false;
    }

    //method for routing stations to release a conveyor lock
    public void unlockConveyor(){
        //simply call unluck() on the theLock
        lock.unlock();
    }
}
