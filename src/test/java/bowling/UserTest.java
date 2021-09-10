package bowling;

import bowling.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    public void 플레이어_이름_빈값() {
        Assertions.assertThatThrownBy(() -> new User(null))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> new User(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 플레이어_이름_영어아님() {
        Assertions.assertThatThrownBy(() -> new User("123"))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> new User("우경서"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 플레이어님_이름_길이_3자아님() {
        Assertions.assertThatThrownBy(() -> new User("ab "))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> new User("abcd"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
