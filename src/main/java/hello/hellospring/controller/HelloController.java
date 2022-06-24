package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
/*     웹앱에서 /hello라고 들어오면 이 메소드를 호출한다.
     아래의 Get은 HTTP Method의 GET
     Spring이 model 만들어서 넣어줌
     model에 "data"="hello!!" 를 넣은 거
     return의 hello는 resources/templates/hello.html
     스프링은 위 내용과 함께 hello를 찾아 렌더링 함
     컨트롤러에서 리턴 값으로 문자를 반환하면 viewResolver가
     화면을 찾아서 처리한다.
     스프링 부트 템플릿엔진은 기본적으로 viewName 매핑
     resources:templates/ + (viewName) + '.html'

     */
    @GetMapping("hello") 
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

}

