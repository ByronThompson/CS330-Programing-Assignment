package GUI;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class SimTime {
    private JFormattedTextField textField1;
    private JPanel maxTime;
    private JButton submitButton;

    public SimTime(){
        JFrame frame = new JFrame("");
        //frame.setJMenuBar(createMenuBar());
        frame.setContentPane(maxTime);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        formatTextField();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUISecretary.setSimTime(Float.parseFloat(textField1.getValue().toString()));
                frame.dispose();
            }
        });
    }

    private void formatTextField(){
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        textField1.setFormatterFactory(new DefaultFormatterFactory(formatter));

        textField1.setValue(GUISecretary.getSimTime());
    }
}
