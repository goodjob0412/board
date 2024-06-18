package com.btistudy.board.dto;

import com.btistudy.board.domain.UserAccount;

import java.time.LocalDate;
import java.time.LocalDateTime;

/* 레코드: 데이터 전달을 목적으로 하는 객체를 더 빠르게 만드는 기능
          상속 할 수 없음
          파라미터에 명시한 필드는 final들임
          getter setter 롬복과 상관 없이 자동으로 만들어줌
*/
public record UserAccountDto(
        Long id,
        String userId,
        String userPassword,
        String email,
        String nickname,
        String memo,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {


    public static UserAccountDto of (Long id, String userId, String userPassword, String email, String nickname, String memo, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new UserAccountDto(id, userId, userPassword, email, nickname, memo, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
            entity.getId(),
            entity.getUserId(),
            entity.getUserPassword(),
            entity.getEmail(),
            entity.getNickname(),
            entity.getMemo(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getModifiedAt(),
            entity.getModifiedBy()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.of(
                userId,
                userPassword,
                email,
                nickname,
                memo
        );
    }
}
