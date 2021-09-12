package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.frame.Frames;

class PlayerTest {
    @DisplayName("3자리 문자열을 입력 받으면, Player객체를 생성한다")
    @Test
    void createTest() {
        assertThat(Player.from("KCS", Frames.from(new ArrayList<>()))).isInstanceOf(Player.class);
    }

    @DisplayName("2자리 문자열을 입력 받으면, 예외를 던진다")
    @Test
    void exceptionTest1() {
        assertThatThrownBy(() -> Player.from("KC", Frames.from(new ArrayList<>()))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("빈문자를 입력 받으면, 예외를 던진다")
    @Test
    void exceptionTest2() {
        assertThatThrownBy(() -> Player.from("", Frames.from(new ArrayList<>()))).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("널을 입력 받으면, 예외를 던진다")
    @Test
    void exceptionTest3() {
        assertThatThrownBy(() -> Player.from(null, Frames.from(new ArrayList<>()))).isInstanceOf(IllegalArgumentException.class);
    }
}
