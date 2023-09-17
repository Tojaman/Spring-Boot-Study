package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository : 스프링이 MemoryMemberRepository 인식하게 하기 위함
// (만약 @Repository를 안붙이면 순수 자바 클래스인 MemoryMemberRepository를 스프링이 인식할 수 없음)
//@Repository
public class MemoryMemberRepository  implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // sequence : 0, 1, 2 .. 등 key 값을 생성해줌

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id값 셋팅
        store.put(member.getId(), member); // store 객체에 id, name 값 저장
        return member;
    }

    // Optional은 null값도 반환할 수 있게 하게 위해 사용
    @Override
    public Optional<Member> findById(Long id) {

        return Optional.ofNullable(store.get(id)); // Optional.ofNullable() : Null일 경우를 대비
    }

    @Override
    public Optional<Member> findByName(String name) {
        // store 객체에 name 값이 하나라도 있을 경우 반환. 없으면 null 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store 객체의 값을 리스트 형태로 반환
    }

    public void  clearStore() {
        store.clear();
    }
}
