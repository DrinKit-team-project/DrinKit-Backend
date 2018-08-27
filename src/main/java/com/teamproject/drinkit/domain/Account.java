package com.teamproject.drinkit.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "ACCOUNT")
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="ACCOUNT_USERNAME")
    private String username;

    @Column(name = "ACCOUNT_LOGINID")
    private String userId;

    @Column(name = "ACCOUNT_PASSWORD")
    private String password;

    @Column(name = "ACCOUNT_USER_ROLE")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(name = "ACCOUNT_SOCIAL_ID")
    private String socialId;

    @Column(name = "ACCOUNT_SOCIAL_PROVIDER")
    @Enumerated(value = EnumType.STRING)
    private SocialProviders socialProvider;

    @Column(name = "ACCOUNT_SOCIAL_PROFILEPIC")
    private String profileHref;

    @Column(name = "ACCOUNT_REVIEWS")
    @OneToMany(mappedBy = "account")
    private List<Review> reviews = new ArrayList<>();

    private Account(Long id, String username, String userId, String password, UserRole userRole, String socialId, SocialProviders socialProvider, String profileHref, List<Review> reviews){
        this.id = id;
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.userRole = userRole;
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.profileHref = profileHref;
        this.reviews = reviews;
    }

    public Account(String username, String userId, String password, UserRole userRole, String socialId, SocialProviders socialProvider, String profileHref, List<Review> reviews){
        this(0L, username, userId, password, userRole, socialId, socialProvider, profileHref, reviews);
    }

    public void writeReview(Review review){
        reviews.add(review);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
