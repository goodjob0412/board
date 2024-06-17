package com.btistudy.board.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*  할일 : @Disabled 어노테이션 사용법 알아두기
    하기 전에 알아둘거: 테스트 코드 작성할건데 일단 돌리면 404 에러남
    이유는 Ex06_1_ArticleController 에 작성된 내용이 없고, dao 같은 것도 없어서 그럼
    (뷰 파일 만들고 하면 됨)

 */
// WebMvcTest 이렇게만 쓰면 모든 컨트롤러들을 다 읽어들임.(지금은 하나밖에 없어서 상관없음)
// 그래서 필요한 컨트롤러만 가지고 올 수 있도록 () 안에 해당 컨트롤러 명시
@WebMvcTest(ArticleController.class)
@DisplayName("view 컨트롤러 테스트 - 게시글")
class Ex06_2_ArticleControllerTest {
    private final MockMvc mvc;

    Ex06_2_ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }
    /* 테스트 API 만들건데 엑셀 api에 있는 순서대로 만들거임
        1) 게시판 페이지
        2) 게시글(상세) 페이지
        3) 게시글 검색 페이지
        4) 게시판 해시태그 검색 전용 페이지
     */

//    @Disabled("구현 중")
    @Test
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상호출")
    public void articleAll() throws Exception {
        mvc.perform(get("/articles")) // /articles  가서 정보 얻어올건데
                .andExpect(status().isOk()) //  상태코드 200이 뜨면 정상이야 라는 뜻
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // 뷰 파일은 html로 만들기 때문에 데이터 타입이 html 임.
                                                // contentType 는 exact match 라서 MediaType 이 쯴짜 TEXT_HTML 일때만 OK 가 뜸
                                                // contentTypeCompatibleWith 는 호환되는 타입까지 OK
                .andExpect(view().name("articles/index")) // 현재 정보를 가지고 온 문서의 이름이 index 이고 articles 폴더 안에 있는거냐?
                .andExpect(model().attributeExists("articles")); // 해당 뷰 파일(index) 에서는 게시글들의 목록들이 쫙 떠야 하는데,
                                                                    // 그 말은 서버에서 게시글들 가져왔다는 뜻임. 그러면 모델로 데이터를 밀어넣어줬다는 뜻인데 
                                                                    // 그게 있는지 없는지 검사할 수 있음
                // 해석: model().attributeExists("articles") 에서 articles은 내가 임의로 준 키값. 맵에 articles 라는 키가 있는지 검사해라 라는 뜻
    }


    @Disabled("구현 중")
    @Test
    @DisplayName("[view][GET] 게시글(상세) 페이지 - 정상호출")
    public void articleOne() throws Exception {
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("articlesComments")); // 상세페이지에는 댓글들도 여러개 있을수 있으니 모델에 articlesComments 도 키값으로 있을거임
    }


    @Disabled("구현 중")
    @Test
    @DisplayName("[view][GET] 게시글 검색 페이지 - 정상호출")
    public void articleSearch() throws Exception {
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search")) ;
        // .andExpect(model().attributeExists("search")); // 이 테스트 에서는 검색 페이지만 뿌려주면 되는거라서 아직 데이터를 받아오지 않은 상태라서 필요 없음
    }


    @Disabled("구현 중")
    @Test
    @DisplayName("[view][GET] 게시판 해시태그 검색 전용 페이지 - 정상호출")
    public void articleSearchHashtag() throws Exception {
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search-hashtag")) ;
    }

}

/* 다 하면 resources > templates > articles 폴더 만들고 > index.html 파일 생성 */












