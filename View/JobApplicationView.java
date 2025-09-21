package View;

import Models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JobApplicationView extends JFrame {
    private JButton btnApply;
    private Job job;
    private Candidate candidate;

    public JobApplicationView(Job job, Candidate candidate) {
        this.job = job;
        this.candidate = candidate;
        setTitle("Apply Job: " + job.getTitle());
        setSize(300,200);
        setLayout(new BorderLayout());

        JLabel lbl = new JLabel("Applying for: " + job.getTitle() + " (" + job.getJobType() + ")");
        add(lbl, BorderLayout.CENTER);

        btnApply = new JButton("Confirm Apply");
        add(btnApply, BorderLayout.SOUTH);
    }

    public void addApplyListener(ActionListener l) {
        btnApply.addActionListener(l);
    }
}
