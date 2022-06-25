package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 다른데서 갖다 쓸 게 아니기 때문에 퍼블릭으로 안해도 된다.
// Test 순서는 보장되지 않는다.
// 순서랑 상관없이 동작되도록 설계해야된다는 말이다.
// 올바른 테스트를 위해선 필드 store를 매번 비워주는 게 맞다.
class MemoryMemberRepositoryTest {

    // 동일한 클래스를 만들고 객체를 가져와 테스트를 한 점 주목
    // 하지만 테스트 케이스를 먼저 작성한 뒤 구현 클래스를 작성할 수 있는데
    // 이것을 테스트 주도 개발(TDD) 라고 부른다. (순서 역전)

    MemberRepository repository = new MemoryMemberRepository();

    // 하나의 Test가 끝날 때마다 호출됨
    @AfterEach
    void afterEach(){
        repository.clearStore();
    }


    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        // Optional에서 get을 바로 꺼내는 방식은 좋은 건 아님. test라서 허용
        Member result = repository.findById(member.getId()).get();

        // import static 쓰자
        assertThat(member).isEqualTo((result));
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // 복붙 했을떄 shift + f6 누르면 글자 수정 가능
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }


}
