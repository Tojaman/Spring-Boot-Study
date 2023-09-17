package hello.hellospring.controller;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 스프링 컨테이너에 등록
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
//    MemberService는 객체 하나만 생성하고 공용으로 사용하면 되므로 공용으로 하나만 생성해서 스프링 컨테이너에 등록한 후 사용한다.
//    private final MemberService memberService = new MemberService();
    private final MemberService memberService;

//    @Autowired -> 의존성 주입(DI) : MemberController가 생성될 때 스프링 빈에 등록되어 있는 MemberService 객체를 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원 등록
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // "redirect:/" : 웹 root 경로로 redirect하라는 뜻
        return "redirect:/";
    }

    // 회원 조회
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}