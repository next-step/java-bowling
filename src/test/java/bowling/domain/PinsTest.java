package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PinsTest {
    @Test
    void 투구한_핀_개수가_0보다_작을_때() {
        assertThatThrownBy(() -> {
            new Pins(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 투구한_핀_개수가_10보다_클_때() {
        assertThatThrownBy(() -> {
            new Pins(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 핀_합계_계산() {
        assertThat(new Pins(5).sum(new Pins(4))).isEqualTo(9);
    }

}