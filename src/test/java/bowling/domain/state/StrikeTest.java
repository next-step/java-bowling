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

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("첫번째 투구에서 모든 핀을 쓰러뜨린 상태 테스트")
class StrikeTest {

    @DisplayName("Strike 는 초기화시 어떤 정보도 필요 없다")
    @Test
    void init() {
        assertThat(Strike.init()).isInstanceOf(Strike.class);
    }

    @DisplayName("Strike 상태는 Clean 상태다")
    @Test
    void isClean() {
        Strike strike = Strike.init();

        assertThat(strike.isClean()).isTrue();
    }

    @DisplayName("Strike 상태에 score 를 더하면 기존 값에 +10 의 스코어를 반환한다")
    @MethodSource
    @ParameterizedTest
    void addScore(Score score, Score expectedScore) {
        Strike strike = Strike.init();

        assertThat(strike.addScore(score)).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> addScore() {
        return Stream.of(
                Arguments.of(InProgressScore.ofStrike(), InProgressScore.of(20, 1)),
                Arguments.of(InProgressScore.ofSpare(), CalculableScore.from(20)),
                Arguments.of(InProgressScore.of(5, 2), InProgressScore.of(15, 1))
        );
    }

}
