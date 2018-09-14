package com.teamproject.drinkit.controller;

import com.teamproject.drinkit.domain.UploadFileResponse;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.service.FileStorageService;
import com.teamproject.drinkit.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/menus/{menuId}/reviews")
public class ApiReviewController {
    private static final Logger log = LoggerFactory.getLogger(ApiReviewController.class);

    @Autowired
    private ReviewService reviewService;

    // TODO : 파일 한글이름 깨짐현상 해결하기

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping(value = "", produces = "application/json;charset=utf8")
    public ResponseEntity<ReviewDto> addReview(@RequestHeader("Authorization") String header, @PathVariable Long menuId, @RequestParam("ratings") double ratings,
                               @RequestParam("contents") String contents, @RequestParam(value = "file", required = false) MultipartFile file) {
        if(file != null) {
            UploadFileResponse uploadFileResponse = fileStorageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8).body(reviewService.addReview(header, menuId, new ReviewDto(ratings, contents, uploadFileResponse)));
        }
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8).body(reviewService.addReview(header, menuId, new ReviewDto(ratings, contents)));
    }

    @PostMapping(value = "/{id}", produces = "application/json;charset=utf8")
    public ResponseEntity<ReviewDto> editReview(@RequestHeader("Authorization") String header, @PathVariable Long menuId, @PathVariable Long id, @RequestParam("ratings") double ratings,
                                @RequestParam("contents") String contents, @RequestParam(value = "file", required = false) MultipartFile file) {
        if(file != null) {
            UploadFileResponse uploadFileResponse = fileStorageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(reviewService.edit(header, menuId, new ReviewDto(id, ratings, contents, uploadFileResponse)));
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(reviewService.edit(header, menuId, new ReviewDto(id, ratings, contents)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@RequestHeader("Authorization") String header, @PathVariable Long menuId, @PathVariable Long id) {
        reviewService.delete(header, menuId, id);
        return new ResponseEntity<>("review deleting success", HttpStatus.OK);
    }

}
