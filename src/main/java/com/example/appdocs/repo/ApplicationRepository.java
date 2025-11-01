package com.example.appdocs.repo;

import com.example.appdocs.domain.Application;
import com.example.appdocs.domain.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    Page<Application> findByStatus(Status status, Pageable pageable);

    Page<Application> findByApplicantNameContainingIgnoreCaseOrPositionContainingIgnoreCase(
            String applicantName, String position, Pageable pageable);

    Page<Application> findByStatusAndApplicantNameContainingIgnoreCaseOrStatusAndPositionContainingIgnoreCase(
            Status status1, String applicantName,
            Status status2, String position,
            Pageable pageable
    );
}
