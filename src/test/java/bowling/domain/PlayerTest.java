package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class PlayerTest {


    @DisplayName("참가자의 이름은 3글자 이하여야 한다")
    @Test
    public void validate_success_nameLength() throws Exception {
        assertThatThrownBy(
                () -> new Player("abcdef")
        ).isInstanceOf(BowlingException.class);
    }
}
