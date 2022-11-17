package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NameTest {

    @Test
    @DisplayName("기본기능 테스트")
    void name() {
        Name name = new Name("ASD");
        assertThat(name.getName()).isEqualTo("ASD");
    }

    @ParameterizedTest
    @ValueSource(strings = {"AA", "DDDD"})
    @DisplayName("범위를 벗어나는 이름 테스트")
    void name_range(String value) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Name(value));
    }
}
