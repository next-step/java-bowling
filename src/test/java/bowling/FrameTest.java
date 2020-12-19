package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static bowling.Scoring.*;
import static org.assertj.core.api.Assertions.*;

/**
 * #### 프레임
 * [x] 1~9 프레임은 1..2 개의 투구를 가진다
 * [x] 각 프레임은 1부터 시작하는 자신의 프레임 넘버를 가진다
 * [x] 각 프레임은 프래임내 투구 결과에 따라 스트라이크/스페어/미스/거터의 상태를 가진다
 * [x] 1~9 프레임은 첫번째 투구의 쓰러뜨린 핀 수가 10인 경우 다음 프레임으로 전환한다
 */
public class FrameTest {

    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new NormalFrame();
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

    @DisplayName("프레임이 끝나지 않으면 반환할 상태가 없다")
    @Test
    void incomplete() {
        assertThat(frame.getScoring()).isEqualTo(Optional.empty());
        frame.throwBall(0);
        assertThat(frame.getScoring()).isEqualTo(Optional.empty());
    }

    @DisplayName("프레임이 끝나지 않으면 같은 프레임을 반환한다")
    @Test
    void nextFrameIsSame() {
        Frame nextFrame = frame.throwBall(0);
        assertThat(nextFrame).isEqualTo(frame);
    }

    @DisplayName("스트라이크이면 새로운 프레임을 반환한다")
    @Test
    void nextFrameIsNewWhenStrike() {
        Frame nextFrame = frame.throwBall(10);
        assertThat(nextFrame).isNotEqualTo(frame);
    }

    @DisplayName("프레임이 끝나면 새 프레임을 반환한다")
    @Test
    void nextFrameIsNew() {
        Frame nextFrame = frame.throwBall(5).throwBall(3);
        assertThat(nextFrame).isNotEqualTo(frame);
    }

    @DisplayName("각 프레임은 1부터 시작하는 자신의 프레임 넘버를 가진다")
    @Test
    void frameNumber() {
        assertThat(frame.getNumber()).isEqualTo(1);
        Frame nextFrame = frame.throwBall(10);
        assertThat(nextFrame.getNumber()).isEqualTo(2);
    }


}
