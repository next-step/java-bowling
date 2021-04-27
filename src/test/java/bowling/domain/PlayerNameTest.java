package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class PlayerNameTest {

    @Test
    @DisplayName("알파벳 세 글자가 아닌 문자열이 입력되면 예외 처리한다.")
    void throwExceptionIfInputIsNotThreeLettersInAlphabet() {
        assertAll(
            () -> assertThatThrownBy(() -> PlayerName.wrap("AA1")).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> PlayerName.wrap("AAAA")).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatThrownBy(() -> PlayerName.wrap("한글")).isInstanceOf(IllegalArgumentException.class),
            () -> assertThatCode(() -> PlayerName.wrap("MCC")).doesNotThrowAnyException()
        );
    }
    
}
