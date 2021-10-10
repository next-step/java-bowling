package step4.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PinsTest {
    @DisplayName("볼링핀을 11개 쓰러 뜨렸을 경우 예외 발생")
    @Test
    void maxPinTest() {
        Assertions.assertThatThrownBy(() -> {
            new Pins(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("볼링핀을 10개 쓰러 뜨렸을 경우 예외가 발생한다.")
    @Test
    void maxPinTest2() {
        new Pins(10);
    }

    @DisplayName("볼링핀을 -1개 쓰러 뜨렸을 경우 예외 발생.")
    @Test
    void minPinTest() {
        Assertions.assertThatThrownBy(() -> {
            new Pins(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("볼링핀을 0개 쓰러 뜨렸을 경우 예외가 발생하지 않는다.")
    @Test
    void minPinTest2() {
        new Pins(0);
    }

}