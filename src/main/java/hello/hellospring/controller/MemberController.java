package hello.hellospring.controller;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// @Controller : 스프링 빈에 등록
@Controller
public class MemberController {

    /**
     * 필드 주입
     */
//    @Autowired private MemberService memberService;


    /**
     * setter 주입
     */
//    private MemberService memberService;
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    /**
     * 생성자 주입
     */
    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}