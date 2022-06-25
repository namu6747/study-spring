package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 도메인으로 접속하면 HomeController와 연결됨
    // 얘가 없다면 도메인 접속시 static 패키지의 index.html이 열림
    @GetMapping("/")
    public String home(){
        return "home";
    }

}
