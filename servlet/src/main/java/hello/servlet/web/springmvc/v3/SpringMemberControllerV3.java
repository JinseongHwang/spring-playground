package hello.servlet.web.springmvc.v3;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form", method = GET)
    public String newForm() {
        return "new-form";
    }

    @RequestMapping(value = "/save", method = POST)
    public String save(
        @RequestParam("username") String username,
        @RequestParam("age") int age,
        Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @RequestMapping(method = GET)
    public String members(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
}
