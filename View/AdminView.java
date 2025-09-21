package View;

import Models.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminView extends JFrame {
    public AdminView(RecruitmentModel model) {
        setTitle("Admin Panel");
        setSize(500,400);
        setLayout(new BorderLayout());

        List<Candidate> candidates = model.getCandidates();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for(Candidate c : candidates) listModel.addElement(c.getFullName() + " (" + c.getStatus() + ")");
        JList<String> candidateList = new JList<>(listModel);
        add(new JScrollPane(candidateList), BorderLayout.CENTER);
    }
}
