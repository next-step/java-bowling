package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"AB", "ABCD", "ABCDE", "   "})
    @DisplayName("이름 길이가 3이 아니면 예외 발생, 공백은 길이로 체크 X")
    void lengthIsNotThreeExceptionTest(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessage("이름은 영어로 3글자여야 합니다.");
    }

    @Test
    @DisplayName("이름이 영어가 아니면 예외 발생")
    void nameIsNotEnglishExceptionTest() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name("홍길동"))
                .withMessage("이름은 영어로 3글자여야 합니다.");
    }
}
