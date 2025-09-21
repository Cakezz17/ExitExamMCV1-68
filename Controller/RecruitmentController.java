package Controller;

import Models.*;
import View.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecruitmentController {
    private RecruitmentModel model;
    private LoginView loginView;
    private JobListView jobListView;
    private JobApplicationView jobAppView;
    private AdminView adminView;

    public RecruitmentController(RecruitmentModel model, LoginView loginView) {
        this.model = model;
        this.loginView = loginView;

        //Login
        loginView.addLoginListener(e -> {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            if(username.equals("admin") && password.equals("admin")) {//สำหรับ admin
                loginView.dispose();
                adminView = new AdminView(model);
                adminView.setVisible(true);
            } else {
                Candidate candidate = model.getCandidates().stream()
                        .filter(c -> c.getCandidateId().equals(username))
                        .findFirst().orElse(null);
                if(candidate != null && password.equals("1234")) { //ใช้ id ใน candidate.csv ได้เลย pwd คือ 1234 ทุกอัน
                    loginView.dispose();
                    jobListView = new JobListView(model, candidate, this);
                    jobListView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed!");
                }
            }
        });
    }

    public void showJobApplicationView(Job job, Candidate candidate) {
        jobAppView = new JobApplicationView(job, candidate);
        jobAppView.addApplyListener(e -> {
            //Business rules
            LocalDate deadline = LocalDate.parse(job.getDeadline(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate today = LocalDate.now();

            if(today.isAfter(deadline)) {
                JOptionPane.showMessageDialog(null, "Cannot apply: deadline passed!");
                return;
            }
            if(!job.getJobType().equalsIgnoreCase("co-op") && candidate.getStatus().equalsIgnoreCase("studying")) {
                JOptionPane.showMessageDialog(null, "Cannot apply: candidate studying for regular job!");
                return;
            }
            if(job.getJobType().equalsIgnoreCase("co-op") && !candidate.getStatus().equalsIgnoreCase("studying")) {
                JOptionPane.showMessageDialog(null, "Cannot apply: candidate must be studying!");
                return;
            }

            JOptionPane.showMessageDialog(null, "Applied successfully on " + today);
            jobAppView.dispose();
            jobListView.setVisible(true);
        });
        jobAppView.setVisible(true);
        jobListView.setVisible(false);
    }
}
