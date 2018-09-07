package com.teamproject.drinkit.service;

import com.teamproject.drinkit.exception.FileStorageException;
import com.teamproject.drinkit.exception.MyFileNotFoundException;
import com.teamproject.drinkit.property.FileStorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileStorageService {

    private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try{
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new FileStorageException("파일을 저장할 디렉토리를 생성할 수 없습니다.");
        }
    }

    public UploadFileResponse storeFile(MultipartFile file){
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")){
                throw new FileStorageException("파일 이름이 유효하지 않은 문자를 담고 있습니다." + fileName);
            }
            String randomFileName = generateRandomFileName(fileName);
            Path targetLocation = this.fileStorageLocation.resolve(randomFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return new UploadFileResponse(fileName, randomFileName, file.getContentType(), file.getSize());
        } catch (IOException e) {
            throw new FileStorageException(String.format("파일을 저장할 수 없습니다. 파일 이름 : %s", fileName), e);
        }
    }

    private String generateRandomFileName(String fileName) {
        int index = fileName.lastIndexOf(".");
        return fileName.substring(0, index) + UUID.randomUUID() + '.' + fileName.substring(index+1);
    }

    public Resource loadFileAsResource(String randomFileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(randomFileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }
            throw new MyFileNotFoundException(String.format("찾는 파일이 존재하지 않습니다. 파일 이름 : %s", randomFileName));
        } catch (MalformedURLException e) {
            throw new MyFileNotFoundException(String.format("찾는 파일이 존재하지 않습니다. 파일 이름 : %s", randomFileName, e));
        }
    }
}
