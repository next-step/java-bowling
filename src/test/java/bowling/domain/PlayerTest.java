package bowling.domain;

import bowling.exception.InvalidNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("이름의 길이는 3글자가 아닌경우 예외를 반환한다.")
    void invalidNameLength() {
        assertThatThrownBy(() -> new Player("TEST"))
                .isInstanceOf(InvalidNameException.class)
                .hasMessage("영어 3글자로 입력해야 합니다.");
    }

    @Test
    @DisplayName("이름은 영어로만 되어야 한다.")
    void invalidNameType() {
        assertThatThrownBy(() -> new Player("테스트")).isInstanceOf(InvalidNameException.class);
        assertThatThrownBy(() -> new Player("Hi님")).isInstanceOf(InvalidNameException.class);
        assertThatThrownBy(() -> new Player("123")).isInstanceOf(InvalidNameException.class);
    }


}