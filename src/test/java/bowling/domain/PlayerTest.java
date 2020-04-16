package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PlayerTest {

    @DisplayName("참가자의 이름은 3글자만 가능하다")
    @Test
    public void validate_success() throws Exception {
        new Player("aaa");
    }

    @DisplayName("참가자의 이름은 3글자가 아니면 exception")
    @Test
    public void validate_fail_nameLength() throws Exception {
        assertThatThrownBy(
                () -> new Player("abcdef")
        ).isInstanceOf(BowlingException.class);
    }
}
