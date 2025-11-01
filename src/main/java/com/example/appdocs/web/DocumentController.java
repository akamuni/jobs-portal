package com.example.appdocs.web;

import com.example.appdocs.domain.Document;
import com.example.appdocs.service.DocumentService;
import com.example.appdocs.web.dto.DocumentCreateRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DocumentController {

    private final DocumentService service;

    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @PostMapping("/api/applications/{id}/documents")
    public Document create(@PathVariable Long id, @Valid @RequestBody DocumentCreateRequest req) {
        return service.create(id, req);
    }

    @GetMapping("/api/applications/{id}/documents")
    public List<Document> list(@PathVariable Long id) {
        return service.listByApplication(id);
    }

    @GetMapping("/api/documents/{docId}")
    public Document get(@PathVariable Long docId) {
        return service.get(docId);
    }

    @DeleteMapping("/api/documents/{docId}")
    public void delete(@PathVariable Long docId) {
        service.delete(docId);
    }
}
