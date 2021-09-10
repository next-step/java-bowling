package bowling.domain;

import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class NormalFrameTest {

    @DisplayName("프레임에서 첫번째 투구에서 쓰러트린 핀이 10개면 스트라이크 점수를 받는다.")
    @Test
    void strike() {
        assertThat(new NormalFrame(1).pitch(10).result()).isEqualTo("X");
        assertThat(new NormalFrame(1, Arrays.asList(10)).valueOfFrame()).isEqualTo("X");
    }

    @DisplayName("프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태는 스페어 점수를 받는다.")
    @Test
    void spare() {
        NormalFrame normalFrame = new NormalFrame(1, Arrays.asList(5));
        assertThat(normalFrame.pitch(5).result()).isEqualTo("5|/");

        assertThat(new NormalFrame(1, Arrays.asList(5)).valueOfFrame()).isEqualTo("5");
    }

    @DisplayName("프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태는 미스 점수를 받는다.")
    @Test
    void miss() {
        NormalFrame normalFrame = new NormalFrame(2, Arrays.asList(1));
        assertThat(normalFrame.pitch(1).result()).isEqualTo("1|1");
        assertThat(normalFrame.valueOfFrame()).isEqualTo("1|1");
    }

    @DisplayName("프레임의 두번재 투구에서도 핀을 하나도 쓰러트리지 못한 상태는 거터 점수를 받는다.")
    @Test
    void gutter() {
        assertThat(new NormalFrame(2, Arrays.asList(1)).pitch(0).result()).isEqualTo("1|-");
        assertThat(new NormalFrame(2, Arrays.asList(1,0)).valueOfFrame()).isEqualTo("1|-");
    }

    @DisplayName("정해진 투구 횟수 이상 투구하려 할 때, 에러 발생")
    @Test
    void normalFrame_error() {
        assertThatThrownBy(() -> {
            new NormalFrame(1, Arrays.asList(1, 2)).pitch(2);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            new NormalFrame(1, Arrays.asList(10)).pitch(2);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("현재 Frame 에서 다음 Frame 을 생성할때 판단 기준인 isEnd ")
    @Test
    void nextFrame() {
        assertThat(new NormalFrame(1, Arrays.asList(1,2)).isEnd()).isTrue();
        assertThat(new NormalFrame(1, Arrays.asList(10)).isEnd()).isTrue();
        assertThat(new NormalFrame(1, Arrays.asList(5,5)).isEnd()).isTrue();
    }

}