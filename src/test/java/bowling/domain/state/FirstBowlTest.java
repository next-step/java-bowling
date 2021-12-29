package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.PinsTest;
import bowling.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FirstBowlTest {

    private static Stream<Arguments> provideFirstPinsSecondPinsAndResultState() {
        return Stream.of(
                arguments(Pins.create(3), Pins.create(1), Miss.class),
                arguments(Pins.create(3), Pins.create(7), Spare.class)
        );
    }

    private ThrowingState firstBowlWithFiveScore;

    @BeforeEach
    void beforeEach() {
        firstBowlWithFiveScore = FirstBowl.create(PinsTest.FIVE);
    }

    @DisplayName("첫 투구 이후, 남은 핀들을 넘어선 핀들을 투구한 경우 예외")
    @Test
    void bowlFailed() {
        // when & then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> firstBowlWithFiveScore.bowl(PinsTest.TEN));
    }

    @DisplayName("한 프레임에서 볼링 투구이후 상태 확인")
    @ParameterizedTest
    @MethodSource("provideFirstPinsSecondPinsAndResultState")
    void bowl(Pins first, Pins second, Class<ThrowingState> expectedResultState) {
        // given
        ThrowingState firstBowl = FirstBowl.create(first);
        // when & then
        assertThat(firstBowl.bowl(second)).isInstanceOf(expectedResultState);
    }

    @DisplayName("한 프레임 종료된 상태 확인")
    @Test
    void isEnd() {
        // when & then
        assertThat(firstBowlWithFiveScore.isEnd()).isFalse();
    }

    @DisplayName("현재 상태의 symbol 검증")
    @Test
    void symbol() {
        // when & then
        assertThat(firstBowlWithFiveScore.symbol()).isEqualTo(String.valueOf(5));
    }

    @DisplayName("현재 Score 반환 확인")
    @Test
    void score() {
        // when & then
        assertThat(firstBowlWithFiveScore.score()).isEqualTo(Score.withNonRemainingPitches(5));
    }

    @DisplayName("1 Strike 1 pitching 이후, Score 계산 반환 확인")
    @Test
    void scoreAfter() {
        // given
        Score strike = Score.of(10, 2);
        // when & then
        assertThat(firstBowlWithFiveScore.scoreAfter(strike)).isEqualTo(Score.of(15, 1));
    }
}
