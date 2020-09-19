package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

class PlayerTest {

    @Test
    @DisplayName("참가자 명 반환 검증")
    void getName() {
        String expected = "abc";

        then(Player.of(expected).getName()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"ab", "abcd"})
    @DisplayName("참가자 인스턴스 팩토리 예외 처리 검증")
    void of(String name) {
        assertThatThrownBy(() -> Player.of(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 영문 3 글자만 허용 됩니다.");
    }
}
