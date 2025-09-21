package View;

import Models.*;
import Controller.RecruitmentController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class JobListView extends JFrame {
    private JList<Job> jobJList;
    private JButton btnApply;
    private RecruitmentController controller;
    private Candidate candidate;

    public JobListView(RecruitmentModel model, Candidate candidate, RecruitmentController controller) {
        this.controller = controller;
        this.candidate = candidate;
        setTitle("Job List");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        List<Job> openJobs = model.getOpenJobs();
        jobJList = new JList<>(openJobs.toArray(new Job[0]));
        add(new JScrollPane(jobJList), BorderLayout.CENTER);

        btnApply = new JButton("Apply");
        add(btnApply, BorderLayout.SOUTH);

        btnApply.addActionListener(e -> {
            Job selected = jobJList.getSelectedValue();
            if(selected != null)
                controller.showJobApplicationView(selected, candidate);
        });
    }
}
