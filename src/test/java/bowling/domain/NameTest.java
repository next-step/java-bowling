package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created By mand2 on 2020-12-18.
 */
public class NameTest {

    @Test
    @DisplayName("이름 생성자")
    void inputName() {
        Name name = Name.from("PJS");

        assertThat(name).isEqualTo(Name.from("PJS"));
    }

    @ParameterizedTest
    @DisplayName("이름 세자리수가 아니면 예외처리")
    @ValueSource(strings = {"PJ", "PJS1"})
    void validateInputNameLength(String inputName) {
        assertThatThrownBy(() -> Name.from(inputName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Name.MESSAGE_NAME_PATTERN_LENGTH);
    }


    @ParameterizedTest
    @DisplayName("이름 영어대소문자가 아니면 예외처리")
    @ValueSource(strings = {"가pa", "PJ1"})
    void validateInputNameLanguage(String inputName) {
        assertThatThrownBy(() -> Name.from(inputName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Name.MESSAGE_NAME_PATTERN_LENGTH);
    }


}
