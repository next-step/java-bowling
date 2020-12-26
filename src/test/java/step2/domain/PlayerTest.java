package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerTest {

    @Test
    @DisplayName("플레이어 생성")
    void createPlayer() {
        assertThat(Player.from("abc")).isInstanceOf(Player.class);
    }

    @ParameterizedTest
    @CsvSource({"ab", "abcd"})
    @DisplayName("플레이어 이름이 3글자가 아닌 경우 예외 처리")
    void exceptPlayerLength(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> Player.from(name));
    }

}