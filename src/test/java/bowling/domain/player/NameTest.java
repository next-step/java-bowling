package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("이름 테스트")
class NameTest {

    @DisplayName("이름에 공백이 들어가있는 경우 공백은 제거된다.")
    @ParameterizedTest(name = "{index}-{displayName}")
    @CsvSource(value = {" abc,abc",
            "abc ,abc",
            " abc ,abc"
    })
    void nameStrip(String input, String expected) {
        assertThat(new Name(input)).isEqualTo(new Name(expected));
    }

    @DisplayName("이름이 null 또는 빈 문자열인 경우 예외가 발생한다.")
    @ParameterizedTest(name = "{displayName} 입력값={0}")
    @NullAndEmptySource
    void nameEmptyException(String input) {
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름의 길이가 3보다 큰 경우 예외가 발생한다.")
    @Test
    void nameSizeException() {
        assertThatThrownBy(() -> new Name("test"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("한글이 포함되어 있는 경우 예외가 발생한다.")
    @Test
    void korException() {
        assertThatThrownBy(() -> new Name("ㄱa"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
