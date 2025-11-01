package com.example.appdocs.service;

import com.example.appdocs.domain.Application;
import com.example.appdocs.domain.Document;
import com.example.appdocs.repo.ApplicationRepository;
import com.example.appdocs.repo.DocumentRepository;
import com.example.appdocs.web.dto.DocumentCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class DocumentService {
    private final DocumentRepository docRepo;
    private final ApplicationRepository appRepo;

    public DocumentService(DocumentRepository docRepo, ApplicationRepository appRepo) {
        this.docRepo = docRepo;
        this.appRepo = appRepo;
    }

    public Document create(Long applicationId, DocumentCreateRequest req) {
        Application app = appRepo.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found: " + applicationId));
        Document d = new Document();
        d.setFileName(req.getFileName());
        d.setFileType(req.getFileType());
        d.setUrl(req.getUrl());
        d.setApplication(app);
        return docRepo.save(d);
    }

    @Transactional(readOnly = true)
    public List<Document> listByApplication(Long applicationId) {
        appRepo.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found: " + applicationId));
        return docRepo.findByApplicationId(applicationId);
    }

    @Transactional(readOnly = true)
    public Document get(Long docId) {
        return docRepo.findById(docId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found: " + docId));
    }

    public void delete(Long docId) {
        if (!docRepo.existsById(docId)) throw new IllegalArgumentException("Document not found: " + docId);
        docRepo.deleteById(docId);
    }
}
