package me.study.testcodewitharchitecture.user.service;

import me.study.testcodewitharchitecture.mock.FakeMailSender;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CertificationServiceTest {

    @Test
    void 이메일과_컨텐츠가_제대로_만들어져서_보내지는지_테스트() {
        // given
        FakeMailSender mailSender = new FakeMailSender();
        CertificationService certificationService = new CertificationService(mailSender);

        // when
        certificationService.send("abc@example.com", 1, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");

        // then
        assertThat(mailSender.email).isEqualTo("abc@example.com");
        assertThat(mailSender.title).isEqualTo("Please certify your email address");
        assertThat(mailSender.content).isEqualTo(
                "Please click the following link to certify your email address: " +
                        "http://localhost:8080/api/users/1/verify?certificationCode=aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab"
        );
    }
}
