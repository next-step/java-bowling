package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    @Test
    @DisplayName("RoundNumber와 FrameScore를 가지고 생성한다.")
    void create() {
        // given
        final RoundNumber roundNumber = new RoundNumber(1);
        final FrameScore frameScore = new FrameScore();

        // when
        final Frame frame = new Frame(roundNumber, frameScore);

        // then
        assertThat(frame).isEqualTo(new Frame(roundNumber, frameScore));
    }
}
