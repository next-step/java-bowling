package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @Test
    @DisplayName("투구 점수를 반영한다.")
    void addBall() {
        int frameNumber = 1;
        Frame frame = new Frame(frameNumber);
        frame.fallDown(5);

        assertThat(frame.getScore()).isEqualTo(5);

        frame.fallDown(5);

        assertThat(frame.getScore()).isEqualTo(10);
    }

    @Test
    @DisplayName("score에 보너스를 더하는지 테스트 한다.")
    void addBonus() {
        Score strike = Score.ofStrike(0, 10);
        Frame firstFallDown = new Frame(2, new Pins(Arrays.asList(new Pin(5), new Pin())));
        Frame secondFallDown = new Frame(2, new Pins(Arrays.asList(new Pin(5), new Pin(4))));

        assertThat(firstFallDown.addBonus(strike)).isEqualTo(new Score(15, 1));
        assertThat(secondFallDown.addBonus(strike)).isEqualTo(new Score(19, 0));

    }
}
