package bowling.domain;

import bowling.exception.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NameTest {

    @DisplayName("Name 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"JIA", "LPE", "dsa"})
    void from(String name) {
        Name actual = Name.from(name);

        assertThat(actual).isEqualTo(Name.from(name));
    }

    @DisplayName("Name 3글자 제한 생성 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"SL", "sleep"})
    void from_invalid_name(String name) {
        String exceptionMessage = ExceptionMessage.INVALID_NAME_LENGTH;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Name.from(name))
                .withMessage(exceptionMessage);
    }

    @DisplayName("Name 빈 이름 생성 테스트")
    @ParameterizedTest
    @NullAndEmptySource
    void from_empty_name(String name) {
        String exceptionMessage = ExceptionMessage.NAME_NOT_NULL;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Name.from(name))
                .withMessage(exceptionMessage);
    }

}
