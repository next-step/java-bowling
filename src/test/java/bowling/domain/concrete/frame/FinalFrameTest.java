package bowling.domain.concrete.frame;

import bowling.domain.engine.roll.Roll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalFrameTest {

    @Test
    @DisplayName("첫 투구가 스트라이크라면 두 번 더 던질 수 있다.")
    void throwThreeTimes() {
        FinalFrame frame = FinalFrame.init();

        frame.roll(Roll.result(10));

        assertThatCode(() -> {
            frame.roll(Roll.result(10));
            frame.roll(Roll.result(10));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("스페어 처리가 되었다면, 한 번 더 던질 수 있다.")
    void giveOneMoreChanceIfSpareCleared() {
        FinalFrame frame = FinalFrame.init();

        frame.roll(Roll.result(0));
        frame.roll(Roll.result(10));

        assertThatCode(() -> frame.roll(Roll.result(5))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("스페어 처리에 실패하면 그대로 프레임을 종료한다.")
    void frameEndIfMissed() {
        FinalFrame frame = FinalFrame.init();

        frame.roll(Roll.result(8));
        frame.roll(Roll.result(1));

        assertThat(frame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("이미 종료된 상태에서 공을 던지려고 시도하면 예외 처리한다.")
    void throwExceptionIfFrameAlreadyEnded() {
        FinalFrame strikeFrame = FinalFrame.init();
        strikeFrame.roll(Roll.result(10));
        strikeFrame.roll(Roll.result(10));
        strikeFrame.roll(Roll.result(10));
        assertThat(strikeFrame.isEnded()).isTrue();

        FinalFrame spareFrame = FinalFrame.init();
        spareFrame.roll(Roll.result(6));
        spareFrame.roll(Roll.result(4));
        spareFrame.roll(Roll.result(3));
        assertThat(spareFrame.isEnded()).isTrue();

        FinalFrame missedFrame = FinalFrame.init();
        missedFrame.roll(Roll.result(7));
        missedFrame.roll(Roll.result(2));
        assertThat(missedFrame.isEnded()).isTrue();

        assertAll(
            () -> assertThatThrownBy(() -> strikeFrame.roll(Roll.result(10)))
                    .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> spareFrame.roll(Roll.result(10)))
                    .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> missedFrame.roll(Roll.result(10)))
                    .isInstanceOf(IllegalStateException.class)
        );
    }

}
