package me.study.gradlemultimodules.api;

import lombok.RequiredArgsConstructor;
import me.study.gradlemultimodules.member.dto.MemberReadResponseDto;
import me.study.gradlemultimodules.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<MemberReadResponseDto> readMember() {
        return ResponseEntity.ok(memberService.readMember());
    }
}
