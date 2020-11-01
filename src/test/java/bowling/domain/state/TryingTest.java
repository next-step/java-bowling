package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TryingTest {
    private State trying;

    @BeforeEach
    void setUp() {
        trying = new Trying(7);
    }

    @Test
    @DisplayName("Trying 상태에서는 공을 굴릴 수 있다.")
    void isFinish() {
        assertThat(trying.isFinish()).isFalse();
    }

    @Test
    @DisplayName("Trying 상태에서 추가 공을 굴려 핀을 모두 쓰러뜨리면 Spare 를 반환한다.")
    void bowlSpare() {
        State spare = trying.bowl(()->3);
        assertThat(spare).isInstanceOf(Spare.class);
    }

    @Test
    @DisplayName("Trying 상태에서 추가 공을 굴려 핀을 모두 쓰러뜨리지 못하면 Miss 를 반환한다.")
    void bowlMiss() {
        State miss = trying.bowl(()->2);
        assertThat(miss).isInstanceOf(Miss.class);
    }

    @Test
    @DisplayName("핀을 하나 이상 넘어뜨렸을 시 넘어진 핀 수를 출력한다.")
    void printPinCount() {
        assertThat(trying.print().trim()).isEqualTo("7");
    }

    @Test
    @DisplayName("핀을 하나도 넘어뜨리지 못했을 때 Gutter(-)를 출력한다.")
    void printGutter() {
        State gutter = new Trying(0);
        assertThat(gutter.print().trim()).isEqualTo("-");
    }
}
