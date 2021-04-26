package bowling;

import bowling.domain.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {
    @Test
    public void lengthTest() {
        // given
        final String user = "ttokey";

        // when

        // then
        assertThatThrownBy(() -> {
            new User(user);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
