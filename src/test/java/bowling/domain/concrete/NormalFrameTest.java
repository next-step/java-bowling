package bowling.domain.concrete;

import bowling.domain.engine.roll.Roll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class NormalFrameTest {

    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = NormalFrame.init();
    }

    @Test
    @DisplayName("첫 투구에서 모든 핀을 쓰러트리면 프레임을 종료한다.")
    void strike() {
        normalFrame.roll(Roll.result(10));

        assertThat(normalFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("두 번째 투구까지 마쳤다면 프레임을 종료한다.")
    void missedOrSpare() {
        NormalFrame missedFrame = NormalFrame.init();
        missedFrame.roll(Roll.result(7));
        missedFrame.roll(Roll.result(2));

        NormalFrame spareFrame = NormalFrame.init();
        spareFrame.roll(Roll.result(8));
        spareFrame.roll(Roll.result(2));

        assertAll(
            () -> assertThat(missedFrame.isEnded()).isTrue(),
            () -> assertThat(spareFrame.isEnded()).isTrue()
        );
    }

    @Test
    @DisplayName("이미 종료된 프레임에서 공을 더 던질 수 없다.")
    void cannotThrowBallIfFrameAlreadyEnded() {
        NormalFrame strikeFrame = NormalFrame.init();
        strikeFrame.roll(Roll.result(10));

        NormalFrame missedFrame = NormalFrame.init();
        missedFrame.roll(Roll.result(7));
        missedFrame.roll(Roll.result(2));

        NormalFrame spareFrame = NormalFrame.init();
        spareFrame.roll(Roll.result(8));
        spareFrame.roll(Roll.result(2));

        assertAll(
            () -> assertThatThrownBy(() -> strikeFrame.roll(Roll.result(0)))
                .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> missedFrame.roll(Roll.result(0)))
                .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> spareFrame.roll(Roll.result(0)))
                .isInstanceOf(IllegalStateException.class)
        );
    }
    
}
