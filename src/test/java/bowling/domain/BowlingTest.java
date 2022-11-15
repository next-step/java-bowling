package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingTest {


    static Stream<Arguments> generatedPinsAndExpectedRound() {
        return Stream.of(
                Arguments.of(List.of(5, 4), List.of(0, 9)),
                Arguments.of(List.of(10), List.of(0)),
                Arguments.of(List.of(10, 10, 10), List.of(0, 0, 30))
        );
    }

    @Test
    void shouldReturnGameFinished() {
        Bowling bowling = new Bowling();
        playUntilFinish(bowling);
        assertThat(bowling.isFinish()).isTrue();
    }

    private void playUntilFinish(Bowling bowling) {
        Username username = new Username("kcs");
        IntStream.range(0, BowlingRound.LAST_ROUND_NUM)
                .forEach((idx) -> bowling.play(10, username));
        bowling.play(10, username);
        assertThat(bowling.isFinish()).isFalse();
        bowling.play(10, username);
    }

    @ParameterizedTest
    @DisplayName("계산 결과를 반환해야 합니다.")
    @MethodSource("generatedPinsAndExpectedRound")
    void testPlay(List<Integer> pins, List<Integer> expectedScoreResult) {
        Bowling bowling = new Bowling();

        IntStream.range(0, pins.size())
                .forEach((index) -> validateCalculateResult(pins, expectedScoreResult, bowling, index));
    }

    private void validateCalculateResult(List<Integer> pins, List<Integer> expectedScoreResult, Bowling bowling, int index) {
        ScoreResult scoreResult = bowling.play(pins.get(index), new Username("kcs"));
        Integer expectResult = expectedScoreResult.get(index);
        if (expectResult.equals(0)) {
            assertThat(scoreResult.getScores()).isEmpty();
            return;
        }
        assertThat(scoreResult.getScores()).containsExactly(expectResult);
    }

}
