package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("마지막 프레임 스트라이크 포함 상태로 종료")
public class StrikeLastFrameOverTests {

    private StrikeLastFrameOver strikeLastFrameOver = StrikeLastFrameOver.newInstance(Pin.of(6));

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> strikeLastFrameOver = StrikeLastFrameOver.newInstance(Pin.of(6)));
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThatIllegalStateException()
                .isThrownBy(() -> strikeLastFrameOver.bowl(Pin.of(1)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(strikeLastFrameOver.createFrameScore()).isEqualTo(FrameScore.newInstance(Score.of(22), LeftScoreCount.of(0)));
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        assertThat(strikeLastFrameOver.addingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(26), LeftScoreCount.of(0)));
        assertThat(strikeLastFrameOver.addingUpFrameScore(FrameScore.createSpare())).isEqualTo(FrameScore.newInstance(Score.of(20), LeftScoreCount.of(0)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertTrue(strikeLastFrameOver.isOver());
    }
}
