package com.btistudy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString(callSuper = true)
@Entity
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
public class Article extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유번호


/* 추가 - 유저 정보(userId), 양방향 바인딩 */
    @Setter
    @ManyToOne(optional = false)
    private UserAccount userAccount;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 10000)
    private String content;

    @Setter
    private String hashtag;

/* 양방향 바인딩*/
//    @OrderBy("id")
    @OrderBy("createdAt")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<ArticleComment> articleComment = new LinkedHashSet<>();

    protected Article() {
    }

/* 추가 - 유저 정보(userId) */
//    private Article(String title, String content, String hashtag) {
    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }


/* 추가 - UserAccount */
    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Article(userAccount, title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id!=null && id.equals(article.id);
    }

    @Override
    public int hashCode() { // 동일성 비교
        return Objects.hashCode(id);
    }
}
