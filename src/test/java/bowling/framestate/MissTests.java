package bowling.framestate;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import bowling.framestate.Miss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Miss 테스트")
public class MissTests {

    private Miss miss = Miss.newInstance(Pin.of(6), Pin.of(3));

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> Miss.newInstance(Pin.of(6), Pin.of(3)));
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThatIllegalStateException()
                .isThrownBy(() -> miss.bowl(Pin.of(1)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(miss.createFrameScore()).isEqualTo(FrameScore.newInstance(Score.of(9), LeftScoreCount.of(0)));
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        assertThat(miss.addNextAddingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(19), LeftScoreCount.of(0)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertTrue(miss.isOver());
    }
}
