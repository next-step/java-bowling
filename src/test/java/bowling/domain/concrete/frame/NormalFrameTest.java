package bowling.domain.concrete.frame;

import bowling.domain.RollResult;
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
        normalFrame.roll(RollResult.of(10));

        assertThat(normalFrame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("두 번째 투구까지 마쳤다면 프레임을 종료한다.")
    void missedOrSpare() {
        NormalFrame missedFrame = NormalFrame.init();
        missedFrame.roll(RollResult.of(7));
        missedFrame.roll(RollResult.of(2));

        NormalFrame spareFrame = NormalFrame.init();
        spareFrame.roll(RollResult.of(8));
        spareFrame.roll(RollResult.of(2));

        assertAll(
            () -> assertThat(missedFrame.isEnded()).isTrue(),
            () -> assertThat(spareFrame.isEnded()).isTrue()
        );
    }

    @Test
    @DisplayName("이미 종료된 프레임에서 공을 더 던질 수 없다.")
    void cannotThrowBallIfFrameAlreadyEnded() {
        NormalFrame strikeFrame = NormalFrame.init();
        strikeFrame.roll(RollResult.of(10));

        NormalFrame missedFrame = NormalFrame.init();
        missedFrame.roll(RollResult.of(7));
        missedFrame.roll(RollResult.of(2));

        NormalFrame spareFrame = NormalFrame.init();
        spareFrame.roll(RollResult.of(8));
        spareFrame.roll(RollResult.of(2));

        assertAll(
            () -> assertThatThrownBy(() -> strikeFrame.roll(RollResult.of(0)))
                .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> missedFrame.roll(RollResult.of(0)))
                .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> spareFrame.roll(RollResult.of(0)))
                .isInstanceOf(IllegalStateException.class)
        );
    }
    
}
