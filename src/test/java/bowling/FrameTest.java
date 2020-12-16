package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static bowling.BallThrow.MAX_PINS;
import static bowling.BallThrow.MIN_PINS;
import static bowling.FrameTest.Scoring.*;
import static org.assertj.core.api.Assertions.*;

/**
 * #### 프레임
 * [x] 1~9 프레임은 1..2 개의 투구를 가진다
 * * 마지막 프래임은 1..3 개의 투구를 가진다
 * * 각 프레임은 1부터 시작하는 자신의 프레임 넘버를 가진다
 * [x] 각 프레임은 프래임내 투구 결과에 따라 스트라이크/스페어/미스/거터의 상태를 가진다
 * * 1~9 프레임은 첫번째 투구의 쓰러뜨린 핀 수가 10인 경우 다음 프레임으로 전환한다
 * * 마지막 프레임은 아홉번째 프레임이 스트라이크 인 경우 한번 더 투구 할 수 있다
 * * 마지막 프레임은 첫번째 투구가 스트라이크 인 경우 두번 더 투구할 수 있다.
 * * 마지막 프레임은 더이상 투구할 수 없으면 종료한다.
 */
public class FrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new Frame();
    }

    @DisplayName("스트라이크이면 두번째 투구를 할 수 없다")
    @Test
    void strikeAndSecondThrow() {
        frame.throwBall(10);
        assertThatThrownBy(() -> frame.throwBall(10))
                .isInstanceOf(IllegalFallingPinsException.class);
    }

    @DisplayName("스트라이크가 아니면 두번째 투구가 가능하다")
    @Test
    void secondThrow() {
        frame.throwBall(5);
        frame.throwBall(5);
        assertThat(frame.sumOfFallingPins()).isEqualTo(10);
    }

    @DisplayName("10개를 쓰러뜨리면 스트라이크")
    @Test
    void strike() {
        frame.throwBall(10);
        assertThat(frame.getScoring()).isEqualTo(Optional.of(STRIKE));
    }

    @DisplayName("9/1 개를 쓰러뜨리면 스페어")
    @Test
    void spare() {
        frame.throwBall(9);
        frame.throwBall(1);
        assertThat(frame.getScoring()).isEqualTo(Optional.of(SPARE));
    }

    @DisplayName("9/0 개를 쓰러뜨리면 미스")
    @Test
    void miss() {
        frame.throwBall(9);
        frame.throwBall(0);
        assertThat(frame.getScoring()).isEqualTo(Optional.of(MISS));
    }

    @DisplayName("아무것도 쓰러뜨리지 못하면 거터")
    @Test
    void gutter() {
        frame.throwBall(0);
        frame.throwBall(0);
        assertThat(frame.getScoring()).isEqualTo(Optional.of(GUTTER));
    }

    @DisplayName("아직 프레임이 끝나지 않은 경우")
    @Test
    void incomplete() {
        assertThat(frame.getScoring()).isEqualTo(Optional.empty());
        frame.throwBall(0);
        assertThat(frame.getScoring()).isEqualTo(Optional.empty());
    }

    @DisplayName("프레임이 끝나지 않으면 같은 프레임을 반환한다")
    @Test
    void nextFrame() {
        Frame nextFrame = frame.throwBall(0);
        assertThat(nextFrame).isEqualTo(frame);
    }

    private static class Frame {
        private BallThrow firstThrow;
        private BallThrow secondThrow;

        public Frame throwBall(int fallingPins) {
            if (firstThrow == null) {
                firstThrow = new BallThrow(fallingPins);
                return this;
            }
            secondThrow = firstThrow.throwSecond(fallingPins);
            return null;
        }

        public Optional<Scoring> getScoring() {
            if (isIncomplete()) {
                return Optional.empty();
            }
            if (firstThrow.isStrike()) {
                return Optional.of(STRIKE);
            }
            if (sumOfFallingPins() == MAX_PINS) {
                return Optional.of(SPARE);
            }
            if (sumOfFallingPins() == MIN_PINS) {
                return Optional.of(GUTTER);
            }
            return Optional.of(MISS);
        }

        private boolean isIncomplete() {
            return firstThrow == null || !firstThrow.isStrike() && secondThrow == null;
        }

        public int sumOfFallingPins() {
            return Optional.ofNullable(firstThrow)
                    .map(ballThrow -> ballThrow.add(secondThrow))
                    .orElse(0);
        }
    }

    protected enum Scoring {
        STRIKE, MISS, GUTTER, SPARE
    }
}
