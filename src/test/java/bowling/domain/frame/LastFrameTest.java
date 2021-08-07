package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        //arrange, act
        boolean isFinish = LastFrame.of().isBowlingFinish();

        //assert
        assertFalse(isFinish);
    }

}