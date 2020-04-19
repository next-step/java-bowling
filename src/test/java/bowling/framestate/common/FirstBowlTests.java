package bowling.framestate.common;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FirstBowlTests {
    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> FirstBowl.newInstance(Pin.of(6)));
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        FirstBowl firstBowl = FirstBowl.newInstance(Pin.of(6));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> firstBowl.bowl(Pin.ofMax()));

        assertThat(firstBowl.bowl(Pin.of(4))).isEqualTo(Spare.newInstance(Pin.of(6), Pin.of(4)));
        assertThat(firstBowl.bowl(Pin.of(3))).isEqualTo(Miss.newInstance(Pin.of(6), Pin.of(3)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        FirstBowl firstBowl = FirstBowl.newInstance(Pin.of(7));

        assertThat(firstBowl.createFrameScore()).isEqualTo(FrameScore.newInstance(Score.of(7), LeftScoreCount.of(1)));
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        FirstBowl firstBowl = FirstBowl.newInstance(Pin.of(7));

        assertThat(firstBowl.addingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(17), LeftScoreCount.of(1)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        FirstBowl firstBowl = FirstBowl.newInstance(Pin.of(8));
        assertFalse(firstBowl.isOver());
    }
}
