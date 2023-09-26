package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service : 스프링 컨테이너에 등록
// (만약 @Service를 안붙이면 순수 자바 클래스인 MemberService를 스프링이 인식할 수 없음)
//@Service
public class MemberService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    Test 코드에서 DI(Dependency Injection)가 가능하게 하기 위에 위 코드를 아래 코드로 변경한다.
    private final MemberRepository memberRepository;
//    @Autowired -> 의존성 주입(DI) : MemberService 생성될 때 스프링 빈에 등록되어 있는 MemberRepository(실제 구현체 MemoryMemberRepository) 객체를 주입
//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        long start = System.currentTimeMillis();

        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
        // 같은 이름의 중복 회원X
        // findByName의 return 값은 Optional 값이므로 Optional 메소드인 ifPresent() 바로 사용 가능
//        validateDuplicateMember(member);
//        memberRepository.save(member);
//        return member.getId();
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
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }

    }

    /**
     * 특정 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
