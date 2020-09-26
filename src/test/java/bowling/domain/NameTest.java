package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameTest {

    @DisplayName("적절한 이름 테스트")
    @ValueSource(strings = {"ABC", "MDH", "QQQ", "ZZZ", "ASF", "aBc"})
    @ParameterizedTest
    void validNameTest(String value) {
        assertEquals(new Player(value).getName(), value.toUpperCase());
    }

    @DisplayName("부적절한 이름 테스트")
    @ValueSource(strings = {"AMDH", "1QQ", "ZZ", "AS&"})
    @ParameterizedTest
    void invalidNameTest(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Player(value));
    }
}
