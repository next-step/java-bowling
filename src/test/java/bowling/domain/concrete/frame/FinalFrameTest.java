package bowling.domain.concrete.frame;

import bowling.domain.RollResult;
import bowling.domain.engine.frame.state.CannotCalculateScoreException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinalFrameTest {

    private FinalFrame strikeFrame;
    private FinalFrame strikeFrame2;
    private FinalFrame strikeFrame3;
    private FinalFrame spareFrame;
    private FinalFrame missedFrame;

    @BeforeEach
    void setUp() {
        strikeFrame = FinalFrame.init();
        strikeFrame.roll(RollResult.of(10));
        strikeFrame.roll(RollResult.of(10));
        strikeFrame.roll(RollResult.of(10));

        strikeFrame2 = FinalFrame.init();
        strikeFrame2.roll(RollResult.of(10));
        strikeFrame2.roll(RollResult.of(6));
        strikeFrame2.roll(RollResult.of(4));

        strikeFrame3 = FinalFrame.init();
        strikeFrame3.roll(RollResult.of(10));
        strikeFrame3.roll(RollResult.of(6));
        strikeFrame3.roll(RollResult.of(3));

        spareFrame = FinalFrame.init();
        spareFrame.roll(RollResult.of(6));
        spareFrame.roll(RollResult.of(4));
        spareFrame.roll(RollResult.of(3));

        missedFrame = FinalFrame.init();
        missedFrame.roll(RollResult.of(7));
        missedFrame.roll(RollResult.of(2));
    }

    @Test
    @DisplayName("첫 투구가 스트라이크라면 두 번 더 던질 수 있다.")
    void throwThreeTimes() {
        FinalFrame frame = FinalFrame.init();

        frame.roll(RollResult.of(10));

        assertThatCode(() -> {
            frame.roll(RollResult.of(10));
            frame.roll(RollResult.of(10));
        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("스페어 처리가 되었다면, 한 번 더 던질 수 있다.")
    void giveOneMoreChanceIfSpareCleared() {
        FinalFrame frame = FinalFrame.init();

        frame.roll(RollResult.of(0));
        frame.roll(RollResult.of(10));

        assertThatCode(() -> frame.roll(RollResult.of(5))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("스페어 처리에 실패하면 그대로 프레임을 종료한다.")
    void frameEndIfMissed() {
        FinalFrame frame = FinalFrame.init();

        frame.roll(RollResult.of(8));
        frame.roll(RollResult.of(1));

        assertThat(frame.isEnded()).isTrue();
    }

    @Test
    @DisplayName("이미 종료된 상태에서 공을 던지려고 시도하면 예외 처리한다.")
    void throwExceptionIfFrameAlreadyEnded() {
        FinalFrame strikeFrame = FinalFrame.init();
        strikeFrame.roll(RollResult.of(10));
        strikeFrame.roll(RollResult.of(10));
        strikeFrame.roll(RollResult.of(10));
        assertThat(strikeFrame.isEnded()).isTrue();

        FinalFrame strikeFrame2 = FinalFrame.init();
        strikeFrame2.roll(RollResult.of(10));
        strikeFrame2.roll(RollResult.of(6));
        strikeFrame2.roll(RollResult.of(4));
        assertThat(strikeFrame2.isEnded()).isTrue();

        FinalFrame strikeFrame3 = FinalFrame.init();
        strikeFrame3.roll(RollResult.of(10));
        strikeFrame3.roll(RollResult.of(6));
        strikeFrame3.roll(RollResult.of(3));
        assertThat(strikeFrame3.isEnded()).isTrue();

        FinalFrame spareFrame = FinalFrame.init();
        spareFrame.roll(RollResult.of(6));
        spareFrame.roll(RollResult.of(4));
        spareFrame.roll(RollResult.of(3));
        assertThat(spareFrame.isEnded()).isTrue();

        FinalFrame missedFrame = FinalFrame.init();
        missedFrame.roll(RollResult.of(7));
        missedFrame.roll(RollResult.of(2));
        assertThat(missedFrame.isEnded()).isTrue();

        assertAll(
            () -> assertThatThrownBy(() -> strikeFrame.roll(RollResult.of(10)))
                    .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> strikeFrame2.roll(RollResult.of(10)))
                    .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> strikeFrame3.roll(RollResult.of(10)))
                .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> spareFrame.roll(RollResult.of(10)))
                    .isInstanceOf(IllegalStateException.class),
            () -> assertThatThrownBy(() -> missedFrame.roll(RollResult.of(10)))
                    .isInstanceOf(IllegalStateException.class)
        );
    }

    @Test
    @DisplayName("점수를 생성한다.")
    void createScore() {
        assertAll(
            () -> assertThat(strikeFrame.getScore().getValue()).isEqualTo(30),
            () -> assertThat(strikeFrame2.getScore().getValue()).isEqualTo(20),
            () -> assertThat(strikeFrame3.getScore().getValue()).isEqualTo(19),
            () -> assertThat(spareFrame.getScore().getValue()).isEqualTo(13),
            () -> assertThat(missedFrame.getScore().getValue()).isEqualTo(9)
        );
    }

    @Test
    @DisplayName("점수 계산을 완료할 수 없을 때 점수를 가져오려고 시도하면 예외 처리한다.")
    void cannotGetScoreThatIfCalculationIsNotCompleted() {
        FinalFrame finalFrame = FinalFrame.init();

        finalFrame.roll(RollResult.of(10));
        finalFrame.roll(RollResult.of(10));

        assertThatThrownBy(finalFrame::getScore).isInstanceOf(CannotCalculateScoreException.class);
    }
    
}
