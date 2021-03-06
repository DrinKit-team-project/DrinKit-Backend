package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public BaseEntity() {
        this.createdDate = LocalDateTime.now();
    }

    @JsonIgnore
    public String getFormattedCreatedDate(){
        return getFormattedDate(this.createdDate, "yyyy.MM.dd HH:mm:ss");
    }

    @JsonIgnore
    public String getFormattedModifiedDate(){
        return getFormattedDate(this.modifiedDate, "yyyy.MM.dd HH:mm:ss");
    }

    private String getFormattedDate(LocalDateTime dateTime, String format){
        if (dateTime == null){
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    @JsonIgnore
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(modifiedDate, that.modifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdDate, modifiedDate);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
