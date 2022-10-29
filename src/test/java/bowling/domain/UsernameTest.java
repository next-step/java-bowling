package bowling.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UsernameTest {

    @ParameterizedTest
    @CsvSource({"four", ","})
    void shouldValidateUsername(String name) {
        assertThatThrownBy(() -> new Username(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
