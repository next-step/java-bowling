package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NameTest {

    @DisplayName("적절한 이름 테스트")
    @ValueSource(strings = {"ABC", "MDH", "QQQ", "ZZZ", "ASF"})
    @ParameterizedTest
    void validNameTest(String value) {
        assertEquals(new Name(value).getValue(), value);
    }

    @DisplayName("부적절한 이름 테스트")
    @ValueSource(strings = {"aBC", "AMDH", "1QQ", "ZZ", "AS&"})
    @ParameterizedTest
    void invalidNameTest(String value) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Name(value));
    }
}
