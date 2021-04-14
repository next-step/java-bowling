package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FrameTest {

    private Frame frame;

    @BeforeEach
    void setup() {
        frame = new Frame();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2", "0:9", "5:3", "6:2"}, delimiter = ':')
    void canThrowTwice(int prePoint, int curPoint) {
        frame.throwBall(prePoint);
        assertThat(frame.ended()).isFalse();

        frame.throwBall(curPoint);
        assertThat(frame.ended()).isTrue();
    }

    @Test
    void canDetermineStrike() {
        frame.throwBall(10);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:10:true", "1:9:true", "2:8:true", "3:7:true", "4:6:true",
            "5:5:true", "6:4:true", "7:3:true", "8:2:true", "9:1:true",
            "0:8:false", "1:4:false", "2:7:false", "3:4:false", "5:3:false",
            "6:2:false", "7:1:false", "8:1:false", "9:0:false"}, delimiter = ':')
    void canDetermineSpare(int prePoint, int curPoint, boolean expected) {
        frame.throwBall(prePoint);
        frame.throwBall(curPoint);

        assertThat(frame.isSpare()).isEqualTo(expected);
    }

}
