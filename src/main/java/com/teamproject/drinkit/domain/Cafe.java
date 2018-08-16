package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamproject.drinkit.dto.CafeDto;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Cafe extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    private String imageURL;

    @JsonIgnore
    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Menu> menus = new ArrayList<>();

    @Embedded
    @Column
    private List<String> categoryList = new ArrayList<>();

    private boolean deleted = false;

    public Cafe() {}
    public Cafe(String name) {
        this.name = name;
    }

    public void registerImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void addCategory(String expectedCategoryName) {
        this.categoryList.add(expectedCategoryName);
    }

    public CafeDto makeToDto() {
        CafeDto cafeDto = new CafeDto(this.name, this.imageURL);
        for (String category : categoryList) {
            cafeDto.addCategoty(category);
        }
        return cafeDto;
    }
}
