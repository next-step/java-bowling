package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
