package com.btistudy.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "email"),
        @Index(columnList = "nickname"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
//@Entity
public class Ex08_2_UserAccount extends Ex02_3_AuditingFields {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유번호

    @Id
    @Setter @Column(nullable = false)
    private String userId;

    @Setter @Column(nullable = false)
    private String userPassword;


    @Setter @Column(length = 100)
    private String email;

    @Setter @Column(length = 100)
    private String nickname;

    @Setter
    private String memo;

    public Ex08_2_UserAccount() {}

    public Ex08_2_UserAccount(String userId, String userPassword, String email, String nickname, String memo) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
    }

    public static Ex08_2_UserAccount of(String userId, String userPassword, String email, String nickname, String memo) {
        return new Ex08_2_UserAccount(userId, userPassword, email, nickname, memo);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ex08_2_UserAccount that = (Ex08_2_UserAccount) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}


















