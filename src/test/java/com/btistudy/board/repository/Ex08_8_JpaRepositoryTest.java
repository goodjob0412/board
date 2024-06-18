package com.btistudy.board.repository;

import com.btistudy.board.config.Ex01_3_JpaConfig;
import com.btistudy.board.domain.Article;
import com.btistudy.board.domain.UserAccount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(Ex01_3_JpaConfig.class)
class Ex08_8_JpaRepositoryTest {

    // 생성자 주입
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;
/* 새로 삽입 */ private final UserAccountRepository userAccountRepository;

    public Ex08_8_JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository,
            @Autowired UserAccountRepository userAccountRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
        this.userAccountRepository = userAccountRepository;
    }



    /* select 테스트 */
    @DisplayName("select 테스트")
    @Test
    void selectTest() {
        // given

        // when
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles).isNotNull().hasSize(100);
    }

    /* insert 테스트 */
    @DisplayName("insert 테스트")
    @Test
    void insertTest() {
        // given
        // 기존 카운트 구하기
        long prevCount = articleRepository.count();

        // when
        // 삽입

/* 추가 */
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("new bitstudy", "1234", null, null, null));
/* 변경 */
        // Article article = Article.of( "제목1", "내용1", "Red");
        Article article = Article.of(userAccount, "제목1", "내용1", "Red");
        articleRepository.save(article);

        // then
        // 현재카운트가 기존 카운트 + 1 이면 테스트 통과
        assertThat(articleRepository.count()).isEqualTo(prevCount + 1);
    }

    /* update 테스트 */
    @DisplayName("update 테스트")
    @Test
    void updateTest() {
        /* 기존 데이터 있어야 하고, 그걸 수정했을때 확인 해야함
            1) 기존의 영속성 컨텍스트 한개 엔티티(객체) 가져오기
            2) 업데이트 - (해쉬태그 업데이트 해보기)
         */

        // given - 테스트를 위해 주어지는 상황
        /* 순서 - 1) 기존의 영속성 컨텍스트 한개 엔티티(객체) 가져오기
            1. 기존의 영속성 컨텍스트로부터 하나 엔티티를 가져올건데 -> articleRepository.findById()
            2. 글번호 1번은 보통 무조건 있으니까 -> findById(1L)
            3. 없으면 throw 시켜서 일단 현재 테스트는 끝나게 하기 -> .orElseThrow()
         */
            Article article = articleRepository.findById(1L).orElseThrow();

        /* 순서 - 2) 업데이트 - (해쉬태그 업데이트 해보기)
            1. 변수 updateHashtag 에 새로 바꿀 해시태그 문자열로 값 저장
            2. 엔티티(article)에 있는 setter 를 이용해서 변수 updateHashtag 에 있는 문자열로 업데이트 하기

         */
            String updateHashtag = "Blue"; // 변수에 업데이트할 문자열 넣고
            article.setHashtag(updateHashtag); // 엔티티에 있는 setter 를 이용해서 변수를 업데이트 하기

        // when - 테스트 해야하는 내용
        /*
            영속성 컨텍스트로부터 가져온 데이터를 그냥 save만 하고 아무것도 안하고 끝내버리면 어짜피 롤백됨.
            테스트를 돌리면 Run탭에 마지막 메세지는 select 구문이 나온다.
            그래서 save 하고 flush 해줘서 해당 필요한 구문까지만 나오게 하기

            * saveAndFlush: 바로 DB에 적용하는 방식

            Flush란 push 같은거
            - flush 동작 과정
                1. 변경점 감지
                2. 수정된 Entity(article) 를 지연 sql 저장소에 등록
                3. 쓰기 지연 sql 저장소의 쿼리를 DB에 전송 (등록, 수정, 삭제 쿼리)
         */
//        Article savedArticle = articleRepository.save(article);
        Article savedArticle = articleRepository.saveAndFlush(article);

        // then
        // savedArticle 이 "hashtag" 필드를 가지고 있는데, 그 필드에 updateHashtag 값이 있는지 확인해라
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHashtag);
    }

    /* delete 테스트 */
    @DisplayName("delete 테스트")
    @Test
    void deleteTest() {
        /* 기존에 데이터 있어야 되고
            값을 하나 꺼내서 지우기

            1) 기존의 영속성 컨테스트로부터 엔티티 꺼내오기
            2) 지우면 DB 개수 하나 줄어드는 거니까 미리 엔티티 개수(count) 구하기
            3) 하나 삭제
            4) 2번에서 구한 개수 - 1한거랑 새로 현재 구한 count랑 비교해서 같은면 통과
         */

        // given
        /* 1) 기존의 영속성 컨테스트로부터 엔티티 꺼내오기 */
        Article article = articleRepository.findById(1L).orElseThrow();

        /* 2) 지우면 DB 개수 하나 줄어드는 거니까 미리 엔티티 개수(count) 구하기
             게시글(articleRepository)뿐만 아니라 연관된 댓글(articleCommentRepository) 까지 삭제할거라서 두개 개수를 다 뽑아야함
        */
            long prevArticleCount = articleRepository.count();
            long prevArticleCommentCount =articleCommentRepository.count(); // 1000

            int deleteCommentSize = article.getArticleComment().size(); // 10

        // when
            articleRepository.delete(article);

        // then
        // 현재 게시글 개수 (articleRepository.count()) 가 아까 구한 prevArticleCount 보다 1 적으면 테스트 통과 라는 뜻
        assertThat(articleRepository.count()).isEqualTo(prevArticleCount - 1);

        // 현재 게시글의 대한 댓글 개수
        assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentCount - deleteCommentSize);

    }
}

















