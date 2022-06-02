package bowling.domain;

import org.junit.jupiter.api.Test;

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

}