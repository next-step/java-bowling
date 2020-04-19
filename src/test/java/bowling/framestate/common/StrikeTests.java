package bowling.framestate.common;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("스트라이크 테스트")
public class StrikeTests {

    private Strike strike = Strike.newInstance();

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(Strike::newInstance);
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThatIllegalStateException()
                .isThrownBy(() -> strike.bowl(Pin.of(1)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(strike.createFrameScore()).isEqualTo(FrameScore.createStrike());
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        assertThat(strike.addingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(20), LeftScoreCount.of(1)));
        assertThat(strike.addingUpFrameScore(FrameScore.createSpare())).isEqualTo(FrameScore.newInstance(Score.of(20), LeftScoreCount.of(0)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertTrue(strike.isOver());
    }
}
