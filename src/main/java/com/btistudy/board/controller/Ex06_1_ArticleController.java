package com.btistudy.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/*  엑셀 api 순서대로
     /articles	                GET	게시판 페이지
     /articles/{article-id}	    GET	게시글(상세)페이지
     /articles/search	        GET	게시판 검색 전용 페이지
     /articles/search-hashtag	GET	게시판 해시태그 검색 전용 페이지
 */

//@Controller
@RequestMapping("/articles")
public class Ex06_1_ArticleController {

    @GetMapping
    public String articles(ModelMap model) {
        /* Model은 인터페이스
           ModelMap은 클래스(구현체)  사실 둘이 다른건 없음. Model 써도 같은 결과임  */
        
        model.addAttribute("articles", List.of());
        return "Ex06_3_index";

    }
}
