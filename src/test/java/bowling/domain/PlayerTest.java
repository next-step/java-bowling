package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    @DisplayName("이름이 3글자가 넘었을때 Exception이 나는지 확인한다.")
    void name() {
        assertThatThrownBy(() -> {
            new Player("tester");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
