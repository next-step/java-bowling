package bowling.model.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("기다리고 있는 투구 개수 테스트")
public class WaitingPitchingCountTest {

    @DisplayName("기다리고 있는 투구 개수가 0개 미만 2개 초과면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(ints = {-1, 3})
    void outOfRangeWaitingPitchingCountExceptionTest(int count) {
        // given, when, then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WaitingPitchingCount(count))
                .withMessage("기다리고 있는 투구 개수는 0개 이상 2개 이하이어야 합니다.");
    }

    @DisplayName("스트라이크는 2개의 다음 투구를 기다려야 한다.")
    @Test
    void waitingPitchingCountOfStrikeTest() {
        // given, when, then
        assertSame(WaitingPitchingCount.ofStrike().count(), 2);
    }

    @DisplayName("스페어는 1개의 다음 투구를 기다려야 한다.")
    @Test
    void waitingPitchingCountOfSpareTest() {
        // given, when, then
        assertSame(WaitingPitchingCount.ofSpare().count(), 1);
    }

    @DisplayName("스트라이크, 스페어가 아니면 다음 투구를 기다리지 않는다.")
    @Test
    void waitingPitchingCountOfNotStrikeAndNotSpareTest() {
        // given, when, then
        assertSame(WaitingPitchingCount.noCount().count(), 0);
    }
}
