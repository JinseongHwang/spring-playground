package me.study.javatest.study;

import lombok.RequiredArgsConstructor;
import me.study.javatest.domain.Study;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudyController {

    final StudyRepository studyRepository;

    @GetMapping("/study/{id}")
    public Study getStudy(@PathVariable Long id) {
        return studyRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Study not found for '" + id + "'"));
    }

    @PostMapping("/study")
    public Study createStudy(@RequestBody Study study) {
        return studyRepository.save(study);
    }
}
