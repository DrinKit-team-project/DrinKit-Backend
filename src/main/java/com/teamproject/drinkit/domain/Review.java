package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.exception.AuthorizationException;
import com.teamproject.drinkit.support.BooleanToYNConverter;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "REVIEW")
public class Review extends BaseEntity {
    private static final Logger log = LoggerFactory.getLogger(Review.class);

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REVIEW_RATINGS", nullable=false)
    private double ratings;

    @Lob
    @Column(name = "REVIEW_CONTENTS")
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "menu_id"))
    private Menu menu;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean deleted;

    @Embedded
    private UploadFileResponse uploadFileResponse;

    public Review(){}

    public Review(Long id, double ratings, String contents, UploadFileResponse uploadFileResponse){
        this(id, ratings, contents);
        this.uploadFileResponse = uploadFileResponse;
    }

    public Review(Long id, double ratings, String contents){
        this.id = id;
        this.ratings = ratings;
        this.contents = contents;
        this.deleted = false;
    }

    public Review(double ratings, String contents){
        this.ratings = ratings;
        this.contents = contents;
        this.deleted = false;
    }

    public Review(double ratings, String contents, UploadFileResponse uploadFileResponse){
        this(ratings, contents);
        this.uploadFileResponse = uploadFileResponse;
    }

    public static Review from(ReviewDto reviewDto){
        log.debug("uploadFileResponse null check :{}" ,reviewDto.toString());
        return new Review(reviewDto.getRatings(), reviewDto.getContents(), reviewDto.getUploadFileResponse());
    }

    public void registerMenu(Menu menu) {
        this.menu = menu;
    }

    public void registerWriter(Account writer) {
        this.writer = writer;
    }

    public Review edit(Account logined, ReviewDto target){
        if(!isSameAccount(logined)){
            throw new AuthorizationException("로그인 유저와 글쓴이가 다릅니다.");
        }
        this.ratings = target.getRatings();
        this.contents = target.getContents();
        this.uploadFileResponse = target.getUploadFileResponse();
        return this;
    }

    public Review delete(Account logined){
        if(!isSameAccount(logined)){
            throw new AuthorizationException("로그인 유저와 글쓴이가 다릅니다.");
        }
        this.deleted = true;
        return this;
    }

    public static Logger getLog() {
        return log;
    }

    public Long getId() {
        return id;
    }

    public double getRatings() {
        return ratings;
    }

    public String getContents() {
        return contents;
    }

    public Account getWriter() {
        return writer;
    }

    public Menu getMenu() {
        return menu;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public UploadFileResponse getUploadFileResponse() {
        return uploadFileResponse;
    }

    private boolean isSameAccount(Account logined){
        return this.writer.equals(logined);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", ratings=" + ratings +
                ", contents='" + contents + '\'' +
                ", writer=" + writer +
                ", menu=" + menu +
                ", deleted=" + deleted +
                ", uploadFileResponse=" + uploadFileResponse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
