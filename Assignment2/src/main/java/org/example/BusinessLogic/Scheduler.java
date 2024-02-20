package org.example.BusinessLogic;

import org.example.Model.Server;
import org.example.Model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        this.servers = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server(maxTasksPerServer);
            this.servers.add(server);
            Thread thread = new Thread(server);
            thread.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ShortestQueueStrategy();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ShortestTimeStrategy();
        }
    }

    public void dispatchTask(Task task) {
        strategy.addTasks(servers, task);
    }

    public List<Server> getServers() {
        return servers;
    }
}
