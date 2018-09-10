package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String tagName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tagList")
    @JsonIgnore
    private List<Menu> menus = new ArrayList<>();

    private int searchCount = 0;

    public Tag() {}
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public void plusSearchCount() {
        this.searchCount++;
    }

    public void registerMenu(Menu menu) {
        this.menus.add(menu);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", searchCount=" + searchCount +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return searchCount == tag.searchCount &&
                Objects.equals(id, tag.id) &&
                Objects.equals(tagName, tag.tagName) &&
                Objects.equals(menus, tag.menus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName, menus, searchCount);
    }
}
