package com.example.appdocs.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "applications")
public class Application {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=2, max=100)
    private String applicantName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String position;

    @Enumerated(EnumType.STRING)
    private Status status = Status.SUBMITTED;

    private Instant submittedAt;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Document> documents = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (submittedAt == null) submittedAt = Instant.now();
        if (status == null) status = Status.SUBMITTED;
    }

    public Long getId() { return id; }
    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Instant getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(Instant submittedAt) { this.submittedAt = submittedAt; }
    public List<Document> getDocuments() { return documents; }
    public void setDocuments(List<Document> documents) { this.documents = documents; }

    public void addDocument(Document doc) {
        this.documents.add(doc);
        doc.setApplication(this);
    }

    public void removeDocument(Document doc) {
        this.documents.remove(doc);
        doc.setApplication(null);
    }
}
