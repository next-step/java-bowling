package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class NormalFrameTest {

    @DisplayName("normal 프레임에서 첫번째 투구에서 쓰러트린 핀이 10개면 스트라이크 점수를 받는다.")
    @Test
    void strike() {
        assertThat(new NormalFrame( 10).getScore()).isEqualTo(ScoreType.STRIKE);
    }

    @DisplayName("프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태는 스페어 점수를 받는다.")
    @Test
    void spare() {
        NormalFrame normalFrame = new NormalFrame(8);
        normalFrame.addPinCount(2);
        assertThat(normalFrame.getScore()).isEqualTo(ScoreType.SPARE);
    }

    @DisplayName("프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태는 미스 점수를 받는다.")
    @Test
    void miss() {
        NormalFrame normalFrame = new NormalFrame(7);
        normalFrame.addPinCount(0);
        assertThat(normalFrame.getScore()).isEqualTo(ScoreType.MISS);
    }

    @DisplayName("프레임의 두번재 투구에서도 핀을 하나도 쓰러트리지 못한 상태는 거터 점수를 받는다.")
    @Test
    void gutter() {
        NormalFrame normalFrame = new NormalFrame(0);
        normalFrame.addPinCount(0);
        assertThat(normalFrame.getScore()).isEqualTo(ScoreType.GUTTER);
    }

    @DisplayName("정해진 투구 횟수 이상 투구하려 할 때, 에러 발생")
    @Test
    void normalFrame_error(){
        assertThatThrownBy(() -> {
            NormalFrame normalFrame = new NormalFrame(10);
            normalFrame.addPinCount(2);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            NormalFrame normalFrame = new NormalFrame(1);
            normalFrame.addPinCount(3);
            normalFrame.addPinCount(4);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}