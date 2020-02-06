package com.kincoom.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    //DB Layer 접근자 (Mybatis의 경우 Dao)
    //인터페이스 생성 후 JpaRepository<Entity 클래스, PK타입> 를 상속하면 기본적인 CRUD 메소드가 자동으로 생성
    //Entity 클래스와 기본 Entity Repository는 함께 위치

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
