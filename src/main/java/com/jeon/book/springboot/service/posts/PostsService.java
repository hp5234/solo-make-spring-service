package com.jeon.book.springboot.service.posts;

import com.jeon.book.springboot.domain.posts.Posts;
import com.jeon.book.springboot.domain.posts.PostsRepository;
import com.jeon.book.springboot.web.dto.PostsListResponseDto;
import com.jeon.book.springboot.web.dto.PostsResponseDto;
import com.jeon.book.springboot.web.dto.PostsSaveRequestDto;
import com.jeon.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
    update 에는 쿼리를 날리는 부분이 보이지 않는다.
    JPA 의 영속성 컨텍스트로 인함 : 엔티티를 영구저장하는 환경
    JPA 의 핵심내용은 엔티티가 영속성 컨텍스트에 포함되어있느냐로 갈린다.
    JPA 의 엔티티 매니저 가 활성화 된 상태로 : Spring Data Jpa 를 사용하면 기본 옵션
        트랜잭션 안에서 데이터베이스에서 데이터를 가져오면
        해당 데이터는 영속성 컨텍스트가 유지된 상태다.
    영속성 컨텍스트가 유지되는 상태에서 해당 데이터의 값을 변경하면 트랜젝션이 끝나는 시점에 변경분을 반영
    -> 이러한 개념을 더티체킹이라고 한다.
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션의 범위는 유지하되 조회 기능만 남겨두어 조회 속도 향상
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id= " + id));

        postsRepository.delete(posts);
    }
}
