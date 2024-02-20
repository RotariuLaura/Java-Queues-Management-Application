package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import java.util.List;

public class ShortestTimeStrategy implements Strategy {
    @Override
    public void addTasks(List<Server> servers, Task task) {
        Server shortestServer = servers.get(0);
        for (Server server : servers) {
            if (server.getWaitingPeriod().get() < shortestServer.getWaitingPeriod().get()) {
                shortestServer = server;
            }
        }
        shortestServer.addTask(task);
    }
}
