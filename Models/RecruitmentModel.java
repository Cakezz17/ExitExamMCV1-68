package Models;

import java.io.*;
import java.util.*;

public class RecruitmentModel {
    private List<Company> companies = new ArrayList<>();
    private List<Job> jobs = new ArrayList<>();
    private List<Candidate> candidates = new ArrayList<>();

    public RecruitmentModel() {
        loadCompanies();
        loadJobs();
        loadCandidates();
    }

    private void loadCompanies() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(RecruitmentModel.class.getResourceAsStream("database/Companies.csv")))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4) companies.add(new Company(data[0], data[1], data[2], data[3]));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadJobs() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(RecruitmentModel.class.getResourceAsStream("database/Jobs.csv")))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",",7);
                if (data.length>=7) jobs.add(new Job(data[0],data[1],data[2],data[3],data[4],data[5],data[6]));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadCandidates() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(RecruitmentModel.class.getResourceAsStream("database/Candidate.csv")))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",",5);
                if (data.length>=5) candidates.add(new Candidate(data[0],data[1],data[2],data[3],data[4]));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<Job> getJobs() { return jobs; }
    public List<Company> getCompanies() { return companies; }
    public List<Candidate> getCandidates() { return candidates; }

    // filter jobs เปิด
    public List<Job> getOpenJobs() {
        List<Job> openJobs = new ArrayList<>();
        for(Job j : jobs) if(j.isOpen()) openJobs.add(j);
        return openJobs;
    }

    // ตรวจสอบสมัครงาน
    public boolean canApply(Job job, Candidate candidate) {
        if(!job.isOpen()) return false;
        if(job.getJobType().equalsIgnoreCase("co-op") && !candidate.getStatus().equalsIgnoreCase("studying")) return false;
        if(job.getJobType().equalsIgnoreCase("regular") && !candidate.getStatus().equalsIgnoreCase("graduated")) return false;
        return true;
    }
}
