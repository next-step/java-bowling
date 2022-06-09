package bowling_step3.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubtotalTest {
    @Test
    void 초기에는INIT의state를가진다() {
        Subtotal subtotal = new Subtotal();
        assertThat(subtotal).isEqualTo(new Subtotal(State.INIT, 0));
    }

    @Test
    void accumulateBonus_다음프레임이스트라이크인경우바로보너스기다림을종료() {
        Subtotal subtotal = new Subtotal(State.WAIT_ONCE, 0);
        subtotal.accumulateBonus(10);
        assertThat(subtotal).isEqualTo(new Subtotal(State.DONE, 10));
    }

    @Test
    void accumulateBonus_다음프레임이Strike가아닌경우두번다기다림() {
        Subtotal subtotal = new Subtotal(State.WAIT_TWICE, 0);
        subtotal.accumulateBonus(1);
        assertThat(subtotal).isEqualTo(new Subtotal(State.WAIT_ONCE, 1));
        subtotal.accumulateBonus(2);
        assertThat(subtotal).isEqualTo(new Subtotal(State.DONE, 3));
    }
}
