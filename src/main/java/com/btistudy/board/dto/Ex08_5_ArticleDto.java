package com.btistudy.board.dto;

import com.btistudy.board.domain.Article;
import com.btistudy.board.domain.UserAccount;

import java.time.LocalDateTime;

/* 레코드란: JDK16 에서 새로 나온거
*           DTO 와 비슷하지만 DTO를 구현하면 getter, setter, equals, hashCode, toString 같은 
*           데이터 처리나 연산을 하기 위해 오버라이드 된 메소드를 반복해서 작성해야함.
*           
*           롬복을 이용해서 반복적인 코드를 줄였는데 근본적인 해결책은 아님

*           그래서 나온게 record 임.
*           특정 데이터와 관련있는 필드들만 묶어늫 자료구조임.
*           주의: record 는 entity로는 사용 불가
* 
*  사용법
*       레코드명(헤더), {바디} 의 구조를 가지는데 헤더에 나열되는 필드를 컴포넌트 라고 부름
*       레코드는 생성자, toString, equals, hashCode 를 선언하지 않아도 알아서 제공해준다.
*       단, getter 사용시 getName() 이런식으로 쓰지 말고 대놓고 필드명을 사용하면 된다.
*
*  */
public record Ex08_5_ArticleDto( // 우선 엔티티가 가지고 있는 모든 정보들을 dto도 가지고 있게 해서 나중에 필요할때 가공할거임
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static Ex08_5_ArticleDto of(Long id,
                                       UserAccountDto userAccountDto,
                                       String title,
                                       String content,
                                       String hashtag,
                                       LocalDateTime createdAt,
                                       String createdBy,
                                       LocalDateTime modifiedAt,
                                       String modifiedBy) {
        return new Ex08_5_ArticleDto(id, userAccountDto, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    
    /* entity를 매개변수로 입력하면 Dto로 변환해주는 메서드
        entity를 받아서 new 한 다음에 인스턴스에다가 entity.get~ 해가면서 맵핑시켜서 return 하는거
        맵퍼처럼 사용할 수 있게 만든거임.
     */
    public static Ex08_5_ArticleDto from(Article entity) {
        return new Ex08_5_ArticleDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Article toEntity() {
        return Article.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }
}


















