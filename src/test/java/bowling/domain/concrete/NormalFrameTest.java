package bowling.domain.concrete;

import bowling.domain.engine.PitchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    @Test
    @DisplayName("첫 투구에서 모든 핀을 쓰러트리면 프레임을 종료한다.")
    void strike() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.throwBall(PitchResult.wrap(10));

        assertThat(normalFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("두 번째 투구까지 마쳤다면 프레임을 종료한다.")
    void missedOrSpare() {
        NormalFrame missedFrame = new NormalFrame();
        missedFrame.throwBall(PitchResult.wrap(7));
        missedFrame.throwBall(PitchResult.wrap(2));

        NormalFrame spareFrame = new NormalFrame();
        spareFrame.throwBall(PitchResult.wrap(8));
        spareFrame.throwBall(PitchResult.wrap(2));

        assertAll(
            () -> assertThat(missedFrame.isEnded()).isTrue(),
            () -> assertThat(spareFrame.isEnded()).isTrue()
        );
    }

    @Test
    @DisplayName("이미 종료된 프레임에서 공을 더 던질 수 없다.")
    void cannotThrowBallIfFrameAlreadyEnded() {
        NormalFrame strikeFrame = new NormalFrame();
        strikeFrame.throwBall(PitchResult.wrap(10));

        NormalFrame missedFrame = new NormalFrame();
        missedFrame.throwBall(PitchResult.wrap(7));
        missedFrame.throwBall(PitchResult.wrap(2));

        NormalFrame spareFrame = new NormalFrame();
        spareFrame.throwBall(PitchResult.wrap(8));
        spareFrame.throwBall(PitchResult.wrap(2));

        assertAll(
            () -> assertThatThrownBy(() -> strikeFrame.throwBall(PitchResult.wrap(0)))
                .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> missedFrame.throwBall(PitchResult.wrap(0)))
                .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> spareFrame.throwBall(PitchResult.wrap(0)))
                .isInstanceOf(IllegalStateException.class)
        );
    }
    
}
