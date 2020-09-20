package bowling.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @Test
    @DisplayName("유저의 이름 3글자 초과시 예외발생 테스트")
    void user_throw_exception_test() {
        assertThatThrownBy(() -> User.of("kjyang"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
