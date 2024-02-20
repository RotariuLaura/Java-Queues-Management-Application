package org.example.Model;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    int maxTasks;

    public Server(int maxTasks) {
        this.tasks = new ArrayBlockingQueue<>(maxTasks);
        this.waitingPeriod = new AtomicInteger(0);
        this.maxTasks = maxTasks;
    }

    public synchronized void addTask(Task task) {
        tasks.add(task);
        int serviceTime = task.getServiceTime();
        waitingPeriod.addAndGet(serviceTime);
    }

    public void run() {
        while (true) {
            if (!tasks.isEmpty()) {
                try {
                    Task task = tasks.element();
                    Thread.sleep(1000);

                } catch (Exception e) {
                }
            }
        }
    }

    public void decrementWaitingPeriod() {
        waitingPeriod.addAndGet(-1);
    }

    public synchronized void removeTask(int i) {
        int index = 0;
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            if (index == i) {
                Task task = iterator.next();
                iterator.remove();
                break;
            }
            index++;
        }
    }

    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }
}
