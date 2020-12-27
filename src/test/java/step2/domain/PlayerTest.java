package step2.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("Player 생성 기능")
    void createPlayer() {
        assertThat(Player.from("abc").toString()).isEqualTo("abc");
    }

    @ParameterizedTest
    @CsvSource(value = {"a","ab","abcd"})
    @DisplayName("이름이 3글자가 아닐 경우 예외 처리")
    void exceptNameLength(String name) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> Player.from(name));
    }

}