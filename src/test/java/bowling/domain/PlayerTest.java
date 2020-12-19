package bowling.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어 생성")
    void createPlayer() {
        String name = "LKM";
        assertThat(Player.from(name)).isInstanceOf(Player.class);
    }

    @Test
    @DisplayName("이름이 3글자가 아닐 경우 예외 처리")
    void exceptNameLengthThree() {
        String name = "abcd";
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Player.from(name));
    }

    @Test
    @DisplayName("이름이 비어있는 경우 예외 처리")
    void exceptNameLengthEmpty() {
        String name = "";
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Player.from(name));
    }

}