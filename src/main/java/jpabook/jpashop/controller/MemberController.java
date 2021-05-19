package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        /**
         * MemberForm 에서 name 은 필수적으로 입력하도록 했고, validation 을 진행한다.
         * validation 에서 걸리게 된다면 BindingResult 에 에러가 담긴다.
         * 에러가 담기면 다시 createMemberForm.html 올 렌더링한다.
         * 그 때 매개변수로 받은 MemberForm 도 다시 되돌려주기 때문에,
         * JSP 의 history.back() 처럼 입력했던 값이 유지된 채로 돌아간다.
         * 또한 validation 할 때 작성했던 message 도 함께 렌더링해준다.
         */
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }
}
