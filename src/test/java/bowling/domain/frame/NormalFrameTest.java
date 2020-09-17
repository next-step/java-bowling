package bowling.domain.frame;

import bowling.domain.pin.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("일반 프레임에서 0~9개 핀을 치면 상태가 계속(False) 값인지 테스트")
    void continue_test() {
        // given
        Frame normalFrame = NormalFrame.of();

        // when
        normalFrame.bowl(Pin.of(9));

        // then
        assertThat(normalFrame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("일반 프레임에서 MISS 이면 상태가 종료(True) 값인지 테스트")
    void miss_test() {
        // given
        Frame normalFrame = NormalFrame.of();

        // when
        normalFrame.bowl(Pin.of(8));
        normalFrame.bowl(Pin.of(1));

        // then
        assertThat(normalFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("일반 프레임에서 Spare 이면 상태가 종료(True) 값인지 테스트")
    void spare_test() {
        // given
        Frame normalFrame = NormalFrame.of();

        // when
        normalFrame.bowl(Pin.of(8));
        normalFrame.bowl(Pin.of(2));

        // then
        assertThat(normalFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("일반 프레임에서 Strike 이면 상태가 종료(True) 값인지 테스트")
    void strike_test() {
        // given
        Frame normalFrame = NormalFrame.of();

        // when
        normalFrame.bowl(Pin.of(10));

        // then
        assertThat(normalFrame.isEnd()).isTrue();
    }

}
