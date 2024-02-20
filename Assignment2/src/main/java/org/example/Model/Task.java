package org.example.Model;

public class Task implements Comparable<Task> {
    private final int ID;
    private final int arrivalTime;
    private int serviceTime;

    public Task(int ID, int arrivalTime, int serviceTime) {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getID() {
        return ID;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "(" + ID + "," + arrivalTime + "," + serviceTime + ')';
    }

    public int compareTo(Task other) {
        if (this.arrivalTime == other.arrivalTime) {
            return this.serviceTime - other.serviceTime;
        }
        return this.arrivalTime - other.arrivalTime;
    }
}
