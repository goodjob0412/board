package com.btistudy.board.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

/*  할일: @Disabled 어노테이션 사용법 알아두기

        하기 전에 알아둘거: 테스트 코드 작성할건데 일단 돌리면 404 에러남
        이유는 Ex06_1_ArticleController 에 작성된 내용이 없고, dao 같은것도 없어서 그럼
        (뷰 파일 만들고 하면 됨)
 */

// WebMvcTest 이렇게만 쓰면 모든 컨트롤러들을 다 읽어들임.(지금은 하나밖에 없어서 상관없음)
// 그래서 필요한 컨트롤러만 가지고 올 수 있도록 () 안에 해당 컨트롤러 명시
@WebMvcTest(ArticleController.class)
@DisplayName("view 컨트롤러 테스트 - 게시글")
class Ex06_2_ArticleControllerTest {
    private final MockMvc mockMvc;

    Ex06_2_ArticleControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    /* 테스트 API 만들건데 엑셀 api 에 있는 순서대로 만들거임
        1) 게시판 페이지
        2) 게시글(상세) 페이지
        3) 게시글 검색 페이지
        4) 게시판 해시태그 검색 전용 페이지
    */

    @Test
    @DisplayName("[view][GET] 게시글 리시트 (게시판) 페이지 - 정상호출")
    public void articleAll() throws Exception {

    }

}



















