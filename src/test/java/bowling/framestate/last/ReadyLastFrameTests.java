package bowling.framestate.last;

import bowling.FrameScore;
import bowling.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Ready 마지막 프레임 상태 테스트")
public class ReadyLastFrameTests {

    private ReadyLastFrame readyLastFrame = ReadyLastFrame.newInstance();

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(ReadyLastFrame::newInstance);
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        assertThat(readyLastFrame.bowl(Pin.ofMax())).isInstanceOf(StrikeLastFrame.class);
        assertThat(readyLastFrame.bowl(Pin.of(3))).isEqualTo(FirstBowlLastFrame.newInstance(Pin.of(3)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        assertThat(readyLastFrame.createFrameScore()).isEqualTo(FrameScore.createReady());
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        assertThat(readyLastFrame.addingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.createStrike());
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        assertFalse(readyLastFrame.isOver());
    }
}
