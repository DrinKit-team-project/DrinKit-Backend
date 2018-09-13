package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teamproject.drinkit.dto.CafeDto;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "CAFE")
public class Cafe extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob
    private String imageURL;

    @JsonIgnore
    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Menu> menus = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "cafe_category_list",
            joinColumns = @JoinColumn(name = "cafe_id"))
    @Column(name = "CATEGORY_NAME")
    private List<String> categoryNames = new ArrayList<>();

    private boolean deleted = false;

    public Cafe() {}

    public Cafe(Long id, String name, String imageURL, String categories){
        this.id = id;
        this.name = name;
        this.imageURL = imageURL;
        this.categoryNames.addAll(Arrays.asList(split(categories)));
    }

    public Cafe(String name, String imageURL, String categories) {
        this(0L, name, imageURL, categories);
    }

    private String[] split(String categories) {
        return categories.split("/");
    }

    public Cafe addMenu(Menu menu) {
        this.menus.add(menu);
        return this;
    }

    public CafeDto makeToDto() {
        return new CafeDto(this.name, this.imageURL);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
