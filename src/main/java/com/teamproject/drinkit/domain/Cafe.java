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
//    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<String> categoryNames = new ArrayList<>();

    private boolean deleted = false;

    public Cafe() {}
    public Cafe(String name) {
        this.name = name;
    }

    public void registerImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void addCategoryName(String expectedCategoryName) {
        this.categoryNames.add(expectedCategoryName);
    }

    public Cafe addMenu(Menu menu) {
        this.menus.add(menu);
        return this;
    }
    public CafeDto makeToDto() {
        CafeDto cafeDto = new CafeDto(this.name, this.imageURL);
//        for (String category : categoryList) {
//            cafeDto.addCategoty(category);
//        }
        return cafeDto;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", menus=" + menus +
                ", deleted=" + deleted +
                '}';
    }
}
