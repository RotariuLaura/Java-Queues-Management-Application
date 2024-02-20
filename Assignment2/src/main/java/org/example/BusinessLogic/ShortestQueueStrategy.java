package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import java.util.List;

public class ShortestQueueStrategy implements Strategy {
    @Override
    public void addTasks(List<Server> servers, Task task) {
        Server shortestServer = servers.get(0);
        for (Server server : servers) {
            if (server.getTasks().length < shortestServer.getTasks().length) {
                shortestServer = server;
            }
        }
        shortestServer.addTask(task);
    }
}
