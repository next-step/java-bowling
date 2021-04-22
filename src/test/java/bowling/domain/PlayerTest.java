package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @DisplayName("이름을 3글자 초과로 입력하면 예외가 발생한다")
    @Test
    void max_name_length() {
        assertThatThrownBy(() -> {
            Player.from("shkwon");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("영어 이외에 한글, 숫자, 특수문자를 이름으로 생성하면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"권순호", "@!#", "123"})
    void only_english_name(String name) {
        assertThatThrownBy(() -> {
            Player.from(name);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
