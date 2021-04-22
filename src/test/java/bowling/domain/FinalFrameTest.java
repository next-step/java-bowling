package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalFrameTest {

    @Test
    @DisplayName("첫 투구가 스트라이크라면 두 번 더 던질 수 있다.")
    void throwThreeTimes() {
        FinalFrame frame = new FinalFrame();

        frame.throwBall(PitchResult.wrap(10));

        assertThatCode(() -> {
            frame.throwBall(PitchResult.wrap(10));
            frame.throwBall(PitchResult.wrap(10));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("스페어 처리가 되었다면, 한 번 더 던질 수 있다.")
    void giveOneMoreChanceIfSpareCleared() {
        FinalFrame frame = new FinalFrame();

        frame.throwBall(PitchResult.wrap(0));
        frame.throwBall(PitchResult.wrap(10));

        assertThatCode(() -> frame.throwBall(PitchResult.wrap(5))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("스페어 처리에 실패하면 그대로 프레임을 종료한다.")
    void frameEndIfMissed() {
        FinalFrame frame = new FinalFrame();

        frame.throwBall(PitchResult.wrap(8));
        frame.throwBall(PitchResult.wrap(1));

        assertThat(frame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("이미 종료된 상태에서 공을 던지려고 시도하면 예외 처리한다.")
    void throwExceptionIfFrameAlreadyEnded() {
        FinalFrame strikeFrame = new FinalFrame();
        strikeFrame.throwBall(PitchResult.wrap(10));
        strikeFrame.throwBall(PitchResult.wrap(10));
        strikeFrame.throwBall(PitchResult.wrap(10));
        assertThat(strikeFrame.isEnded()).isTrue();

        FinalFrame spareFrame = new FinalFrame();
        spareFrame.throwBall(PitchResult.wrap(6));
        spareFrame.throwBall(PitchResult.wrap(4));
        spareFrame.throwBall(PitchResult.wrap(3));
        assertThat(spareFrame.isEnded()).isTrue();

        FinalFrame missedFrame = new FinalFrame();
        missedFrame.throwBall(PitchResult.wrap(7));
        missedFrame.throwBall(PitchResult.wrap(2));
        assertThat(missedFrame.isEnded()).isTrue();

        assertAll(
            () -> assertThatThrownBy(() -> strikeFrame.throwBall(PitchResult.wrap(10)))
                    .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> spareFrame.throwBall(PitchResult.wrap(10)))
                    .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> missedFrame.throwBall(PitchResult.wrap(10)))
                    .isInstanceOf(IllegalStateException.class)
        );
    }
    
}
