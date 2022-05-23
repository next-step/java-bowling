package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.Score;
import bowling.domain.state.Miss;
import bowling.domain.state.Ready;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @Test
    void number() {
        Frame frame = NormalFrame.init();

        assertThat(frame.number()).isEqualTo(1);
    }

    @ParameterizedTest
    @CsvSource({"5,4", "5,5"})
    void bowl_WhenTwoPinsAreMissOrSpare_ReturnsNextFrame(int firstPin, int secondPin) {
        Frame frame = NormalFrame.init();
        Frame nextFrame = frame.bowl(Pin.of(firstPin)).bowl(Pin.of(secondPin));

        assertThat(frame.number() + 1)
                .isEqualTo(nextFrame.number());
    }

    @Test
    void bowl_WhenFirstPinIsStrike_ReturnsNextFrame() {
        Frame frame = NormalFrame.init();
        Frame nextFrame = frame.bowl(Pin.of(10));

        assertThat(frame.number() + 1)
                .isEqualTo(nextFrame.number());
    }

    @Test
    void bowl_WhenNineFrameFinished_ReturnsLastFrame() {
        Frame nineFrame = new NormalFrame(9, new Ready());
        Frame lastFrame = nineFrame.bowl(Pin.of(10));

        assertThat(lastFrame).isInstanceOf(FinalFrame.class);
    }

    @Test
    void score_WhenCantGetScore_ThrowsIllegalStateException() {
        Frame firstFrame = NormalFrame.init();
        firstFrame.bowl(Pin.of(10))
                .bowl(Pin.of(4));

        Score score = firstFrame.score();
        assertThatThrownBy(score::getScore)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void score_Miss() {
        Frame firstFrame = NormalFrame.init();
        firstFrame.bowl(Pin.of(5))
                .bowl(Pin.of(4));

        Score score = firstFrame.score();
        assertThat(score.getScore()).isEqualTo(9);
    }

    @ParameterizedTest
    @CsvSource({"5,5,4,14", "10,4,4,18"})
    void score_WhenSpareOrStrike_ReturnsScoreAfterThirdBowl(int firstPin, int secondPin, int thirdPin, int result) {
        Frame frame = NormalFrame.init();
        frame.bowl(Pin.of(firstPin))
                .bowl(Pin.of(secondPin))
                .bowl(Pin.of(thirdPin));

        Score score = frame.score();
        assertThat(score.getScore()).isEqualTo(result);
    }

    @Test
    void additionalScore_ForSpareFromMissFrame54() {
        Frame frame = new NormalFrame(2, new Miss(5, 4));

        Score beforeScore = Score.ofSpare();
        Score afterScore = frame.additionalScore(beforeScore);

        assertThat(afterScore.getScore()).isEqualTo(15);
    }

    @Test
    void additionalScore_ForStrikeFromStrikeFrameAndMissFrame54() {
        Frame nextFrame = new NormalFrame(3, new Miss(5, 4));
        Frame frame = new NormalFrame(2, new Strike(), nextFrame);

        Score beforeScore = Score.ofStrike();
        Score afterScore = frame.additionalScore(beforeScore);

        assertThat(afterScore.getScore()).isEqualTo(25);
    }
}
