package com.btistudy.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEx02_3_AuditingFields is a Querydsl query type for Ex02_3_AuditingFields
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QEx02_3_AuditingFields extends EntityPathBase<Ex02_3_AuditingFields> {

    private static final long serialVersionUID = -1874202781L;

    public static final QEx02_3_AuditingFields ex02_3_AuditingFields = new QEx02_3_AuditingFields("ex02_3_AuditingFields");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath modifiedBy = createString("modifiedBy");

    public QEx02_3_AuditingFields(String variable) {
        super(Ex02_3_AuditingFields.class, forVariable(variable));
    }

    public QEx02_3_AuditingFields(Path<? extends Ex02_3_AuditingFields> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEx02_3_AuditingFields(PathMetadata metadata) {
        super(Ex02_3_AuditingFields.class, metadata);
    }

}

