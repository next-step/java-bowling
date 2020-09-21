package bowling.model;

import bowling.exception.UserNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @ParameterizedTest
    @ValueSource(strings = {"toto", "java", "james"})
    @DisplayName("유저 생성 실패 : 이름 3글자 초과")
    public void create_fail(String name) {

        assertThatThrownBy(() -> User.valueOf(name))
                .isInstanceOf(UserNameException.class);

    }

}
