package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
public class UploadFileResponse {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;

    public UploadFileResponse(){ }
    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, Long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
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
}
