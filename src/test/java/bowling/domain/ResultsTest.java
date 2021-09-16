package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResultsTest {
    Results results;
    Frames frames;

    private static Stream<Arguments> addFrameAndLastResultTest() {
        return Stream.of(
            Arguments.of(NormalFrame.of(1, 2), "1|2"),
            Arguments.of(NormalFrame.of(0, 0), "-|-"),
            Arguments.of(FinalFrame.of(4, 6, 0), "4|/|-"),

            Arguments.of(NormalFrame.of(4, 6), "4|/"),
            Arguments.of(FinalFrame.of(4, 6, 10), "4|/|X"),
            Arguments.of(FinalFrame.of(10, 4, 6), "X|4|/"),

            Arguments.of(FinalFrame.of(10, 10, 0), "X|X|-"),
            Arguments.of(FinalFrame.of(10, 0, 10), "X|-|/"),

            Arguments.of(FinalFrame.of(10, 0, 0), "X|-|-"),
            Arguments.of(FinalFrame.of(0, 10, 0), "-|/|-"),
            Arguments.of(FinalFrame.of(0, 10, 6), "-|/|6"),
            Arguments.of(FinalFrame.of(10), "X"),
            Arguments.of(NormalFrame.of(10), "X"),
            Arguments.of(FinalFrame.of(10, 10), "X|X"),
            Arguments.of(FinalFrame.of(10, 10, 10), "X|X|X")
        );
    }

    @BeforeAll
    void setUp() {
        frames = Frames.from(Arrays.asList(1, 2, 10, 5, 5));
        results = Results.from(frames);
    }

    @DisplayName("점수를 의미하는 리스트 데이터를 , Results 객체를 생성한다")
    @Test
    void createTest() {
        Frames frames = Frames.from(Arrays.asList(1, 2, 10, 5, 5));
        assertThat(Results.from(frames)).isInstanceOf(Results.class);
    }

    @DisplayName("주어진 다양한 프레임 객체가 주어지면, lastResult메서드를 이용하여 문자열을 반환한다")
    @ParameterizedTest
    @MethodSource
    void addFrameAndLastResultTest(Frame frame, String expected) {
        results.addFrame(frame);
        assertThat(results.lastResult()).isEqualTo(expected);
    }
}
