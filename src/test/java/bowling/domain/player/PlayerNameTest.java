package bowling.domain.player;

import bowling.domain.player.PlayerName;
import bowling.exception.InvalidNameException;
import bowling.exception.NameLengthOverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlayerNameTest {

    @DisplayName("플레이어 이름을 생성할 수 있다.")
    @Test
    void create() {
        String name = "otk";
        PlayerName expect = PlayerName.of(name);

        PlayerName actual = PlayerName.of(name);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("이름에 공백이나 null이 들어올 경우 커스텀 예외를 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidNames")
    void validName(String name) {
        assertThatExceptionOfType(InvalidNameException.class).isThrownBy(
                () -> PlayerName.of(name)
        );
    }

    @DisplayName("이름이 3글자를 초과하면 커스텀 예외를 발생한다.")
    @Test
    void validNameLength() {
        assertThatExceptionOfType(NameLengthOverException.class).isThrownBy(
                () -> PlayerName.of("ohta")
        );
    }

    static Stream<String> invalidNames() {
        return Stream.of("", "   ", null);
    }
}