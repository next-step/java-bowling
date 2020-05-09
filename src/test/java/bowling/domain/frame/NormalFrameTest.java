package bowling.domain.frame;

import bowling.domain.exception.InvalidThrowBallException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NormalFrameTest {
    @Test
    @DisplayName("1~9 프레임은 스트라이크 후에 투구하지 않는다.")
    public void getStrikeFrame() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(10);
        assertThat(normalFrame.isRollable()).isFalse();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9})
    @DisplayName("1~9 프레임은 스트라이크 외엔 2번째 투구를 한다.")
    public void getNotStrikeFrame(int pinCount) {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(pinCount);
        assertThat(normalFrame.isRollable()).isTrue();
    }

    @Test
    @DisplayName("세번째 투구 시도 불가 확인")
    public void throwBallThirdTimesNotRollable() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(2);
        normalFrame.rollingBall(2);
        assertThat(normalFrame.isRollable()).isFalse();
    }

    @Test
    @DisplayName("세번째 투구 시도 시 Exception 발생")
    public void throwBallThirdTimesException() {
        NormalFrame normalFrame = new NormalFrame();

        normalFrame.rollingBall(2);
        normalFrame.rollingBall(2);

        assertThatExceptionOfType(InvalidThrowBallException.class).isThrownBy(
                () -> normalFrame.rollingBall(2)
        );
    }
}
