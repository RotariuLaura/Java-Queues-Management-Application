package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import javax.swing.*;
import java.io.FileWriter;
import java.util.*;

public class SimulationManager implements Runnable {
    private Scheduler scheduler;
    private List<Task> generatedTasks;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
    static FileWriter fileLogs, fileResults;
    private int timeLimit;
    private JTextField message;
    private int currentTime = 0, totalTasks = 0, totalTime = 0, maxTasks = 0, peakHour = 0;
    private double averageWaitingTime, averageServiceTime = 0.0;

    public SimulationManager(int numberOfServers, int numberOfClients, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, JTextField message) {
        this.generatedTasks = new ArrayList<>();
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        scheduler.changeStrategy(selectionPolicy);
        this.generateNRandomTasks(numberOfClients, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
        this.message = message;
    }

    public void generateNRandomTasks(int numberOfClients, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime) {
        int sumServiceTime = 0;
        Random rand = new Random();
        for (int i = 0; i < numberOfClients; i++) {
            int serviceTime = rand.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime;
            int arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            Task task = new Task(i + 1, arrivalTime, serviceTime);
            generatedTasks.add(task);
            sumServiceTime += task.getServiceTime();
        }
        averageServiceTime = (double) sumServiceTime / (double) numberOfClients;
        Collections.sort(generatedTasks);
    }

    public void processTasks() throws Exception {
        int tasks = 0;
        for (int i = 0; i < scheduler.getServers().size(); i++) {
            Server server = scheduler.getServers().get(i);
            tasks += server.getTasks().length;
            if (server.getTasks().length == 0) {
                fileLogs.write("Queue " + (i + 1) + ": closed\n");
            } else {
                fileLogs.write("Queue " + (i + 1) + ": " + Arrays.toString(server.getTasks()) + "\n");
                if (server.getTasks()[0].getServiceTime() > 0) {
                    server.getTasks()[0].setServiceTime(server.getTasks()[0].getServiceTime() - 1);
                    server.decrementWaitingPeriod();
                }
                for (int j = 0; j < server.getTasks().length; j++) {
                    if (server.getTasks()[j].getServiceTime() == 0) {
                        totalTime += currentTime - server.getTasks()[j].getArrivalTime();
                        server.removeTask(j);
                    }
                }
            }
            if (tasks > maxTasks) {
                maxTasks = tasks;
                peakHour = currentTime;
            }
        }
    }

    public void writeResultsFile() throws Exception {
        StringBuilder results = new StringBuilder();
        averageWaitingTime = (double) totalTime / (double) totalTasks;
        results.append("Average service time: ").append(averageServiceTime).append("\n").append("Average waiting time: ");
        results.append(averageWaitingTime).append("\n").append("Peak hour: ").append(peakHour);
        fileResults.write(String.valueOf(results));
        fileLogs.close();
        fileResults.close();
    }

    public void writeLogEvents() throws Exception {
        fileLogs.write("Time " + currentTime + "\n");
        fileLogs.write("Waiting clients:\n");
        for (Task task : generatedTasks)
            fileLogs.write("(" + task.getID() + "," + task.getArrivalTime() + "," + task.getServiceTime() + ") ");
        fileLogs.write("\n");
    }

    public void run() {
        try {
            while (currentTime < timeLimit) {
                for (int i = generatedTasks.size() - 1; i >= 0; i--) {
                    if (generatedTasks.get(i).getArrivalTime() == currentTime) {
                        scheduler.dispatchTask(generatedTasks.get(i));
                        generatedTasks.remove(i);totalTasks++;
                    }
                }
                writeLogEvents();
                processTasks();
                currentTime++;
                Thread.sleep(1000);
                int close = 1;
                for (int i = 0; i < scheduler.getServers().size(); i++) {
                    Server server = scheduler.getServers().get(i);
                    if (server.getTasks().length != 0) {
                        close = 0;
                    }
                }
                if (close == 1 && generatedTasks.size() == 0) {
                    fileLogs.write("Simulation ended\n"); break;
                }
            }
            writeResultsFile();
            message.setText("Check the log of events and the results!");
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

    public void startSimulation() {
        try {
            fileLogs = new FileWriter("log_events.txt");
            fileResults = new FileWriter("results.txt");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }
}
