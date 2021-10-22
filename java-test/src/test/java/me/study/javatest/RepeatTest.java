package me.study.javatest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import me.study.javatest.domain.Study;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class RepeatTest {

    @DisplayName("반복하기")
    @RepeatedTest(value = 10, name = "{displayName} -> {currentRepetition}/{totalRepetitions}") // 10번 반복
    void repeat1(RepetitionInfo repetitionInfo) throws Exception {
        // given
        // 1/10 ~ 10/10
        System.out.println("RepeatedTest -> " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
        // when
        // then
    }

    @DisplayName("Param반복")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
    @EmptySource // 비어있는 parameter 추가
    @NullSource // null parameter 추가
    void repeat2(String message) throws Exception {
        // given
        System.out.println(message);
        // when
        // then
    }

    @DisplayName("Param반복")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void repeat3(@ConvertWith(StudyConverter.class) Study study) { // SimpleArgumentConverter 사용
        System.out.println(study.getLimit());
    }

    @DisplayName("Param반복")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void repeat4(Integer limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study);
    }

    @DisplayName("Param반복")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, '자바 스터디'", "20, 스프링"})
    void repeat5(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    // 클래스 타입을 매개변수로 1개만 받을 경우, 커스텀 컨버터의 구현이 필요하다.
    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

}
