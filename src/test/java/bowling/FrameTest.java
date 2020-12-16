package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * #### 프레임
 * * 1~9 프레임은 1..2 개의 투구를 가진다
 * * 마지막 프래임은 1..3 개의 투구를 가진다
 * * 각 프레임은 1부터 시작하는 자신의 프레임 넘버를 가진다
 * * 각 프레임은 프래임내 투구 결과에 따라 스트라이크/스페어/미스/거터의 상태를 가진다
 * * 1~9 프레임은 첫번째 투구의 쓰러뜨린 핀 수가 10인 경우 다음 프레임으로 전환한다
 * * 마지막 프레임은 아홉번째 프레임이 스트라이크 인 경우 한번 더 투구 할 수 있다
 * * 마지막 프레임은 첫번째 투구가 스트라이크 인 경우 두번 더 투구할 수 있다.
 * * 마지막 프레임은 더이상 투구할 수 없으면 종료한다.
 */
public class FrameTest {
    @DisplayName("볼을 던지면 상태를 알 수 있다")
    @Test
    void createAndThrowBall() {
        Frame frame = new Frame();
        frame.throwBall(10);
        assertThat(frame.getScoring()).isEqualTo(Scoring.STRIKE);
    }

    @DisplayName("스트라이크이면 두번째 투구를 할 수 없다")
    @Test
    void strikeAndSecondThrow() {
        Frame frame = new Frame();
        frame.throwBall(10);
        assertThatThrownBy(() -> frame.throwBall(10))
                .isInstanceOf(IllegalFallingPinsException.class);
    }

    @DisplayName("스트라이크가 아니면 두번째 투구가 가능하다")
    @Test
    void secondThrow() {
        Frame frame = new Frame();
        frame.throwBall(5);
        frame.throwBall(5);
        assertThat(frame.sumOfFallingPins()).isEqualTo(10);
    }

    private static class Frame {
        private BallThrow firstThrow;
        private BallThrow secondThrow;

        public void throwBall(int fallingPins) {
            if (firstThrow == null) {
                firstThrow = new BallThrow(fallingPins);
                return;
            }
            secondThrow = firstThrow.throwSecond(fallingPins);
        }

        public Scoring getScoring() {
            return Scoring.STRIKE;
        }
    }

    private enum Scoring {
        STRIKE
    }
}
