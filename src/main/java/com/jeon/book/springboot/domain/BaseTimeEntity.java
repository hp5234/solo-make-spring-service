package com.jeon.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// JPA 에서 제공하는 JPA Auditing 을 통한 Entity 의 생성시간, 수정시간 관리
@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity 를 상속하는 경우 BaseTimeEntity 의 필드들도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다.
public class BaseTimeEntity { // 모든 Entity 클래스의 상위 클래스가 되어 createdDate, modifiedDate 를 자동으로 관리하는 역할을 한다.

    @CreatedDate // Entity 가 생성되어 저장될 대 시간이 자동으로 저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity 의 값을 변경할 때 시간이 자동으로 저장되도록 한다.
    private LocalDateTime modifiedDate;
}
