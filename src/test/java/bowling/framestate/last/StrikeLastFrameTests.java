package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StrikeLastFrameTests {

    private StrikeLastFrame strikeLastFrame = StrikeLastFrame.newInstance();

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(StrikeLastFrame::newInstance);
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThat(strikeLastFrame.bowl(Pin.ofMax())).isEqualTo(StrikeLastFrameOver.newInstance(Pin.ofMax()));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(strikeLastFrame.createFrameScore()).isEqualTo(FrameScore.newInstance(Score.ofAllPins(), LeftScoreCount.of(1)));
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        assertThat(strikeLastFrame.addNextAddingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(20), LeftScoreCount.of(1)));
        assertThat(strikeLastFrame.addNextAddingUpFrameScore(FrameScore.createSpare())).isEqualTo(FrameScore.newInstance(Score.of(20), LeftScoreCount.of(0)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertFalse(strikeLastFrame.isOver());
    }
}
