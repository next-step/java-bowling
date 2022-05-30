package bowling.domain;

import bowling.domain.state.*;
import bowling.exception.EndedFrameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameTest {
    private Frame frame;

    @BeforeEach
    void setUp() {
        frame = new Frame();
    }

    @Test
    void 프레임_시작시_준비상태() {
        assertTrue(new Frame().frameState() instanceof Ready);
    }

    @Test
    void 첫_프레임_거터() {
        frame.play(1, 0);
        assertTrue(frame.frameState() instanceof Gutter);
    }

    @Test
    void 첫_프레임_스트라이크() {
        frame.play(1, 10);
        assertTrue(frame.frameState() instanceof Strike);
    }

    @Test
    void 첫_프레임_보통() {
        frame.play(1, 5);
        assertTrue(frame.frameState() instanceof FirstBowl);
    }

    @Test
    void 두번째_프레임_스페어() {
        frame.play(1, 5);
        frame.play(1, 5);
        assertTrue(frame.frameState() instanceof Spare);
    }

    @Test
    void 두번째_프레임_미스() {
        frame.play(1, 0);
        frame.play(1, 0);
        assertTrue(frame.frameState() instanceof Miss);
    }

    @Test
    void 두번째_프레임_보통() {
        frame.play(1, 5);
        frame.play(1, 0);
        assertTrue(frame.frameState() instanceof SecondBowl);
    }

    @Test
    void 이미_종료_프레임_플레이_스트라이크() {
        frame.play(1, 10);
        assertThatThrownBy(() -> frame.play(1, 2))
                .isInstanceOf(EndedFrameException.class);
    }

    @Test
    void 이미_종료_프레임_플레이_스페어() {
        frame.play(1, 2);
        frame.play(1, 8);
        assertThatThrownBy(() -> frame.play(1, 2))
                .isInstanceOf(EndedFrameException.class);
    }

    @Test
    void 이미_종료_프레임_플레이_미스() {
        frame.play(1, 2);
        frame.play(1, 5);
        assertThatThrownBy(() -> frame.play(1, 2))
                .isInstanceOf(EndedFrameException.class);
    }
}