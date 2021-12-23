package me.study.gradlemultimodules.member.service;

import me.study.gradlemultimodules.member.dto.MemberReadResponseDto;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    public MemberReadResponseDto readMember() {
        return MemberReadResponseDto.builder()
            .name("황진성")
            .phoneNumber("010-XXXX-XXXX")
            .gpa(4.5)
            .build();
    }
}
