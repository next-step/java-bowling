package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8})
    @DisplayName("다음 프레임을 생성한다.")
    void nextFrame(final int roundNumberSource) {
        // given
        final RoundNumber roundNumber = new RoundNumber(roundNumberSource);
        final RoundNumber nextRoundNumber = new RoundNumber(roundNumberSource + 1);
        final FrameScore frameScore = new FrameScore();
        final Frame frame = new NormalFrame(roundNumber, frameScore);

        // when
        final Frame nextFrame = frame.createNextFrame();

        // then
        assertThat(nextFrame).isEqualTo(new NormalFrame(nextRoundNumber, frameScore));
    }
}
