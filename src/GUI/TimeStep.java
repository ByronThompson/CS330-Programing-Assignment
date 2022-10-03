package GUI;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class TimeStep {
    private JFormattedTextField textField1;
    private JPanel timeStep;
    private JButton submitButton;

    public TimeStep(){
        JFrame frame = new JFrame("");
        //frame.setJMenuBar(createMenuBar());
        frame.setContentPane(timeStep);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        formatTextField();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUISecretary.setTimeStep(Float.parseFloat(textField1.getText()));
                frame.dispose();
            }
        });
    }

    private void formatTextField(){
        textField1.setValue(GUISecretary.getTimeStep());
    }
}
