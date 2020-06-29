package bowling.domain;

import bowling.domain.exceptions.TooManyFrameResultsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameResultsTests {
    @DisplayName("FrameStatus 리스트를 전달받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        List<FrameResult> frameStatusList = Arrays.asList(FrameResult.ONE, FrameResult.TWO);
        assertThat(new FrameResults(frameStatusList)).isEqualTo(new FrameResults(frameStatusList));
    }

    @DisplayName("size가 4 이상인 리스트로는 생성할 수 없다.")
    @ParameterizedTest
    @MethodSource("biggerThanFourResource")
    void createValidationTest(List<FrameResult> biggerThanFour) {
        assertThatThrownBy(() -> new FrameResults(biggerThanFour))
                .isInstanceOf(TooManyFrameResultsException.class);
    }
    public static Stream<List<FrameResult>> biggerThanFourResource() {
        return Stream.of(
                Arrays.asList(FrameResult.ONE, FrameResult.ONE, FrameResult.ONE, FrameResult.ONE),
                Arrays.asList(FrameResult.ONE, FrameResult.ONE, FrameResult.ONE, FrameResult.ONE, FrameResult.ONE)
        );
    }

    @DisplayName("스페어가 없는 경우 점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("calculateWithoutSpareResource")
    void calculateWithoutSpareTest(FrameResults frameResults, int expectedResult) {
        assertThat(frameResults.calculateScore()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> calculateWithoutSpareResource() {
        return Stream.of(
                Arguments.of(new FrameResults(Collections.singletonList(FrameResult.STRIKE)), 10),
                Arguments.of(new FrameResults(Collections.singletonList(FrameResult.FIVE)), 5),
                Arguments.of(new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.TWO)), 7),
                Arguments.of(new FrameResults(
                        Arrays.asList(FrameResult.STRIKE, FrameResult.STRIKE, FrameResult.STRIKE)), 30),
                Arguments.of(new FrameResults(
                        Arrays.asList(FrameResult.STRIKE, FrameResult.FIVE, FrameResult.FOUR)), 19)
        );
    }

    @DisplayName("스페어가 있는 경우 점수를 계산할 수 있다.")
    @ParameterizedTest
    @MethodSource("calculateWithSpareResource")
    void calculateWithSpareTest(FrameResults frameResults, int expectedResult) {
        assertThat(frameResults.calculateScore()).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> calculateWithSpareResource() {
        return Stream.of(
                Arguments.of(new FrameResults(Arrays.asList(FrameResult.FIVE, FrameResult.SPARE)), 10),
                Arguments.of(new FrameResults(
                        Arrays.asList(FrameResult.STRIKE, FrameResult.FIVE, FrameResult.SPARE)), 20),
                Arguments.of(new FrameResults(
                        Arrays.asList(FrameResult.FIVE, FrameResult.SPARE, FrameResult.FOUR)), 14),
                Arguments.of(new FrameResults(
                        Arrays.asList(FrameResult.FIVE, FrameResult.SPARE, FrameResult.STRIKE)), 20)
        );
    }
}
