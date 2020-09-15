package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ScoringHistoryTest {

    @DisplayName("한 프레임의 득점기록이 끝났는지 테스트(스트라이크 혹은 2번 모두 던진경우)")
    @ParameterizedTest
    @MethodSource("provideHistoriesAndResult")
    void isDone_test(ScoringHistory firstTry, BowlingScore secondRecord, boolean expected) {
        // given
        ScoringHistory scoringHistory = ScoringHistory.secondTry(firstTry, secondRecord);

        // when
        boolean result = scoringHistory.isDone();

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideHistoriesAndResult() {
        return Stream.of(
                Arguments.of(ScoringHistory.firstTry(new BowlingScore(10)), BowlingScore.emptyScore(), true),
                Arguments.of(ScoringHistory.firstTry(new BowlingScore(5)), new BowlingScore(4), true),
                Arguments.of(ScoringHistory.firstTry(new BowlingScore(3)), BowlingScore.emptyScore(), false)
        );
    }

    @DisplayName("총 핀의 갯수를 초과하는 값이 들어오는 경우 예외 테스트")
    @ParameterizedTest
    @MethodSource("provideHistories")
    void exceed_remain_pin_test(ScoringHistory scoringHistory, BowlingScore secondRecord) {
        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            ScoringHistory.secondTry(scoringHistory, secondRecord);
        });
    }

    private static Stream<Arguments> provideHistories () {
        return Stream.of(
                Arguments.of(ScoringHistory.firstTry(new BowlingScore(5)), new BowlingScore(6)),
                Arguments.of(ScoringHistory.firstTry(new BowlingScore(2)), new BowlingScore(10)),
                Arguments.of(ScoringHistory.firstTry(new BowlingScore(3)), new BowlingScore(8))
        );
    }
}
