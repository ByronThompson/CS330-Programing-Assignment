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
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem t;

    public MainMenu() {
        editObjectButton.setVisible(false);
        populateScenarioList();

        menuBar = new JMenuBar();
        menu = new JMenu();
        t = new JMenuItem("Test");
        menu.add(t);

        menuBar.add(menu);

        addObjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editObjectButton.setVisible(!editObjectButton.isVisible());
            }
        });

        objectList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event){
                if(!event.getValueIsAdjusting()){
                    editObjectButton.setVisible(true);
                }
            }
        });

        JFrame frame = new JFrame("Byron Thompson CS330 Programming Assignment");
        frame.setJMenuBar(menuBar);
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

    private void populateScenarioList(){
        DefaultListModel<String> model = new DefaultListModel<>();
        ArrayList<String> scenarios = GUISecretary.getScenarioNames();
        Collections.sort(scenarios);

        for(String s : scenarios){
            System.out.println("s");
            model.addElement(s);
        }
        scenarioList.setModel(model);

    }
}
