package bowling.domain.score;

import bowling.domain.frame.DownedPin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ScoreTest {

    @Test
    @DisplayName("스코어의 생성")
    void testScore() {
        List<DownedPin> pins = new ArrayList<>();
        DownedPin firstPitch = DownedPin.fromNumber(4);
        DownedPin secondPitch = firstPitch.fromPreviousPitch(5);
        pins.add(firstPitch);
        pins.add(secondPitch);

        Score sampleScore = new Score(pins);
    }
}
