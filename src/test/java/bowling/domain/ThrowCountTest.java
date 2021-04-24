package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThrowCountTest {

    @DisplayName("던지는 횟수는 0~ 3 번 사이입니다.")
    @Test
    void case1() {
        Assertions.assertThatThrownBy(() -> {
            ThrowCount.of(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}