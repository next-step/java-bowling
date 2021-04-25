package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("RoundNumber와 FrameScore를 가지고 생성한다.")
    void create() {
        // given
        final RoundNumber roundNumber = new RoundNumber(1);
        final FrameScore frameScore = new FrameScore();

        // when
        final Frame frame = new NormalFrame(roundNumber, frameScore);

        // then
        assertThat(frame).isEqualTo(new NormalFrame(roundNumber, frameScore));
    }

    @Test
    @DisplayName("첫 프레임을 생성한다.")
    void createFirstFrame() {
        // given
        final int firstRoundNumber = 1;
        final RoundNumber roundNumber = new RoundNumber(firstRoundNumber);
        final FrameScore frameScore = new FrameScore();

        // when
        final Frame frame = NormalFrame.createFirstFrame();

        // then
        assertThat(frame).isEqualTo(new NormalFrame(roundNumber, frameScore));
    }
}
