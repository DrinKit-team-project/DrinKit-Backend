package com.teamproject.drinkit.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.teamproject.drinkit.dto.ReviewDto;
import com.teamproject.drinkit.exception.AuthorizationException;
import com.teamproject.drinkit.security.AccountDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "ACCOUNT")
public class Account extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="ACCOUNT_USERNAME")
    @JsonProperty("nickname")
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

    @JsonIgnore
    @Column(name = "ACCOUNT_REVIEWS")
    @OneToMany(mappedBy = "writer")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "account_menu",
        joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private Set<Menu> favoriteMenus = new HashSet<>();

    public Account(Long id, String username, String userId, String password, UserRole userRole, String socialId, SocialProviders socialProvider, String profileHref){
        this.id = id;
        this.username = username;
        this.userId = userId;
        this.password = password;
        this.userRole = userRole;
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.profileHref = profileHref;
    }

    public Account(String username, String userId, String password, UserRole userRole, String socialId, SocialProviders socialProvider, String profileHref){
        this(0L, username, userId, password, userRole, socialId, socialProvider, profileHref);
    }

    public void writeReview(Review review){
        reviews.add(review);
        review.registerWriter(this);
    }

    public Account editNickname(Account logined, String newNickname){
        if(!isSameAccount(logined)){
            throw new AuthorizationException("로그인 유저와 글쓴이가 다릅니다.");
        }
        this.username = newNickname;
        return this;
    }

    public Account addFavoriteMenu(Menu favoriteMenu) {
        this.favoriteMenus.add(favoriteMenu);
        favoriteMenu.registerAccount(this);
        return this;
    }

    public Account removeFavoriteMenu(Menu favoriteMenu) {
        this.favoriteMenus.remove(favoriteMenu);
        favoriteMenu.removeAccount(this);
        return this;
    }

    private boolean isSameAccount(Account logined) {
        return this.equals(logined);
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
