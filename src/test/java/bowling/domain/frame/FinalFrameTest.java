package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private FinalFrame finalFrame;

    @BeforeEach
    void setup() {
        finalFrame = new FinalFrame();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2", "0:9", "5:3", "6:2"}, delimiter = ':')
    void canThrowTwoBalls(int prePoint, int curPoint) {
        finalFrame.throwBall(prePoint);
        finalFrame.throwBall(curPoint);
        assertThat(finalFrame.ended()).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:9", "0:10", "5:5", "6:4"}, delimiter = ':')
    void canThrowBonusBalls(int prePoint, int curPoint) {
        finalFrame.throwBall(prePoint);
        finalFrame.throwBall(curPoint);
        assertThat(finalFrame.ended()).isFalse();

        finalFrame.throwBall(prePoint);
        assertThat(finalFrame.ended()).isTrue();
    }

}
