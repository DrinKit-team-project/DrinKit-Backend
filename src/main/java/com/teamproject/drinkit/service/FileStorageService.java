package com.teamproject.drinkit.service;

import com.teamproject.drinkit.domain.FileRepository;
import com.teamproject.drinkit.domain.MyFile;
import com.teamproject.drinkit.exception.FileStorageException;
import com.teamproject.drinkit.exception.MyFileNotFoundException;
import com.teamproject.drinkit.property.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

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

    public String storeFile(MultipartFile file){
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")){
                throw new FileStorageException("파일 이름이 유효하지 않은 문자를 담고 있습니다." + fileName);
            }

            // Copy file to target directory
            // path = fileStorageLocation/fileName
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            // 이름 겹치면 overwrite 되는 옵션.
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new FileStorageException(String.format("파일을 저장할 수 없습니다. 파일 이름 : %s", fileName), e);
        }
    }

    public Resource loadFileAsResource(String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }
            throw new MyFileNotFoundException(String.format("찾는 파일이 존재하지 않습니다. 파일 이름 : %s", fileName));
        } catch (MalformedURLException e) {
            throw new MyFileNotFoundException(String.format("찾는 파일이 존재하지 않습니다. 파일 이름 : %s", fileName, e));
        }
    }
}
