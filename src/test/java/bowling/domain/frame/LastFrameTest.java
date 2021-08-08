package bowling.domain.frame;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LastFrameTest {

    @DisplayName("LastFrame은 state와 함께 생성된다.")
    @Test
    void should_make_last_frame() {
        //arrange, act, assert
        assertThat(LastFrame.of()).isInstanceOf(LastFrame.class);
    }

    @DisplayName("Bowling 게임이 끝났는지 확인 결과를 반환한다")
    @Test
    public void should_return_bowling_game_state() throws Exception {
        //arrange
        LastFrame lastFrame = LastFrame.of();
        lastFrame.hitPins(Pins.of(1));
        lastFrame.hitPins(Pins.of(2));

        //act
        boolean isFinish = lastFrame.isBowlingFinish();

        //assert
        assertTrue(isFinish);
    }

    @DisplayName("LastFrame은 add Frame이 불가능하다")
    @Test
    void should_return_false_when_is_finish() {
        //arrange
        List<Frame> frames = new ArrayList<>();
        frames.add(CommonFrame.of());
        LastFrame lastFrame = LastFrame.of();
        lastFrame.hitPins(Pins.of(10));

        //act
        lastFrame.addFrame(frames);

        //assert
        assertThat(frames.size()).isEqualTo(1);
    }

}