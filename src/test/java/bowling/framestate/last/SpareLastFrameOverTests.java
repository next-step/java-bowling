package bowling.framestate.last;

import bowling.FrameScore;
import bowling.LeftScoreCount;
import bowling.Pin;
import bowling.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("마지막 프레임 스페어 포함 상태로 종료")
public class SpareLastFrameOverTests {

    private SpareLastFrameOver spareLastFrameOver = SpareLastFrameOver.newInstance(Arrays.asList(Pin.of(6), Pin.of(4), Pin.of(3)));

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(() -> SpareLastFrameOver.newInstance(Arrays.asList(Pin.of(6), Pin.of(4), Pin.of(3))));
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThatIllegalStateException()
                .isThrownBy(() -> spareLastFrameOver.bowl(Pin.of(1)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(spareLastFrameOver.createFrameScore()).isEqualTo(FrameScore.newInstance(Score.of(16), LeftScoreCount.of(0)));
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        assertThat(spareLastFrameOver.addingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.newInstance(Score.of(20), LeftScoreCount.of(0)));
        assertThat(spareLastFrameOver.addingUpFrameScore(FrameScore.createSpare())).isEqualTo(FrameScore.newInstance(Score.of(16), LeftScoreCount.of(0)));
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertTrue(spareLastFrameOver.isOver());
    }
}
