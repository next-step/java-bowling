package bowling.framestate.common;

import bowling.FrameScore;
import bowling.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DisplayName("Ready 상태 테스트")
public class ReadyTests {

    @DisplayName("생성 테스트")
    @Test
    public void generateTest() {
        assertThatCode(Ready::newInstance);
    }

    @DisplayName("bowl 테스트")
    @Test
    public void bowlTest() {
        Ready ready = Ready.newInstance();

        assertThat(ready.bowl(Pin.ofMax())).isInstanceOf(Strike.class);
        assertThat(ready.bowl(Pin.of(3))).isEqualTo(FirstBowl.newInstance(Pin.of(3)));
    }

    @DisplayName("FrameScore 생성 테스트")
    @Test
    public void createFrameScoreTest() {
        Ready ready = Ready.newInstance();

        assertThat(ready.createFrameScore()).isEqualTo(FrameScore.createReady());
    }

    @DisplayName("FrameScore 합산 테스트")
    @Test
    public void addingUpFrameScoreTest() {
        Ready ready = Ready.newInstance();

        assertThat(ready.addingUpFrameScore(FrameScore.createStrike())).isEqualTo(FrameScore.createStrike());
    }

    @DisplayName("종료 테스트")
    @Test
    public void isOverTest() {
        Ready ready = Ready.newInstance();
        assertFalse(ready.isOver());
    }
}
