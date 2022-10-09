package GUI;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class MainMenu {
    public JPanel MainPanel;
    private JList scenarioList;
    private JButton runScenarioButton;
    private JList objectList;
    private JButton addObjectButton;
    private JButton editObjectButton;
    //private JMenuBar menuBar;
    //private JMenu menu;
    private JMenuItem t;


    JMenu menu, submenu;
    JMenuItem maxTime, timeStep, dimensions;

    public MainMenu() {
        GUISecretary.setMainMenu(this);

        editObjectButton.setVisible(false);
        populateScenarioList();

        /*
        menuBar = new JMenuBar();
        menu = new JMenu();
        t = new JMenuItem("Test");
        menu.add(t);

        menuBar.add(menu);
        */

        addObjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editObjectButton.setVisible(!editObjectButton.isVisible());
            }
        });

        objectList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event){
                if(!event.getValueIsAdjusting()){
                    //editObjectButton.setVisible(true);
                }
            }
        });

        scenarioList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event){
                if(!event.getValueIsAdjusting()){
                    GUISecretary.setScenarioToRun((String) scenarioList.getSelectedValue());
                    updateObjectList();
                }
            }
        });

        JFrame frame = new JFrame("Byron Thompson CS330 Programming Assignment");
        frame.setJMenuBar(createMenuBar());
        frame.setContentPane(MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        runScenarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUISecretary.runSimulation();
            }
        });
    }

    private JMenuBar createMenuBar(){
        JMenuBar mb=new JMenuBar();
        menu=new JMenu("Settings");
        maxTime =new JMenuItem("Max Sim Time");
        timeStep =new JMenuItem("Sim Time Step");
        dimensions =new JMenuItem("Sim Dimensions");
        menu.add(maxTime); menu.add(timeStep); menu.add(dimensions);

        maxTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SimTime();
            }
        });

        timeStep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TimeStep();
            }
        });

        dimensions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dimensions();
            }
        });

        mb.add(menu);

        return mb;
    }

    private void populateScenarioList(){
        DefaultListModel<String> model = new DefaultListModel<>();
        ArrayList<String> scenarios = GUISecretary.getScenarioNames();
        Collections.sort(scenarios);

        for(String s : scenarios){
            model.addElement(s);
        }
        scenarioList.setModel(model);

        scenarioList.setSelectedIndex(0);
        GUISecretary.setScenarioToRun((String) scenarioList.getSelectedValue());
        updateObjectList();

    }

    private void updateObjectList(){
        ArrayList<String> objects = new ArrayList<>();

        objects = GUISecretary.getScenarioObjects();

        DefaultListModel<String> model = new DefaultListModel<>();
        Collections.sort(objects);

        for(String s : objects){
            model.addElement(s);
        }
        objectList.setModel(model);
    }
}
