package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// Service 클래스는 비즈니스에 가까운 용어를 사용해야 한다.
// ctrl + shift + T : Test 생성

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        /* 같은 이름이 있는 중복 회원X
         Optional을 반환하는 건 안 이쁘다. 별로 권장하진 않음.
         ctrl + alt + V : 좌변에 추출
        Optional<Member> result = memberRepository.findByName(member.getName());

         Optional로 꺼내서 람다식 활용
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // 위와 달리 꺼내자마자 ifPresent
        // 근데 또 길게 쭉 나오니깐 메소드로 뽑는 게 좋다
        // ctrl + alt + M
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    // 이렇게 작성하고나서 테스트케이스로 또 옮겨볼 거다.
}
