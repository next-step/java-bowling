package bowling;

import bowling.model.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class UserTest {
    @Test
    public void 플레이어_이름_영어아님() {
        assertThatThrownBy(() -> new User("123"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new User("우경서"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 플레이어님_이름_길이_3자아님() {
        assertThatThrownBy(() -> new User("ab "))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new User("abcd"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
