package Models;

public class Candidate {
    private String candidateId, firstName, lastName, email, status;

    public Candidate(String candidateId, String firstName, String lastName, String email, String status) {
        this.candidateId = candidateId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
    }

    public String getCandidateId() { return candidateId; }
    public String getFullName() { return firstName + " " + lastName; }
    public String getEmail() { return email; }
    public String getStatus() { return status; }
}
