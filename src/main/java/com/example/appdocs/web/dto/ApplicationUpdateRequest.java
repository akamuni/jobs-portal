package com.example.appdocs.web.dto;

import javax.validation.constraints.*;

public class ApplicationUpdateRequest {
    @NotBlank @Size(min=2, max=100)
    private String applicantName;

    @NotBlank @Email
    private String email;

    @NotBlank
    private String position;

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}
