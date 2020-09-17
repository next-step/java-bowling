package bowling.domain.state;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EndFrameStatesTest {

    @Test
    @DisplayName("마지막 프레임에서 MISS를 기록하면 프레임 종료되는 테스트")
    void endFrame_miss_test() {

        // given
        EndFrameStates endFrameStates = new EndFrameStates();

        // when
        endFrameStates.bowl(Pin.of(8));
        endFrameStates.bowl(Pin.of(1));

        // then
        assertThat(endFrameStates.isEnd()).isTrue();
    }

    @Test
    @DisplayName("마지막 프레임에서 SPARE를 기록하면 보너스가 생기는 테스트")
    void endFrame_spare_get_bonus_test() {

        // given
        EndFrameStates endFrameStates = new EndFrameStates();

        // when
        endFrameStates.bowl(Pin.of(8));
        endFrameStates.bowl(Pin.of(2));

        // then
        assertThat(endFrameStates.isEnd()).isFalse();
    }


    @Test
    @DisplayName("마지막 프레임에서 STRIKE를 기록하면 보너스가 2번 생기는 테스트")
    void endFrame_strike_get_two_bonus_test() {
        EndFrameStates endFrameStates = new EndFrameStates();

        endFrameStates.bowl(Pin.of(10));
        assertThat(endFrameStates.isEnd()).isFalse();

        endFrameStates.bowl(Pin.of(2));
        assertThat(endFrameStates.isEnd()).isFalse();

        endFrameStates.bowl(Pin.of(3));
        assertThat(endFrameStates.isEnd()).isTrue();
    }
}
