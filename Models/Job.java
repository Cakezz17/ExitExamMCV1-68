package Models;

public class Job {
    private String jobId, title, description, companyId, deadline, status, jobType;

    public Job(String jobId, String title, String description, String companyId, String deadline, String status, String jobType) {
        this.jobId = jobId;
        this.title = title;
        this.description = description;
        this.companyId = companyId;
        this.deadline = deadline;
        this.status = status;
        this.jobType = jobType;
    }

    public String getJobId() { return jobId; }
    public String getTitle() { return title; }
    public String getCompanyId() { return companyId; }
    public String getDeadline() { return deadline; }
    public String getJobType() { return jobType; }
    public boolean isOpen() { return status.equalsIgnoreCase("open"); }

    @Override
    public String toString() {
        return title + " (" + jobType + ")" + (isOpen() ? " [OPEN]" : " [CLOSED]");
    }
}

