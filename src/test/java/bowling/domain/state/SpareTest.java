package bowling.domain.state;

import bowling.domain.score.CalculableScore;
import bowling.domain.score.InProgressScore;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static bowling.domain.Fixture.DOWNED_PINS_5;
import static bowling.domain.Fixture.DOWNED_PINS_7;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("두 번 투구하여 핀이 모두 쓰러진 상태에 대한 테스트")
class SpareTest {

    @DisplayName("Spare 상태는 첫번째 투구 정보를 가지고 초기화 한다")
    @Test
    void init() {
        assertThat(Spare.from(DOWNED_PINS_5)).isInstanceOf(Spare.class);
    }

    @DisplayName("Spare 상태는 Clean 상태다")
    @Test
    void isClean() {
        Spare spare = Spare.from(DOWNED_PINS_5);

        assertThat(spare.isClean()).isTrue();
    }

    @DisplayName("Spare 상태에 스코어를 더하면 상태에 따라 두 번 더한 스코어를 반환한다")
    @MethodSource
    @ParameterizedTest
    void addScore(Score score, Score expectedScore) {
        Spare spare = Spare.from(DOWNED_PINS_7);

        assertThat(spare.addScore(score)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> addScore() {
        return Stream.of(
                Arguments.of(InProgressScore.ofStrike(), CalculableScore.from(20)),
                Arguments.of(InProgressScore.ofSpare(), CalculableScore.from(17)),
                Arguments.of(InProgressScore.of(5, 1), CalculableScore.from(12)),
                Arguments.of(InProgressScore.of(5, 2), CalculableScore.from(15))
        );
    }

}
