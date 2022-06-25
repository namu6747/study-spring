package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// Controller 어노테이션이 붙으면 스프링 컨테이너가
// 객체를 생성해서 보관하며 관리한다.
// 이걸 스프링 컨테이너에서 스프링 빈이 관리된다고 표현한다.
@Controller
public class MemberController {

    private final MemberService memberService;

    // Autowired 하면 Spring Container에서 자동으로 가져와준다.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
