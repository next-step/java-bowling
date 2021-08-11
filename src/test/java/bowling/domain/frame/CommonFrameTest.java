package bowling.domain.frame;

import bowling.domain.pin.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class CommonFrameTest {

    @DisplayName("CommonFrame은 state와 함께 생성된다.")
    @Test
    void should_make_common_frame() {
        //arrange, act, assert
        assertThat(CommonFrame.of()).isInstanceOf(CommonFrame.class);
    }

    @DisplayName("Bowling 게임이 끝났는지 확인 결과를 반환한다")
    @Test
    public void should_return_bowling_game_state() throws Exception {
        //arrange, act
        boolean isFinish = CommonFrame.of().isBowlingFinish();

        //assert
        assertFalse(isFinish);
    }

    @DisplayName("CommonFrame은 볼링이 끝나지않아서 finish에서 false를 반환한다")
    @Test
    void should_return_false_when_is_finish() {
        //arrange
        CommonFrame commonFrame = CommonFrame.of();

        //act, assert
        assertFalse(commonFrame.isBowlingFinish());
    }

    @DisplayName("현재프레임이 끝났을때 전체 frames에 다음 frame을 생성하여 추가할 수 있다.")
    @Test
    void should_add_next_frame_when_current_frame_is_finish() {
        //arrange
        List<Frame> frames = new ArrayList<>();
        frames.add(CommonFrame.of());
        CommonFrame commonFrame = CommonFrame.of();
        commonFrame.hitPins(Pins.of(10));

        //act
        commonFrame.addFrame(frames);

        //assert
        assertThat(frames.size()).isEqualTo(2);
    }

}