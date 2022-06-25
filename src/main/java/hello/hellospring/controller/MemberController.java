package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import hello.hellospring.domain.*;

import java.util.List;

// Controller 어노테이션이 붙으면 스프링 컨테이너가
// 객체를 생성해서 보관하며 관리한다.
// 이걸 스프링 컨테이너에서 스프링 빈이 관리된다고 표현한다.
// Ctrl + N : Navigate
@Controller
public class MemberController {

    // 필드 주입.
    // @Autowired private MemberService memberService;

    private MemberService memberService;


    // setter 주입
    // 하지만 public setter가 노출되어 있어서 권장하진 않음
    //    @Autowired
    //    public void setMemberService(MemberService memberService) {
    //        this.memberService = memberService;
    //    }

    // Autowired 하면 Spring Container에서 자동으로 가져와준다.

    // 컨트롤러의 어노테이션들은 살려두고 SpringConfig에 정의한 빈 들은
    // 따로 어노테이션을 달지 않아도 된다.
    // 생성자 주입 < 제일 선호하는 방식
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createFrom() {
        // templates 기준으로 경로 설정
        return "members/createMemberForm";
    }

    // 인수에 MemberForm을 줬는데 MemberForm에 name 키가 있으니
    // 이게 잘 동작됐던 것이다. 알아서 처리해줬다.
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        // 이번엔 모델 안에 리스트를 담았다.
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
