package com.btistudy.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/*  엑셀 api 순서대로
     /articles	                GET	게시판 페이지
     /articles/{article-id}	    GET	게시글(상세)페이지
     /articles/search	        GET	게시판 검색 전용 페이지
     /articles/search-hashtag	GET	게시판 해시태그 검색 전용 페이지
 */

@Controller
@RequestMapping("/articles")
public class ArticleController {

    // 게시판 리스트 관련
    @GetMapping
    public String articles(ModelMap model) {
        /* Model은 인터페이스
           ModelMap은 클래스(구현체)  사실 둘이 다른건 없음. Model 써도 같은 결과임  */

        model.addAttribute("articles", List.of());
        return "/articles/index";

    }

    // 게시판 상세 페이지 관련
    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap model) {

        model.addAttribute("articles", "nullllll"); // 나중에 테스트 할때 null 대신 다른 문구 넣어야 함
        // 원래는 null 대신 Article 객체가 들어와야 하는데 도메인코드 Article을 노출시키진 않을거임. 나중에 DTO 만들어서 넣을거임
        // 만약 TDD 인 경우 null은 테스트 못함. 하려면 null 대신 아무 문자열 넣어야함

        model.addAttribute("articleComments", List.of());

        return "/articles/detail";

    }


}
