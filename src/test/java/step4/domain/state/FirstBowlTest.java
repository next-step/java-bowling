package step4.domain.state;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstBowlTest {
    @DisplayName("FirstBowl 상태에서 남은 볼링핀을 모두 쓰러뜨리면 Spare 상태가 된다.")
    @Test
    void FirstBowlToSpare() {
        FirstBowl firstBowl = new FirstBowl(4);
        State state = firstBowl.throwBowl(6);
        Assertions.assertThat(state).isEqualTo(new Spare(4, 6));
    }

    @DisplayName("FirstBowl 상태에서 남은 볼링핀을 모두 못 쓰러뜨리면 LastBowl 상태가 된다.")
    @Test
    void FirstBowlToLastBowl() {
        FirstBowl firstBowl = new FirstBowl(4);
        State state = firstBowl.throwBowl(5);
        Assertions.assertThat(state).isEqualTo(new LastBowl(4, 5));
    }

    @DisplayName("FirstBowl 상태에서 남은 볼링핀 이상의 수가 input으로 들어오면 exception이 발생한다.")
    @Test
    void FirstBowlException() {
        FirstBowl firstBowl = new FirstBowl(4);
        State state = firstBowl.throwBowl(5);
        Assertions.assertThatThrownBy(() -> {
            firstBowl.throwBowl(7);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}