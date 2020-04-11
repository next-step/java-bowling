package bowling.domain;

import bowling.exception.NameLengthOverException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlayerNameTest {

    @DisplayName("플레이어 이름을 생성할 수 있다.")
    @Test
    void create() {
        String name = "otk";
        PlayerName expect = new PlayerName(name);

        PlayerName actual = new PlayerName(name);

        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("이름이 3글자를 초과하면 커스텀 예외를 발생한다.")
    @Test
    void validNameLength() {
        assertThatExceptionOfType(NameLengthOverException.class).isThrownBy(
                () -> new PlayerName("ohta")
        );
    }
}