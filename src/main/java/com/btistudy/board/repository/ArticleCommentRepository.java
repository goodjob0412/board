package com.btistudy.board.repository;

import com.btistudy.board.domain.ArticleComment;
import com.btistudy.board.domain.QArticle;
import com.btistudy.board.domain.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>
        , QuerydslPredicateExecutor<ArticleComment>
        , QuerydslBinderCustomizer<QArticleComment> /* like 검색 */
{
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content, root.createdAt, root.createdBy);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
    
    /* 다 하면 
        1) 빌드 (Ctrl + F9)
        2) Hal 가서 확인해보기
            ex) http://localhost:8080/api/articles?hashtag=Yell
     */

}
