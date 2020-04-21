package bowling.framestate;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("스페어 테스트")
public class SpareTests {

    private Spare spare = Spare.newInstance(Pin.of(6), Pin.of(4));

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> Spare.newInstance(Pin.of(6), Pin.of(4)));
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThatIllegalStateException()
                .isThrownBy(() -> spare.bowl(Pin.of(1)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(spare.createFrameScore()).isEqualTo(FrameScore.createSpare());
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void sumBeforeScoreTest() {
        assertThat(spare.sumBeforeScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(20), LeftScoreCount.of(0)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertTrue(spare.isOver());
    }
}
