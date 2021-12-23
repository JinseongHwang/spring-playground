package me.study.gradlemultimodules.member.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String phoneNumber;

    private Double gpa;

    @Builder
    public Member(String name, String phoneNumber, Double gpa) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gpa = gpa;
    }
}
