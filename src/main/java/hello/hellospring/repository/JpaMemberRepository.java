package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // build.gradle에 data-jpa를 추가해줘서 스프링부트가 EntityManager가 자동으로 연결됨
    // 단 건에 저장 조회 업데이트는 쿼리문 짤 필요 없다.
    // PK 기반이 아닌 것들은 쿼리를 작성
    // jpa를 감싼 스프링 jpa를 사용하면 jpqa또한 작성할필요 없어짐
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // ctrl + alt + N : return과 동일할때 묶어줌
        // Member as m 에서 as가 생략
        // 보다시피 Member 객체 자체를 select하게 된다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public void clearStore() {

    }
}
