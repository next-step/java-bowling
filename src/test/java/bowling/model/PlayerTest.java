package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlayerTest {

    @DisplayName("Player가 생성된다.")
    @Test
    void createPlayer() {
        Player player = new Player("MSJ");
        assertThat(player).isEqualTo(new Player("MSJ"));
    }

    @DisplayName("이름이 영어 3글자가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"문수정", "sj", "123"})
    void validateName(String input) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Player(input);
                });
    }
}
