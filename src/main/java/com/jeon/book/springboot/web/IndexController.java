package com.jeon.book.springboot.web;

import com.jeon.book.springboot.config.auth.dto.SessionUser;
import com.jeon.book.springboot.service.posts.PostsService;
import com.jeon.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());

        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index"; // 머스테치 스타터로 인해 문자열을 반환 시 앞의 경로와 파일 확장자는 자동으로 지정된다.
        // src/main/resources/templates/index.mustache 로 변환되어 ViewResolver 가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto responseDto = postsService.findById(id);
        model.addAttribute("post", responseDto);

        return "posts-update";
    }
}
