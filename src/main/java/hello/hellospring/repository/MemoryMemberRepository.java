package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    // 실무에서는 동시성 문제가 생길 수 있어서 공유되는 Map은 concurrentHashMap 을 사용해야함.
    private static Map<Long, Member> store = new HashMap<>();
    // Long도 원랜 어토믹같은 걸 사용
    private static long sequence = 0L;
    
    // null로 반환될 수 있다면 Optional 항상 고려하자.
    // stream도 필요시 사용하면 좋다
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
    // Map도 values 메소드가 value를 다 돌려준다
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
