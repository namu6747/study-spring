package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // 모델에 담으면 view에 렌더링할 때 넘겨주는거
    // 파라미터 정보 ctrl + p
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name); // 전자가 key, 후자가 name
        return "hello-template";
    }



    // 일반적으로 말하는 api 방식은 객체를 반환하는 거
    @GetMapping("hello-string")
    @ResponseBody // 응답 HTTP meesagebody에 직접 넣겠다
    // view 이런 게 없다. 문자가 그대로 내려간다.
    // html 태그같은 게 하나도 없다
    // viewRsolber를 거치지 않는다.
    //  ctrl + backspace 단어 지우기
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }



    @GetMapping("hello-api")
    @ResponseBody
    // JSON 형태로 전송
    // 객체를 반환하고 리스폰스바디
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // alt+ insert construct
    // 프로퍼티 접근 방식
    // 자바빈 방식
    static class Hello {
        private String name;

        public String getName() {
            return name;

        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

