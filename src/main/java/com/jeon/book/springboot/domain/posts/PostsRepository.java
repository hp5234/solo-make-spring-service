package com.jeon.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Repository 는 Entity 클래스없이 역할을 제대로 수행할 수 없으므로 목적상 둘은 밀접함으로 인해 함께 위치해야한다.

// extends JpaRepository<Entity 클래스 , PK 타입> : 기본적인 CRUD 메서드가 자동 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // SpringDataJpa 에서 제공하지 않는 메소드를 Query 를 통해 작성
    // 기본메서드만을 이용해 해결할 수 있으나 @Query 를 통해 구성하는것이 가독성면에서 유리하다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC ")
    List<Posts> findAllDesc();

    // 조회용 프레임 워크로 querydsl 찾아볼것
    // - 타입 안정성 보장
    // - 사용처 및 레퍼런스가 많다.
}
