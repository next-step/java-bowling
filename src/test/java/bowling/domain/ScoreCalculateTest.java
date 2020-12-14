package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreCalculateTest {
    @Test
    @DisplayName("Strike 한 경우 다음 2번의 투구의 점수를 합한다.")
    public void strikeScoreTest() {
        Frame frame1 = NormalFrame.getFirstFrame();
        Frame frame2 = frame1.initNextFrame();
        Frame frame3 = frame2.initNextFrame();

        frame1.setKnockDownPins(KnockDownPins.valueOf(10));
        frame2.setKnockDownPins(KnockDownPins.valueOf(10));
        frame3.setKnockDownPins(KnockDownPins.valueOf(10));

        assertThat(frame1.getScore()).isEqualTo(30);
    }
}
