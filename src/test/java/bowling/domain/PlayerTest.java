package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("Player 클래스 테스트")
public class PlayerTest {

    @DisplayName("Player 객체를 생성할 수 있다.")
    @Test
    void create() {
        String name = "KSJ";
        Player player = new Player(name);

        assertThat(player).isEqualTo(new Player(name));
    }

    @DisplayName("Player 이름은 영어로 3자만 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(strings = {"볼링", "PSVM"})
    void invalid_name(String name) {
        assertThatExceptionOfType(IncorrectPlayerNameException.class)
                .isThrownBy(() -> new Player(name))
                .withMessage(IncorrectPlayerNameException.MESSAGE);
    }
}
