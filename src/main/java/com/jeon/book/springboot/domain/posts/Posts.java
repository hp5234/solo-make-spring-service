package com.jeon.book.springboot.domain.posts;

import com.jeon.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity // 테이블과 링크될 클래스임을 명시
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 의 생성규칙을 명시 : GenerationType.IDENTITY -> auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타냄 : 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 된다. 기본값 외의 값을 설정시 사용 : varchar 는 기본값이 255
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) // 타입 변경 TEXT
    private String content;

    private String author;

    @Builder // 빌드 패턴 클래스를 생성 : 생성자 위에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
    /*
      Setter 는 없다.
      값은 기본적으로 생성자를 통해 채워 DB 에 삽입하고
      값 변경이 필요한 경우 public 메서드를 호출하여 처리하는 방식을 사용
      생성자 대신 빌더를 사용하여 명확히 어느필드에 어떤 값을 채워야하는지 인지 가능
     */
}
