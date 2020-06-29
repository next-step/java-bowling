package bowling.player.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @DisplayName("이름이 Null 또는 공백이면 IllegalArgumentException throw")
    @ParameterizedTest
    @NullAndEmptySource
    void validateNullOrEmptyName(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름이 null 또는 공백입니다.");
    }

    @DisplayName("이름의 길이가 3이 아니면 IllegalArgumentException throw")
    @ParameterizedTest
    @ValueSource(strings = {"lm", "lemh"})
    void validateNameLength(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름의 길이는 3 이어야 합니다. - " + name);
    }
}