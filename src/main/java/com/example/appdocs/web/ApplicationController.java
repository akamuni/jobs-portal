package com.example.appdocs.web;

import com.example.appdocs.domain.Application;
import com.example.appdocs.domain.Status;
import com.example.appdocs.service.ApplicationService;
import com.example.appdocs.web.dto.*;
import javax.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public Application create(@Valid @RequestBody ApplicationCreateRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public Application get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping
    public PagedResponse<Application> list(
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "submittedAt,desc") String sort
    ) {
        Sort s = Sort.by(sort.split(",")[0]);
        if (sort.toLowerCase().endsWith(",desc")) s = s.descending();
        Page<Application> p = service.search(status, q, PageRequest.of(page, size, s));
        return new PagedResponse<>(p);
    }

    @PutMapping("/{id}")
    public Application update(@PathVariable Long id, @Valid @RequestBody ApplicationUpdateRequest req) {
        return service.update(id, req);
    }

    @PatchMapping("/{id}/status")
    public Application updateStatus(@PathVariable Long id, @Valid @RequestBody StatusUpdateRequest req) {
        return service.updateStatus(id, req.getStatus());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
