package com.btistudy.board.controller;

/*
뷰 엔드포인트
    /articles	                GET	게시판 페이지
    /articles/{article-id}	    GET	게시글(상세) 페이지
    /articles/search	        GET	게시글 검색 페이지
    /articles/search-hashtag	GET	게시판 해시태그 검색 전용 페이지
*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class Ex06_1_ArticleController {

}
