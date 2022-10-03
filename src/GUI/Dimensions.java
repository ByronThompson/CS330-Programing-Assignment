package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dimensions {
    private JButton submitButton;
    private JPanel panel1;
    private JRadioButton a2DRadioButton;
    private JRadioButton a3DRadioButton;

    public Dimensions(){
        JFrame frame = new JFrame("");
        //frame.setJMenuBar(createMenuBar());
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //GUISecretary.setSimTime(Float.parseFloat(textField1.getValue().toString()));
                frame.dispose();
            }
        });
        a2DRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a3DRadioButton.setSelected(!a2DRadioButton.isSelected());
            }
        });

        a3DRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a2DRadioButton.setSelected(!a3DRadioButton.isSelected());
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUISecretary.setSimDimensions(a3DRadioButton.isSelected());
            }
        });

        a2DRadioButton.setSelected(!GUISecretary.getSimDimensions());
        a3DRadioButton.setSelected(!a2DRadioButton.isSelected());

    }
}
