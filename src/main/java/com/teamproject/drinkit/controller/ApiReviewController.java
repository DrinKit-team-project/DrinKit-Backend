package com.teamproject.drinkit.controller;

import com.sun.org.apache.xpath.internal.operations.Mult;
import com.teamproject.drinkit.domain.CreateReviewResponse;
import com.teamproject.drinkit.domain.Menu;
import com.teamproject.drinkit.domain.Review;
import com.teamproject.drinkit.domain.UploadFileResponse;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.security.jwt.JwtDecoder;
import com.teamproject.drinkit.service.FileStorageService;
import com.teamproject.drinkit.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/menus/{menuId}/reviews")
public class ApiReviewController {
    private static final Logger log = LoggerFactory.getLogger(ApiReviewController.class);

    @Resource(name = "reviewService")
    private ReviewService reviewService;

    // TODO : 파일 한글이름 깨짐현상 해결하기

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("")
    public ReviewDto addReview(@RequestHeader("Authorization") String header, @PathVariable Long menuId, @RequestParam("ratings") double ratings,
                               @RequestParam("contents") String contents, @RequestParam(value = "file", required = false) MultipartFile file) {
        if(file != null) {
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(fileName).toUriString();
            log.debug("fileName :{}", fileName);
            log.debug("fileDownloadUri :{}", fileDownloadUri);

            UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
            return reviewService.addReview(header, menuId, new ReviewDto(ratings, contents, uploadFileResponse));
        }
        return reviewService.addReview(header, menuId, new ReviewDto(ratings, contents));
    }

    @PostMapping("/{id}")
    public ReviewDto editReview(@RequestHeader("Authorization") String header, @PathVariable Long menuId, @PathVariable Long id, @RequestParam("ratings") double ratings,
                                @RequestParam("contents") String contents, @RequestParam(value = "file", required = false) MultipartFile file) {
        log.debug("ratings :{}", ratings);
        if(file != null) {
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(fileName).toUriString();
            log.debug("fileName :{}", fileName);
            log.debug("fileDownloadUri :{}", fileDownloadUri);

            UploadFileResponse uploadFileResponse = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
            return reviewService.edit(header, menuId, new ReviewDto(id, ratings, contents, uploadFileResponse));
        }
        return reviewService.edit(header, menuId, new ReviewDto(id, ratings, contents));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@RequestHeader("Authorization") String header, @PathVariable Long menuId, @PathVariable Long id) {
        reviewService.delete(header, menuId, id);
        return new ResponseEntity<>("review deleting success", HttpStatus.OK);
    }

}
