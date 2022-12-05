package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    @DisplayName("플레이어 이름은 3글자 영문자")
    void test1() {
        assertThatThrownBy(() -> {
            new Player("aaaa");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}