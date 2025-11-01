package com.example.appdocs.web.dto;

import com.example.appdocs.domain.Status;
import javax.validation.constraints.NotNull;

public class StatusUpdateRequest {
    @NotNull
    private Status status;

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
