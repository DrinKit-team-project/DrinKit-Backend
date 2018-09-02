package com.teamproject.drinkit.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "FILE")
public class MyFile extends BaseEntity {

    @Id @GeneratedValue
    @Column(name ="FILE_ID")
    private Long id;

    @Column(name ="FILE_ORIGINAL_NAME")
    private String originalName;
    @Column(name = "FILE_RANDOMLY_GENERATED_NAME")
    private String randomlyGeneratedName;

    public MyFile(Long id, String originalName){
        this.id = id;
        this.originalName = originalName;
        this.randomlyGeneratedName = originalName + UUID.randomUUID();
    }

    public MyFile(String originalName){
       this(0L, originalName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MyFile myFile = (MyFile) o;
        return Objects.equals(id, myFile.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id);
    }
}
