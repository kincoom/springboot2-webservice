package com.kincoom.admin.domain.posts;

import com.kincoom.admin.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //클래스 내 모든 필도의 Getter 메소드를 자동생성
@NoArgsConstructor  //기본 생성자 자동추가, public Posts(){} 와 같은 효과
@Entity //테이블과 링크될 클래스임을 나타냄, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭 ex) SalesManger.java -> sales_manager table
public class Posts extends BaseTimeEntity {
    @Id //해당 테이블의 PK 필드를 나타냅니다
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성규칙, GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨
    private Long id;

    @Column(length = 500, nullable = false) //문자열의 경우 VARCHAR(255)가 기본인데 사이즈를 늘리고 싶거나 타입을 TEXT로 변경하고 싶거나 등의 경우에 선언, 선언하지 않더라도 해당클래스의 필드는 모두 컬럼이 된다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    //해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
