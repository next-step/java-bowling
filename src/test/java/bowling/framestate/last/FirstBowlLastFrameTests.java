package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import bowling.framestate.common.Miss;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("첫번째 투구 - 마지막 프레임 상태 테스트")
public class FirstBowlLastFrameTests {

    private FirstBowlLastFrame firstBowlLastFrame = FirstBowlLastFrame.newInstance(Pin.of(6));

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> FirstBowlLastFrame.newInstance(Pin.of(6)));
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> firstBowlLastFrame.bowl(Pin.ofMax()));

        assertThat(firstBowlLastFrame.bowl(Pin.of(4))).isEqualTo(SpareLastFrame.newInstance(Pin.of(6), Pin.of(4)));
        assertThat(firstBowlLastFrame.bowl(Pin.of(2))).isEqualTo(Miss.newInstance(Pin.of(6), Pin.of(2)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(firstBowlLastFrame.createFrameScore()).isEqualTo(FrameScore.newInstance(Score.of(6), LeftScoreCount.of(1)));
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        assertThat(firstBowlLastFrame.addingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(16), LeftScoreCount.of(1)));
        assertThat(firstBowlLastFrame.addingUpFrameScore(FrameScore.createSpare())).isEqualTo(FrameScore.newInstance(Score.of(16), LeftScoreCount.of(0)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertFalse(firstBowlLastFrame.isOver());
    }
}
