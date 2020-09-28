package bowling.domain;

import bowling.domain.frame.LastFrame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreUpdaterTest {

    @DisplayName("점수 업데이트 테스트")
    @Test
    void updateTest() {

        NormalFrame frame = new NormalFrame();
        frame.swing(10);
        assertEquals(frame.getScore(), Swing.DUMMY_SCORE);

        ScoreUpdater updater = new ScoreUpdater();
        updater.checkFrameNeedUpdate(frame);

        updater.update(10);
        assertEquals(frame.getScore(), Swing.DUMMY_SCORE);

        updater.update(10);
        assertEquals(frame.getScore(), 30);
    }
}
