package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstBowlTest {
    @Test
    @DisplayName("두번째 시도에서 모든 핀을 쓰러트린다면 스페어 상태를 반환한다.")
    void bowlWhenSpare() {
        FirstBowl firstBowl = new FirstBowl(new Pins(4));
        assertThat(firstBowl.bowl(6) instanceof Spare).isTrue();
    }

    @Test
    @DisplayName("두번째 시도에서 모든 핀을 쓰러뜨리지 못하면 Miss 상태를 반환한다.")
    void bowlWhenMiss() {
        FirstBowl firstBowl = new FirstBowl(new Pins(4));
        assertThat(firstBowl.bowl(5) instanceof Miss).isTrue();
    }

    @Test
    @DisplayName("해당 상태에서는 해당 프레임이 진행중임을 알린다.")
    void isFinish() {
        assertThat(new FirstBowl(new Pins(4)).isFinish()).isFalse();
    }

    @Test
    @DisplayName("쓰러뜨린 핀의 합계가 10이 넘으면 예외를 던진다.")
    void bowlWhenOver10Pins() {
        FirstBowl firstBowl = new FirstBowl(new Pins(4));
        assertThatThrownBy(() -> firstBowl.bowl(7))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("쓰러트린 핀의 합계는 10을 넘을 수 없습니다");
    }
}
