package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @DisplayName("3자리 문자열을 입력 받으면, Player객체를 생성한다")
    @Test
    void createTest() {
        assertThat(Player.from("KCS")).isInstanceOf(Player.class);
    }

    @DisplayName("2자리 문자열을 입력 받으면, 예외를 던진다")
    @Test
    void exceptionTest1() {
        assertThatThrownBy(() -> Player.from("KC")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈문자를 입력 받으면, 예외를 던진다")
    @Test
    void exceptionTest2() {
        assertThatThrownBy(() -> Player.from("")).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("널을 입력 받으면, 예외를 던진다")
    @Test
    void exceptionTest3() {
        assertThatThrownBy(() -> Player.from(null)).isInstanceOf(IllegalArgumentException.class);
    }
}
