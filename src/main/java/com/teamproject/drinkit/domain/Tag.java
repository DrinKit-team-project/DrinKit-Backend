package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Embeddable
@Entity
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String tagName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tagList")
    private List<Menu> menus = new ArrayList<>();

    private int searchCount = 0;

    public Tag() {}
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public void plusSearchCount() {
        this.searchCount++;
    }

    public Long getId() {
        return id;
    }
    public String getTagName() {
        return tagName;
    }
    public int getSearchCount() {
        return searchCount;
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
