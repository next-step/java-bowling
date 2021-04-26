package bowling.domain.frame;

import bowling.exception.NoNextFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @Test
    @DisplayName("FrameScore를 받아 마지막 프레임을 생성한다.")
    void create() {
        // given
        final FrameScore frameScore = new FrameScore();

        // when
        final FinalFrame finalFrame = FinalFrame.from(frameScore);

        // then
        assertThat(finalFrame).isEqualTo(FinalFrame.from(frameScore));
    }

    @Test
    @DisplayName("FinalFrame의 다음 프레임은 생성되지 않는다.")
    void nextFrame() {
        // given
        final FrameScore frameScore = new FrameScore();
        final FinalFrame finalFrame = FinalFrame.from(frameScore);

        // when
        // then
        assertThatThrownBy(finalFrame::createNextFrame)
                .isInstanceOf(NoNextFrameException.class)
                .hasMessage(NoNextFrameException.FINAL_FRAME_CANNOT_CREATE_NEXT_FRAME);
    }

    @Test
    @DisplayName("FinalFrame은 FinalFrame이다.")
    void isFinalFrame() {
        assertThat(FinalFrame.from(new FrameScore()).isFinalFrame()).isTrue();
    }
}
