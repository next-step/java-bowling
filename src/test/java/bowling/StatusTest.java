package bowling;

import bowling.domain.Pin;
import bowling.domain.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusTest {
    @Test
    @DisplayName("스트라이크")
    void test1() {
        assertThat(Status.findStatus(0, Pin.All_KNOCK_DOWN_PIN, null)).isEqualTo(Status.STRIKE);
    }

    @Test
    @DisplayName("스페어")
    void test2() {
        assertThat(Status.findStatus(1, Pin.of(1), Pin.All_KNOCK_DOWN_PIN)).isEqualTo(Status.SPARE);
    }

    @Test
    @DisplayName("미스")
    void test3() {
        assertThat(Status.findStatus(1, Pin.NO_KNOCK_DOWN_PIN, Pin.of(5))).isEqualTo(Status.MISS);
    }

    @Test
    @DisplayName("거터")
    void test4() {
        assertThat(Status.findStatus(0, Pin.NO_KNOCK_DOWN_PIN, Pin.NO_KNOCK_DOWN_PIN)).isEqualTo(Status.GUTTER);
    }

    @Test
    @DisplayName("Empty")
    void test6() {
        assertThat(Status.findStatus(0, Pin.of(3), Pin.of(3))).isEqualTo(Status.NONE);
    }

}
