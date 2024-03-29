package me.study.javatest.study;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Optional;
import me.study.javatest.domain.Member;
import me.study.javatest.domain.Study;
import me.study.javatest.domain.StudyStatus;
import me.study.javatest.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService() throws Exception {
        // given
        StudyService studyService = new StudyService(memberService, studyRepository);
        final Member member = Member.builder()
            .id(1L)
            .email("example@email.com")
            .build();

        // when
        when(memberService.findById(anyLong())).thenReturn(Optional.of(member));

        doThrow(new IllegalArgumentException())
            .when(memberService).validate(1L);

        when(memberService.findByEmail(anyString()))
            .thenReturn(Optional.of(member))
            .thenThrow(new RuntimeException())
            .thenReturn(Optional.empty());

        // then
        assertNotNull(studyService);
        assertEquals(member.getEmail(), memberService.findById(1L).get().getEmail());
        assertEquals(member.getEmail(), memberService.findById(2L).get().getEmail());
        assertEquals(member.getEmail(), memberService.findById(3L).get().getEmail());

        // when 절에서 anyLong()으로 ArgumentMatchers 를 설정했기 때문에, 뭘 넣어도 member와 같은 객체가 반환된다.
        assertEquals(member.getId(), memberService.findById(1L).get().getId());
        assertEquals(member.getId(), memberService.findById(2L).get().getId());
        assertEquals(member.getId(), memberService.findById(3L).get().getId());

        assertThrows(IllegalArgumentException.class, () -> memberService.validate(1L));

        assertEquals(member.getEmail(), memberService.findByEmail("AAAA@example.com").get().getEmail());
        assertThrows(RuntimeException.class, () -> memberService.findByEmail("BBBB@example.com"));
        assertEquals(Optional.empty(), memberService.findByEmail("CCCC@example.com"));
    }

    @Test
    void stubbingTest() throws Exception {
        // given
        StudyService studyService = new StudyService(memberService, studyRepository);
        final Study study = new Study(10, "테스트");
        final Member member = new Member(1L, "jinseong@example.com");

        // when
        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);

        // then
        assertNotNull(study.getOwnerId());
        assertEquals(member.getId(), study.getOwnerId());
    }

    @Test
    void createNewStudy() throws Exception {
        // given
        final StudyService studyService = new StudyService(memberService, studyRepository);
        final Member member = new Member(1L, "example@email.com");
        final Study study = new Study(10, "테스트");

//        when(memberService.findById(1L)).thenReturn(Optional.of(member));
//        when(studyRepository.save(study)).thenReturn(study);

        // (change "when" to "given")
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        // when
        studyService.createNewStudy(1L, study);

        // then
        assertNotNull(studyService);
        assertEquals(member.getId(), study.getOwnerId());

        // verify
//        verify(memberService, times(1)).notify(study); // memberService.notify(study) 가 1번 호출됨
//        verify(memberService, never()).notify(member); // memberService.notify(member) 가 호출되지 않음
//        verify(memberService, never()).validate(any()); // memberService.validate(any()) 가 호출되지 않음
//        verifyNoMoreInteractions(memberService); // memberService 에서 더 이상의 호출이 발생하지 않는다.

        // (change "verify" to "then")
        then(memberService).should(times(1)).notify(study);
        then(memberService).shouldHaveNoMoreInteractions();
    }

    @Test
    void verifyTest() throws Exception {
        // Given
        final StudyService studyService = new StudyService(memberService, studyRepository);
        final Study study = new Study(10, "더 자바, 테스트");
        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.openStudy(study);

        // Then
        assertEquals(StudyStatus.OPENED, study.getStatus());
        assertNotNull(study.getOpenedDateTime());
        then(memberService).should().notify(study);
    }
}