package bowling.framestate.common;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("스페어 테스트")
public class SpareTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> Spare.newInstance(Pin.of(6), Pin.of(4)));
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        Spare spare = Spare.newInstance(Pin.of(6), Pin.of(4));

        assertThatIllegalStateException()
                .isThrownBy(() -> spare.bowl(Pin.of(1)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        Spare spare = Spare.newInstance(Pin.of(6), Pin.of(4));

        assertThat(spare.createFrameScore()).isEqualTo(FrameScore.createSpare());
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        Spare spare = Spare.newInstance(Pin.of(6), Pin.of(4));

        assertThat(spare.addingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(20), LeftScoreCount.of(0)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        Spare spare = Spare.newInstance(Pin.of(6), Pin.of(4));
        assertTrue(spare.isOver());
    }
}
