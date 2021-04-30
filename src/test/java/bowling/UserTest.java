package bowling;

import bowling.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    @DisplayName("이름이 세글자가 아닌 경우 예외 처리가 발생한다.")
    void nameLength_3() {
        Assertions.assertThatThrownBy(() -> {
            new User("SASS");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
