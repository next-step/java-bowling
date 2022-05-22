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
    void bowl_WhenExtraPinIsFull_ThrowsIllegalStateException(int firstPin, int secondPin, int thirdPin) {
        Frame finalFrame = new FinalFrame()
                .bowl(Pin.of(firstPin))
                .bowl(Pin.of(secondPin))
                .bowl(Pin.of(thirdPin));

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
    @CsvSource({"6,4,9,19", "10,5,3,18"})
    void score_SpareOrStrike(int firstPin, int secondPin, int thirdPin, int result) {
        Frame finalFrame = new FinalFrame()
                .bowl(Pin.of(firstPin))
                .bowl(Pin.of(secondPin))
                .bowl(Pin.of(thirdPin));

        Score score = finalFrame.score();

        assertThat(score.getScore()).isEqualTo(result);
    }

    @Test
    void additionalScore() {
        Frame finalFrame = new FinalFrame()
                .bowl(Pin.of(10))
                .bowl(Pin.of(3))
                .bowl(Pin.of(5));

        Score score = finalFrame.additionalScore(Score.ofStrike());

        assertThat(score.getScore()).isEqualTo(23);
    }
}
