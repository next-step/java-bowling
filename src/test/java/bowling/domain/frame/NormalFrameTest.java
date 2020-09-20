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
        Frame normalFrame = Frame.init();

        // when
        normalFrame.bowl(Pin.of(9));

        // then
        assertThat(normalFrame.isEnd()).isFalse();
    }

    @Test
    @DisplayName("일반 프레임에서 MISS 이면 상태가 종료(True) 값인지 테스트")
    void miss_test() {
        // given
        Frame normalFrame = Frame.init();

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
        Frame normalFrame = Frame.init();

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
        Frame normalFrame = Frame.init();

        // when
        normalFrame.bowl(Pin.of(10));

        // then
        assertThat(normalFrame.isEnd()).isTrue();
    }

    @Test
    @DisplayName("일반 프레임에서 Continue 이면 쓰러뜨린 핀의 점수와 1번 추가 횟수를 가진 상태를 리턴")
    void continue_calculate_score() {
        Frame frame = Frame.init();
        frame.bowl(Pin.of(8));
        assertThat(frame.getScore()).isEqualTo(Score.of(8,Remaining.CONTINUE));
    }

    @Test
    @DisplayName("일반 프레임에서 Gutter 이면 바로 점수를 계산하는 테스트")
    void gutter_calculate_score() {
        Frame frame = Frame.init();
        frame.bowl(Pin.of(0));
        frame.bowl(Pin.of(0));
        assertThat(frame.getScore()).isEqualTo(Score.of(0,Remaining.of(0)));
    }

    @Test
    @DisplayName("일반 프레임에서 MISS 이면 바로 점수를 계산하는 테스트")
    void miss_calculate_score() {
        Frame frame = Frame.init();
        frame.bowl(Pin.of(3));
        frame.bowl(Pin.of(2));
        assertThat(frame.getScore()).isEqualTo(Score.of(5,Remaining.of(0)));
    }

    @Test
    @DisplayName("일반 프레임에서 Spare 이면 추가 1번 보너스 투구로 점수 계산")
    void spare_calculate_score() {
        Frame frame = Frame.init();
        frame.bowl(Pin.of(8));
        Frame current = frame.bowl(Pin.of(2));
        current.bowl(Pin.of(5));
        assertThat(frame.getScore()).isEqualTo(Score.of(15,Remaining.of(0)));
    }

    @Test
    @DisplayName("일반 프레임에서 Strike 이면 추가 2번 보너스 투구로 더 점수 계산")
    void strike_calculate_score() {
        Frame frame = Frame.init();
        Frame current = frame.bowl(Pin.of(10));
        current.bowl(Pin.of(5));
        current.bowl(Pin.of(1));
        assertThat(frame.getScore()).isEqualTo(Score.of(16,Remaining.of(0)));
    }

}
