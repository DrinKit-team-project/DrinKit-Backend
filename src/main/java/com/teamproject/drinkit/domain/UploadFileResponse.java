package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
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
}
