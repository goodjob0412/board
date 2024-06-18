package com.btistudy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;


@Getter
@ToString(callSuper = true)
//@Entity
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
public class Ex08_6_ArticleComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유번호

    @Setter
    @ManyToOne(optional = false)
    private Article article; // 연관관계 매핑.

/* 추가 - 유저 정보(userId), 양방향 바인딩 */
    @Setter
    @ManyToOne(optional = false)
//    @JoinColumn(name = "userId")
    private UserAccount userAccount;



    @Setter
    @Column(nullable = false, length = 500)
    private String content; // 본문


//    // 메타데이터
//    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt; // 생성일시
//    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 생성자
//    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt; // 수정일시
//    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy; // 수정자


    protected Ex08_6_ArticleComment() {
    }


/* 변경 */
//    private ArticleComment(Article article, String content) {
    private Ex08_6_ArticleComment(Article article, UserAccount userAccount, String content) {
        this.article = article;
        this.userAccount = userAccount;
        this.content = content;
    }

/* 변경 */
//    public static ArticleComment of(Article article, String content) {
    public static Ex08_6_ArticleComment of(Article article, UserAccount userAccount, String content) {
        return new Ex08_6_ArticleComment(article, userAccount, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ex08_6_ArticleComment that = (Ex08_6_ArticleComment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
