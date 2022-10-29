package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingTest {



    @Test
    void shouldReturnGameFinished() {
        Bowling bowling = new Bowling();
        playUntilFinish(bowling);
        assertThat(bowling.isFinish()).isTrue();
    }

    private void playUntilFinish(Bowling bowling) {
        IntStream.range(0, BowlingRound.LAST_ROUND_NUM)
                .forEach((idx) -> bowling.play(10));
        bowling.play(10);
        assertThat(bowling.isFinish()).isFalse();
        bowling.play(10);
    }

    @ParameterizedTest
    @DisplayName("쓰러트린 핀 개수 추가 및 현재 볼링 라운드 위치를 변경해야 합니다.")
    @MethodSource("generatedPinsAndExpectedRound")
    void testPlay(int startRoundNumber, int expectedRoundNumber, List<Integer> pins) {
        List<Score> scores = pins.stream()
                .map(Score::new)
                .collect(Collectors.toList());
        Bowling bowling = new Bowling(startRoundNumber);
        pins.forEach(bowling::play);
        BowlingRound currentRound = bowling.currentRound();

        assertThat(currentRound.isSameRound(new BowlingRound(expectedRoundNumber)));
        assertThat(currentRound.containsScore(scores));
    }

    static Stream<Arguments> generatedPinsAndExpectedRound() {
        return Stream.of(
                Arguments.of(1, 2, List.of(5, 5)),
                Arguments.of(3, 4, List.of(10)),
                Arguments.of(10, 10, List.of(10, 10, 10))
        );
    }

}
