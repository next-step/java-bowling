package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.Score;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @Test
    void number() {
        assertThat(new FinalFrame().number()).isEqualTo(10);
    }

    @ParameterizedTest
    @CsvSource({"5,5,5", "10,3,5"})
    void bowl_WhenFrameIsFinished_ThrowsIllegalStateException(int firstPin, int secondPin, int thirdPin) {
        Frame finalFrame = createFinalFrame(firstPin, secondPin, thirdPin);

        assertThatThrownBy(() -> finalFrame.bowl(Pin.of(5)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void score_Miss() {
        Frame finalFrame = new FinalFrame()
                .bowl(Pin.of(4))
                .bowl(Pin.of(5));

        Score score = finalFrame.score();

        assertThat(score.getScore()).isEqualTo(9);
    }

    @ParameterizedTest
    @CsvSource({"6,4,9,19", "6,4,10,20", "10,5,4,19", "10,5,5,20", "10,10,9,29", "10,10,10,30"})
    void score_SpareOrStrike(int firstPin, int secondPin, int thirdPin, int result) {
        Frame finalFrame = createFinalFrame(firstPin, secondPin, thirdPin);

        Score score = finalFrame.score();

        assertThat(score.getScore()).isEqualTo(result);
    }

    @ParameterizedTest
    @CsvSource({"5,5", "0,10", "10,0", "10,10"})
    void finished_False(int firstPin, int secondPin) {
        Frame finalFrame = new FinalFrame()
                .bowl(Pin.of(firstPin))
                .bowl(Pin.of(secondPin));

        assertThat(finalFrame.finished()).isFalse();
    }

    @ParameterizedTest
    @CsvSource({"5,5,5", "8,2,9", "10,8,1", "10,8,2", "10,10,9", "10,10,10"})
    void finished_True(int firstPin, int secondPin, int thirdPin) {
        Frame finalFrame = createFinalFrame(firstPin, secondPin, thirdPin);

        assertThat(finalFrame.finished()).isTrue();
    }

    @Test
    void additionalScore_ForSpare() {
        Frame finalFrame = createFinalFrame(5, 5, 5);

        Score score = finalFrame.additionalScore(Score.ofSpare());

        assertThat(score.getScore()).isEqualTo(15);
    }

    @Test
    void additionalScore_ForStrike() {
        Frame finalFrame = createFinalFrame(5, 5, 5);

        Score score = finalFrame.additionalScore(Score.ofStrike());

        assertThat(score.getScore()).isEqualTo(20);
    }

    @ParameterizedTest
    @CsvSource({
            "8,2,9,8|/|9",
            "8,2,10,8|/|X",
            "10,8,1,X|8|1",
            "10,8,2,X|8|/",
            "10,10,9,X|X|9",
            "10,10,10,X|X|X"})
    void expression(int firstPin, int secondPin, int thirdPin, String result) {
        Frame finalFrame = createFinalFrame(firstPin, secondPin, thirdPin);

        assertThat(finalFrame.expression()).isEqualTo(result);
    }

    private Frame createFinalFrame(int firstPin, int secondPin, int thirdPin) {
        return new FinalFrame()
                .bowl(Pin.of(firstPin))
                .bowl(Pin.of(secondPin))
                .bowl(Pin.of(thirdPin));
    }
}
