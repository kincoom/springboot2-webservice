package com.kincoom.admin.web;

import com.kincoom.admin.web.dto.HelloResponseDto;
import com.kincoom.admin.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello" ;
    }

    /*
    @RequestParam
    외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
    name이란 이름으로 넘긴 파라미터를 메소드 파라미터 name에 저장
    */
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}
