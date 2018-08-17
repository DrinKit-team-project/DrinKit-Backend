package com.teamproject.drinkit.domain;

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

    public Tag() {}
    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(tagName, tag.tagName);
    }
    @Override
    public int hashCode() {

        return Objects.hash(id, tagName);
    }
}
