package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class MemberServiceTest {

    MemberRepository mr;
    MemberService ms;

    @BeforeEach
    public void beforeEach(){
        // 외부에서 mr을 넣어줬다
        // 이런걸 DI(dependency injection)이라고 한다.
        mr = new MemoryMemberRepository();
        ms = new MemberService(mr);
    }


    @AfterEach
    private void afterEach(){
        mr.clearStore();;
    }

    // Test는 사실, 메소드명을 과감하게 한글로 바꿔도 된다.
    // build 과정에서 Test는 실제 코드에 포함되지 않는다.
    // Test는 정상 flow도 중요한데 예외 flow가 훨씬 더 중요하다.
    // shift + f10 : 이전에 실행했던 거 다시 실행
    @Test
    void 회원가입() {
        //given 이 데이터를 기반으로 하구나
        Member member = new Member();
        member.setName("spring");

        //when 이걸 검증하구나
        Long saveId = ms.join(member);

        //then 검증부구나
        Member findMember = ms.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // ctrl + alt + I : 자동 들여쓰기
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        ms.join(member1);
        // {1} 어떤 exception이 터져야 되고
        // {2} 어떤 로직에서 터져야되는 지
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> ms.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*        try {
            ms.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1212");
        }*/

        //then
    }

    @Test
    void 회원목록록() {
    }
    @Test
    void findOne() {
    }
}