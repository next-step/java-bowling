package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("마지막 프레임 스페어 테스트")
public class SpareLastFrameTests {

    private SpareLastFrame spareLastFrame = SpareLastFrame.newInstance(Pin.of(6), Pin.of(4));

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> SpareLastFrame.newInstance(Pin.of(6), Pin.of(4)));
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThat(spareLastFrame.bowl(Pin.ofMax())).isEqualTo(SpareLastFrameOver.newInstance(Arrays.asList(Pin.of(6), Pin.of(4), Pin.ofMax())));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(spareLastFrame.createFrameScore()).isEqualTo(FrameScore.createSpare());
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        assertThat(spareLastFrame.addNextAddingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(20), LeftScoreCount.of(0)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertFalse(spareLastFrame.isOver());
    }
}
