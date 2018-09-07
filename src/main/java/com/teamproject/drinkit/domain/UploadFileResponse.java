package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Embeddable
public class UploadFileResponse {

    private String fileName;
    private String randomlyGeneratedFileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;

    public UploadFileResponse(){ }
    public UploadFileResponse(String fileName, String randomlyGeneratedFileName, String fileType, Long size) {
        this.fileName = fileName;
        this.randomlyGeneratedFileName = randomlyGeneratedFileName;
        this.fileDownloadUri = createDownloadUri(randomlyGeneratedFileName);
        this.fileType = fileType;
        this.size = size;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public Long getSize() {
        return size;
    }
    private String createDownloadUri(String randomlyGeneratedFileName){
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(randomlyGeneratedFileName).toUriString();
    }

}
