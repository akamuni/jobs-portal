package com.example.appdocs.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class DocumentCreateRequest {
    @NotBlank
    private String fileName;

    private String fileType;

    @NotBlank
    @Pattern(
            regexp = "^(https?://)([\\w.-]+)(:[0-9]+)?(/[\\w.,@?^=%&:/~+#-]*)?$",
            message = "url must be a valid http/https URL"
    )
    private String url;

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
