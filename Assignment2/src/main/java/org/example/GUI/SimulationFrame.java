package org.example.GUI;

import org.example.BusinessLogic.SimulationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SimulationFrame {
    private final JFrame frameSimulation;
    private final JTextField minIntervalArrivalTextField;
    private final JTextField maxIntervalArrival;
    private final JTextField minIntervalService;
    private final JTextField maxIntervalService;
    private final JTextField txtSimulationTime;
    private final JTextField txtNrQueues;
    private final JTextField txtNrClients;
    private SimulationManager simulationManager;
    public JTextField message;

    public SimulationFrame() {
        frameSimulation = new JFrame();
        frameSimulation.setResizable(false);
        frameSimulation.setBackground(Color.LIGHT_GRAY);
        frameSimulation.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        frameSimulation.setTitle("Queue management");
        frameSimulation.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frameSimulation.setBounds(100, 100, 460, 440);
        frameSimulation.getContentPane().setLayout(null);

        JLabel lblMinIntervalArrival = new JLabel("Min arrival time:");
        lblMinIntervalArrival.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblMinIntervalArrival.setBounds(10, 12, 100, 20);
        frameSimulation.getContentPane().add(lblMinIntervalArrival);

        JLabel lblMaxArrivalInterval = new JLabel("Max arrival time:");
        lblMaxArrivalInterval.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblMaxArrivalInterval.setBounds(230, 12, 100, 20);
        frameSimulation.getContentPane().add(lblMaxArrivalInterval);

        minIntervalArrivalTextField = new JTextField();
        minIntervalArrivalTextField.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        minIntervalArrivalTextField.setBounds(120, 12, 100, 20);
        frameSimulation.getContentPane().add(minIntervalArrivalTextField);
        minIntervalArrivalTextField.setColumns(10);

        maxIntervalArrival = new JTextField();
        maxIntervalArrival.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        maxIntervalArrival.setBounds(340, 12, 100, 20);
        frameSimulation.getContentPane().add(maxIntervalArrival);
        maxIntervalArrival.setColumns(10);

        JSeparator separator1 = new JSeparator();
        separator1.setBounds(10, 40, 430, 2);
        frameSimulation.getContentPane().add(separator1);

        JLabel lblMinServiceTime = new JLabel("Min service time:");
        lblMinServiceTime.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblMinServiceTime.setBounds(10, 60, 100, 20);
        frameSimulation.getContentPane().add(lblMinServiceTime);

        minIntervalService = new JTextField();
        minIntervalService.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        minIntervalService.setBounds(120, 60, 100, 20);
        frameSimulation.getContentPane().add(minIntervalService);
        minIntervalService.setColumns(10);

        JLabel lblMaxServiceTime = new JLabel("Max service time:");
        lblMaxServiceTime.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblMaxServiceTime.setBounds(230, 60, 100, 20);
        frameSimulation.getContentPane().add(lblMaxServiceTime);

        maxIntervalService = new JTextField();
        maxIntervalService.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        maxIntervalService.setColumns(10);
        maxIntervalService.setBounds(340, 60, 100, 20);
        frameSimulation.getContentPane().add(maxIntervalService);

        JSeparator separator2 = new JSeparator();
        separator2.setBounds(10, 90, 430, 2);
        frameSimulation.getContentPane().add(separator2);

        JLabel labelMaxSimulationTime = new JLabel("Max simulation time:");
        labelMaxSimulationTime.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        labelMaxSimulationTime.setBounds(10, 108, 100, 20);
        frameSimulation.getContentPane().add(labelMaxSimulationTime);

        txtSimulationTime = new JTextField();
        txtSimulationTime.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtSimulationTime.setBounds(120, 108, 100, 20);
        frameSimulation.getContentPane().add(txtSimulationTime);
        txtSimulationTime.setColumns(10);

        JSeparator separator3 = new JSeparator();
        separator3.setBounds(10, 138, 430, 2);
        frameSimulation.getContentPane().add(separator3);

        JLabel lblNumberOfQueues = new JLabel("Number of queues:");
        lblNumberOfQueues.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNumberOfQueues.setBounds(10, 156, 100, 20);
        frameSimulation.getContentPane().add(lblNumberOfQueues);

        txtNrQueues = new JTextField();
        txtNrQueues.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNrQueues.setBounds(120, 156, 100, 20);
        frameSimulation.getContentPane().add(txtNrQueues);
        txtNrQueues.setColumns(10);

        JSeparator separator4 = new JSeparator();
        separator4.setBounds(10, 186, 430, 2);
        frameSimulation.getContentPane().add(separator4);

        JLabel lblNumberOfClients = new JLabel("Number of clients:");
        lblNumberOfClients.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblNumberOfClients.setBounds(10, 204, 100, 20);
        frameSimulation.getContentPane().add(lblNumberOfClients);

        txtNrClients = new JTextField();
        txtNrClients.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        txtNrClients.setBounds(120, 204, 100, 20);
        frameSimulation.getContentPane().add(txtNrClients);
        txtNrClients.setColumns(10);

        JSeparator separator5 = new JSeparator();
        separator5.setBounds(10, 234, 430, 2);
        frameSimulation.getContentPane().add(separator5);

        JLabel lblWait = new JLabel("Wait for the simulation to stop until a message is displayed below!");
        lblWait.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        lblWait.setBounds(10, 254, 340, 20);
        frameSimulation.getContentPane().add(lblWait);

        message = new JTextField();
        message.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        message.setColumns(10);
        message.setBounds(10, 284, 430, 20);
        frameSimulation.getContentPane().add(message);

        JButton btnStartSimulation = new JButton("Start simulation");
        btnStartSimulation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    int numberOfServers, numberofClients, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, timeLimit;
                    numberOfServers = Integer.parseInt(txtNrQueues.getText());
                    numberofClients = Integer.parseInt(txtNrClients.getText());
                    minArrivalTime = Integer.parseInt(minIntervalArrivalTextField.getText());
                    maxArrivalTime = Integer.parseInt(maxIntervalArrival.getText());
                    minServiceTime = Integer.parseInt(minIntervalService.getText());
                    maxServiceTime = Integer.parseInt(maxIntervalService.getText());
                    timeLimit = Integer.parseInt(txtSimulationTime.getText());
                    simulationManager = new SimulationManager(numberOfServers, numberofClients, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime, message);
                    simulationManager.setTimeLimit(timeLimit);
                    Thread thread = new Thread(simulationManager);
                    thread.start();
                    simulationManager.startSimulation();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Please introduce the requested data!");
                }
            }
        });
        btnStartSimulation.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnStartSimulation.setBounds(10, 320, 120, 40);
        frameSimulation.getContentPane().add(btnStartSimulation);

        JButton btnOpenLogEvents = new JButton("See log of events");
        btnOpenLogEvents.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Desktop.getDesktop().open(new File("log_events.txt"));
                } catch (Exception e) {
                    System.out.println("Error!");
                }
            }
        });
        btnOpenLogEvents.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnOpenLogEvents.setBounds(160, 320, 120, 40);
        frameSimulation.getContentPane().add(btnOpenLogEvents);

        JButton btnOpenResults = new JButton("See results");
        btnOpenResults.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    Desktop.getDesktop().open(new File("results.txt"));
                    message.setText("");
                } catch (Exception e) {
                    System.out.println("Error!");
                }
            }
        });
        btnOpenResults.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        btnOpenResults.setBounds(310, 320, 120, 40);
        frameSimulation.getContentPane().add(btnOpenResults);
    }

    public static void main(String[] args) {
        {
            try {
                SimulationFrame window = new SimulationFrame();
                window.frameSimulation.setVisible(true);

            } catch (Exception e) {
                System.out.println("ERROR!");
            }
        }
    }
}
