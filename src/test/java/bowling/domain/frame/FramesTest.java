package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FramesTest {

    @DisplayName("frmae 10개가 포함된 frames를 만들 수 있다")
    @Test
    void should_make_frames_in_10_frame() {
        //arrange, act, assert
        assertThat(Frames.of()).isInstanceOf(Frames.class);
    }

    @DisplayName("Bowling 게임이 끝났는지 확인 결과를 반환한다")
    @Test
    public void should_return_bowling_game_state() throws Exception {
        //arrange, act
        boolean isFinish = Frames.of().isBowlingFinish();

        //assert
        assertFalse(isFinish);
    }

}