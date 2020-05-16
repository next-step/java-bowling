package bowling.domain.frame;

import bowling.domain.exception.InvalidThrowBallException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class FinalFrameTest {
    @Test
    @DisplayName("스트라이크 후에 보너스 투구")
    public void getStrikeFrame() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(10);
        assertThat(finalFrame.isRollable()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9})
    @DisplayName("스트라이크 외엔 2번째 투구를 한다.")
    public void getNotStrikeFrame(int pinCount) {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(pinCount);
        assertThat(finalFrame.isRollable()).isTrue();
    }

    @Test
    @DisplayName("스페어 후 보너스 투구")
    public void throwBallThirdTimesRollingPossible() {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(2);
        finalFrame.rollingBall(8);
        assertThat(finalFrame.isRollable()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 7})
    @DisplayName("보너스 투구가 아닌 세번째 투구 시도 시 Exception 발생")
    public void throwBallThirdTimesException(int pinCount) {
        FinalFrame finalFrame = new FinalFrame();

        finalFrame.rollingBall(2);
        finalFrame.rollingBall(pinCount);

        assertThatExceptionOfType(InvalidThrowBallException.class).isThrownBy(
                () -> finalFrame.rollingBall(2)
        );
    }
}
