package org.zerock.j1.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass   // 이 클래스는 테이블로 만들지 않고, 다른 엔티티 클래스들이 공통적으로 상속받아 사용할 수 있는 공통 속성을 정의하는 추상 클래스입니다.
@Getter   // Lombok을 사용하여 Getter 메서드를 자동으로 생성합니다.
@EntityListeners(value = {AuditingEntityListener.class})   // JPA의 AuditingEntityListener를 사용하여 생성 및 수정 날짜를 자동으로 처리합니다.
public abstract class BaseEntity {   // BaseEntity 클래스는 추상 클래스로 정의됩니다. 다른 엔티티 클래스들이 이 클래스를 상속받아 사용합니다.

    @CreatedDate   // 생성일자를 나타내는 어노테이션입니다.
    @Column(updatable = false)   // 생성일자는 수정이 불가능합니다.
    private LocalDateTime regDate;   // 생성일자 정보를 저장하는 필드입니다.

    @LastModifiedDate   // 최종 수정일자를 나타내는 어노테이션입니다.
    private LocalDateTime modDate;   // 최종 수정일자 정보를 저장하는 필드입니다.

}
