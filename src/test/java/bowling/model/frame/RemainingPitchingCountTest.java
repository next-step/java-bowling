package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("남은 투구 개수 테스트")
public class RemainingPitchingCountTest {

    @DisplayName("남은 투구 개수가 0개 미만 2개 초과면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    void outOfRangeRemainingPitchingCountExceptionTest(int remainingPitchingCount) {
        // given, when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new RemainingPitchingCount(remainingPitchingCount))
                .withMessage("남은 투구 개수는 0개 이상 2개 이하이어야 합니다.");
    }

    @DisplayName("스트라이크 점수 계산을 위해서는 2개의 다음 투구가 남아있다.")
    @Test
    void remainingPitchingCountOfStrikeTest() {
        // given, when, then
        assertSame(RemainingPitchingCount.ofStrike().count(), 2);
    }

    @DisplayName("스페어 점수 계산을 위해서는 1개의 다음 투구가 남아있다.")
    @Test
    void remainingPitchingCountOfSpareTest() {
        // given, when, then
        assertSame(RemainingPitchingCount.ofSpare().count(), 1);
    }

    @DisplayName("첫 번째 투구이고 스트라이크가 아니라면 점수 계산을 위해서는 1개의 다음 투구가 남아있다.")
    @Test
    void remainingPitchingCountOfFirstAndNotStrikeTest() {
        // given, when, then
        assertSame(RemainingPitchingCount.ofFirstAndNotStrike().count(), 1);
    }

    @DisplayName("두 번째 투구이고 스페어가 아니라면 남은 투구가 없다.")
    @Test
    void remainingPitchingCountOfSecondAndNotSpareTest() {
        // given, when, then
        assertSame(RemainingPitchingCount.ofSecondAndNotSpare().count(), 0);
    }
}
