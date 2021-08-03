package bowling.domain.state;

import bowling.domain.pin.DownedPins;
import bowling.domain.score.CalculableScore;
import bowling.domain.score.InProgressScore;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.Fixture.DOWNED_PINS_2;
import static bowling.domain.Fixture.DOWNED_PINS_5;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("진행중 상태의 테스트")
class InProgressTest {

    @DisplayName("진행중 상태는 하나의 쓰러진 핀을 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(InProgress.from(DOWNED_PINS_5)).isInstanceOf(InProgress.class);
    }

    @DisplayName("진행중 상태에서 핀을 쓰러뜨리면 다음 상태를 반환한다")
    @MethodSource
    @ParameterizedTest
    void downPins(DownedPins downedPins, Class<State> expectedState) {
        InProgress inProgress = InProgress.from(DOWNED_PINS_5);

        assertThat(inProgress.downPins(downedPins)).isInstanceOf(expectedState);
    }

    private static Stream<Arguments> downPins() {
        return Stream.of(
                Arguments.of(DOWNED_PINS_5, Spare.class),
                Arguments.of(DOWNED_PINS_2, Miss.class)
        );
    }

    @DisplayName("InProgress 상태에 스코어를 더하면 한 번 더한 스코어를 반환한다")
    @MethodSource
    @ParameterizedTest
    void addScore(Score score, Score expectedScore) {
        InProgress inProgress = InProgress.from(DOWNED_PINS_5);

        assertThat(inProgress.addScore(score)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> addScore() {
        return Stream.of(
                Arguments.of(InProgressScore.ofStrike(), InProgressScore.of(15, 1)),
                Arguments.of(InProgressScore.ofSpare(), CalculableScore.from(15)),
                Arguments.of(InProgressScore.of(5, 1), CalculableScore.from(10)),
                Arguments.of(InProgressScore.of(5, 2), InProgressScore.of(10, 1))
        );
    }

}
