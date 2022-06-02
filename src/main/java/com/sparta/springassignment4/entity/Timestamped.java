package com.sparta.springassignment4.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // 상속했을 때, Column으로 인식하게됨
@EntityListeners(AuditingEntityListener.class)  // 생성/수정 시간을 자동으로 반영하도록 설정
public class Timestamped {

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
