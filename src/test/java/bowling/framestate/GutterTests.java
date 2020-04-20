package bowling.framestate;

import bowling.FrameScore;
import bowling.Pin;
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
    public void addingUpFrameScoreTest() {
        assertThat(gutter.addNextAddingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.createStrike());
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertTrue(gutter.isOver());
    }
}
