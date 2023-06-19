package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
//    private final MemberService memberService = new MemberService();
    //이렇게 new로 만들면 다른 controller들이 가져다 쓸 수 있음
    //기능 자체도 많지 않아서 하나만 만들어 놓고 공용으로 사용하면 된다 => 굳이 여러개의 객체를 만들 필요가 없음

    private final MemberService memberService;

    // 컨트롤러에 한번만 등록하면 쓸 수 있다.
    @Autowired // autowired를 사용하고 이때, 생성자의 파라미터로 memberService를 넣어주는데, 이게 의존성주입이다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService; //생성자를 통해서 memberService가 memberController에 주입된다.
    }

    @GetMapping("/members/new") //url에 직접 치는 방식 => get방식
    public String createForm() {
        return "members/createMemberForm"; //그냥 creatememberfrom으로 넘어감
        // template에 있는 members/createMemberForm으로 맵핑된다.
        //return 하게 되면 template에서 찾는다.
        // viewResolver로 인해서 members/creatememberForm이 선택이되고 ,thymeleaf템플릿 엔진이 얘를 렌더링해준다.
    }
    @PostMapping("/members/new") //tmplates => createMemberForm에 memberResolver로 들어가게 된다.
    //date form에 넣어서 저장할때
    public String create(MemberForm form) { //form을 만든다.
        Member member = new Member();
        member.setName(form.getName()); //form에서 getName으로 꺼내옴

        memberService.join(member);

        return "redirect:/";
    }
    @GetMapping("/members/list")

    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList"
    }
}
