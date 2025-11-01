package com.example.appdocs.service;

import com.example.appdocs.domain.Application;
import com.example.appdocs.domain.Status;
import com.example.appdocs.repo.ApplicationRepository;
import com.example.appdocs.web.dto.ApplicationCreateRequest;
import com.example.appdocs.web.dto.ApplicationUpdateRequest;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApplicationService {
    private final ApplicationRepository repo;

    public ApplicationService(ApplicationRepository repo) {
        this.repo = repo;
    }

    public Application create(ApplicationCreateRequest req) {
        Application a = new Application();
        a.setApplicantName(req.getApplicantName());
        a.setEmail(req.getEmail());
        a.setPosition(req.getPosition());
        return repo.save(a);
    }

    @Transactional(readOnly = true)
    public Application get(Long id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Application not found: " + id));
    }

    public Application update(Long id, ApplicationUpdateRequest req) {
        Application a = get(id);
        a.setApplicantName(req.getApplicantName());
        a.setEmail(req.getEmail());
        a.setPosition(req.getPosition());
        return repo.save(a);
    }

    public Application updateStatus(Long id, Status status) {
        Application a = get(id);
        a.setStatus(status);
        return repo.save(a);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new IllegalArgumentException("Application not found: " + id);
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<Application> search(Status status, String q, Pageable pageable) {
        if (status != null && q != null && !q.trim().isEmpty()) {
            return repo.findByStatusAndApplicantNameContainingIgnoreCaseOrStatusAndPositionContainingIgnoreCase(
                    status, q, status, q, pageable);
        } else if (status != null) {
            return repo.findByStatus(status, pageable);
        } else if (q != null && !q.trim().isEmpty()) {
            return repo.findByApplicantNameContainingIgnoreCaseOrPositionContainingIgnoreCase(q, q, pageable);
        } else {
            return repo.findAll(pageable);
        }
    }
}
