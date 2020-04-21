package bowling.framestate;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Gutter 테스트")
public class GutterTests {

    private Gutter gutter = Gutter.newInstance();

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(Gutter::newInstance);
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThatIllegalStateException()
                .isThrownBy(() -> gutter.bowl(Pin.of(1)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(gutter.createFrameScore()).isEqualTo(FrameScore.createGutter());
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void sumBeforeScoreTest() {
        assertThat(gutter.sumBeforeScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(10), LeftScoreCount.of(0)));
        assertThat(gutter.sumBeforeScore(FrameScore.createSpare())).isEqualTo(FrameScore.newInstance(Score.of(10), LeftScoreCount.of(0)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertTrue(gutter.isOver());
    }
}
