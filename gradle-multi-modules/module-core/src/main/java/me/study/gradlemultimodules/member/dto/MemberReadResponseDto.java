package me.study.gradlemultimodules.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberReadResponseDto {

    private String name;

    private String phoneNumber;

    private Double gpa;
}
