package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"abc", "ARX", "pmc", " abe", "yib ", " qtb "})
    @DisplayName("3글자 영어이름으로 이름을 생성")
    void verifiesValidNames(String input) {
        Name name = new Name(input);
        assertThat(name.name()).isEqualTo(input.trim());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "bb", ";13", "ㄱㄴㅊ", "basqhqert", "][z"})
    void invalidNameThrowsException(String input) {
        CustomException customException = Assertions.assertThrows(CustomException.class, () -> new Name(input));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_NAME);
    }
}
