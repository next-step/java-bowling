package bowling.framestate;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import bowling.framestate.FirstBowl;
import bowling.framestate.Miss;
import bowling.framestate.Spare;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("첫번째 투구 상태 테스트")
public class FirstBowlTests {

    private FirstBowl firstBowl = FirstBowl.newInstance(Pin.of(6));

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> FirstBowl.newInstance(Pin.of(6)));
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> firstBowl.bowl(Pin.ofMax()));

        assertThat(firstBowl.bowl(Pin.of(4))).isEqualTo(Spare.newInstance(Pin.of(6), Pin.of(4)));
        assertThat(firstBowl.bowl(Pin.of(3))).isEqualTo(Miss.newInstance(Pin.of(6), Pin.of(3)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(firstBowl.createFrameScore()).isEqualTo(FrameScore.newInstance(Score.of(6), LeftScoreCount.of(1)));
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        assertThat(firstBowl.addNextAddingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(16), LeftScoreCount.of(1)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertFalse(firstBowl.isOver());
    }
}
